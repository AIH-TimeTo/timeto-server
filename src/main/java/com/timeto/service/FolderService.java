package com.timeto.service;

import com.timeto.config.exception.ErrorCode;
import com.timeto.config.exception.GeneralException;
import com.timeto.domain.Folder;
import com.timeto.domain.Goal;
import com.timeto.domain.User;
import com.timeto.dto.folder.FolderRequest;
import com.timeto.dto.folder.FolderResponse;
import com.timeto.repository.FolderRepository;
import com.timeto.repository.GoalRepository;
import com.timeto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final UserRepository userRepository;
    private final GoalRepository goalRepository;
    private final FolderRepository folderRepository;

    public FolderResponse.CreateFolderRes createFolder (FolderRequest.CreateFolderReq request, Long userId) {

        // 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));

        // 목표 조회
        Goal goal = goalRepository.findById(request.goalId())
                .orElseThrow(() -> new GeneralException(ErrorCode.GOAL_NOT_FOUND));

        // 같은 목표 내에서 폴더 이름 중복 검사
        if (folderRepository.existsByGoalIdAndName(request.goalId(), request.folderName())) {
            throw new GeneralException(ErrorCode.DUPLICATE_FOLDER_NAME);
        }

        // Folder 엔티티 생성
        Folder folder = Folder.builder()
                .goal(goal)
                .name(request.folderName())
                .build();

        // 저장
        Folder savedFolder = folderRepository.save(folder);

        // 응답 DTO 반환
        return new FolderResponse.CreateFolderRes(
                savedFolder.getId(),
                savedFolder.getName(),
                savedFolder.getGoal().getId()
        );
    }
}

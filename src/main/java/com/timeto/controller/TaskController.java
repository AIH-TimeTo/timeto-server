package com.timeto.controller;

import com.timeto.apiPayload.ApiResponse;
import com.timeto.auth.jwt.JwtTokenProvider;
import com.timeto.config.exception.ErrorCode;
import com.timeto.config.exception.GeneralException;
import com.timeto.dto.task.TaskRequest;
import com.timeto.dto.task.TaskResponse;
import com.timeto.auth.oauth.CustomOAuth2User;
import com.timeto.repository.UserRepository;
import com.timeto.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Tag(name = "할 일 API", description = "할 일 관련 API")
public class TaskController {

    private final TaskService taskService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @PostMapping
    @Operation(summary = "TASK_API_01 : 할 일 생성", description = "새로운 할 일을 생성합니다.")
    public ApiResponse<TaskResponse.CreateTaskRes> createTask(
            @Valid @RequestBody TaskRequest.CreateTaskReq request,
            @RequestHeader("Authorization") String token) {

        // 현재 인증된 사용자 정보 가져오기
        String email = jwtTokenProvider.getEmailFromToken(token.replace("Bearer ", ""));
        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException(ErrorCode.USER_DEACTIVATED))
                .getId();

        // 서비스 호출하여 할 일 생성
        TaskResponse.CreateTaskRes response = taskService.createTask(request, userId);

        return ApiResponse.success("할 일이 생성되었습니다.", response);
    }

    @GetMapping("/{taskId}")
    @Operation(summary = "TASK_API_02 : 할 일 조회", description = "특정 할 일의 상세 정보를 조회합니다.")
    public ApiResponse<TaskResponse.GetTaskRes> getTask(
            @PathVariable Long taskId,
            @RequestHeader("Authorization") String token) {

        // 현재 인증된 사용자 정보 가져오기
        String email = jwtTokenProvider.getEmailFromToken(token.replace("Bearer ", ""));
        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException(ErrorCode.USER_DEACTIVATED))
                .getId();

        // 서비스 호출하여 할 일 조회
        TaskResponse.GetTaskRes response = taskService.getTask(taskId, userId);

        return ApiResponse.success("할 일이 조회되었습니다.", response);
    }

    @PutMapping
    @Operation(summary = "TASK_API_03 : 할 일 수정", description = "할 일의 정보를 수정합니다.")
    public ApiResponse<TaskResponse.EditTaskRes> editTask(
            @Valid @RequestBody TaskRequest.EditTaskReq request,
            @RequestHeader("Authorization") String token) {

        // 현재 인증된 사용자 정보 가져오기
        String email = jwtTokenProvider.getEmailFromToken(token.replace("Bearer ", ""));
        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException(ErrorCode.USER_DEACTIVATED))
                .getId();

        // 서비스 호출하여 할 일 수정
        TaskResponse.EditTaskRes response = taskService.editTask(request, userId);

        return ApiResponse.success("할 일이 수정되었습니다.", response);
    }

    @DeleteMapping("/{taskId}")
    @Operation(summary = "TASK_API_04 : 할 일 삭제", description = "특정 할 일을 삭제합니다.")
    public ApiResponse<TaskResponse.DeleteTaskRes> deleteTask(
            @PathVariable Long taskId,
            @RequestHeader("Authorization") String token) {

        // 현재 인증된 사용자 정보 가져오기
        String email = jwtTokenProvider.getEmailFromToken(token.replace("Bearer ", ""));
        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException(ErrorCode.USER_DEACTIVATED))
                .getId();

        // 서비스 호출하여 할 일 조회
        TaskResponse.DeleteTaskRes response = taskService.deleteTask(taskId, userId);

        return ApiResponse.success("할 일이 삭제 되었습니다.", response);
    }

    @PatchMapping("/done/{taskId}")
    @Operation(summary = "TASK_API_05 : 할 일 완료", description = "특정 할 일을 완료합니다.")
    public ApiResponse<TaskResponse.DoneTaskRes> doneTask(
            @PathVariable Long taskId,
            @RequestHeader("Authorization") String token) {

        // 현재 인증된 사용자 정보 가져오기
        String email = jwtTokenProvider.getEmailFromToken(token.replace("Bearer ", ""));
        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException(ErrorCode.USER_DEACTIVATED))
                .getId();

        TaskResponse.DoneTaskRes response = taskService.doneTask(taskId,userId);

        return ApiResponse.success("할 일이 완료되었습니다.", response);
    }

    @PatchMapping("/order")
    @Operation(summary = "TASK_API_06 : 할 일 순서 변경", description = "진행 중인 할 일의 순서를 변경합니다.")
    public ApiResponse<TaskResponse.EditTaskOrderRes> editTaskOrder(
            @Valid @RequestBody TaskRequest.EditTaskOrderReq request,
            @RequestHeader("Authorization") String token) {

        // 현재 인증된 사용자 정보 가져오기
        String email = jwtTokenProvider.getEmailFromToken(token.replace("Bearer ", ""));
        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException(ErrorCode.USER_DEACTIVATED))
                .getId();

        // 서비스 호출하여 할 일 순서 변경
        TaskResponse.EditTaskOrderRes response = taskService.editTaskOrder(request, userId);

        return ApiResponse.success("할 일 순서가 변경되었습니다.", response);
    }

    @GetMapping("/progress/{folderId}")
    @Operation(summary = "TASK_API_07 : 진행 중인 할 일만 조회", description = "진행 중인 할 일만 조회합니다.")
    public ApiResponse<TaskResponse.GetOnlyProgressTaskRes> getOnlyProgressTask (
            @PathVariable Long folderId,
            @RequestHeader("Authorization") String token
    ) {

        // 현재 인증된 사용자 정보 가져오기
        String email = jwtTokenProvider.getEmailFromToken(token.replace("Bearer ", ""));
        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException(ErrorCode.USER_DEACTIVATED))
                .getId();

        TaskResponse.GetOnlyProgressTaskRes response = taskService.getOnlyProgressTask(folderId, userId);

        return ApiResponse.success("진행 중인 할 일만 조회됐습니다.", response);
    }


}
package com.timeto.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 서버 에러
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "S001", "잘못된 입력값입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S002", "서버 에러가 발생했습니다."),
    //USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "U001", "존재하지 않는 사용자입니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "P001", "잘못된 파라미터가 포함되어 있습니다"),
    USER_DEACTIVATED(HttpStatus.FORBIDDEN, "U002", "탈퇴한 사용자입니다"),
    USER_ALREADY_DEACTIVATED(HttpStatus.FORBIDDEN, "U003", "이미 탈퇴한 사용자입니다."),

    // 목표 에러
    DUPLICATE_GOAL_NAME(HttpStatus.BAD_REQUEST, "G001", "목표 이름은 중복될 수 없습니다."),
    GOAL_NOT_FOUND(HttpStatus.NOT_FOUND, "G002", "목표를 찾을 수 없습니다."),
    SAME_GOAL_NAME(HttpStatus.BAD_REQUEST, "G003", "현재 목표 이름과 동일합니다"),
    SAME_GOAL_COLOR(HttpStatus.BAD_REQUEST, "G004","현재 목표 색상과 동일합니다"),

    // 폴더 에러
    DUPLICATE_FOLDER_NAME(HttpStatus.BAD_REQUEST, "F001", "같은 목표 내 폴더 이름은 중복될 수 없습니다."),
    FOLDER_NOT_FOUND(HttpStatus.NOT_FOUND, "F002", "폴더를 찾을 수 없습니다."),
    SAME_FOLDER_NAME(HttpStatus.BAD_REQUEST, "G003", "현재 폴더 이름과 동일합니다"),

    // 할 일 에러 
    TASK_NOT_FOUND(HttpStatus.NOT_FOUND, "T001", "할 일를 찾을 수 없습니다."),
    NO_CHANGES_DETECTED(HttpStatus.BAD_REQUEST, "T002","변경된 내용이 없습니다"),
    INVALID_DONE_CHANGES(HttpStatus.BAD_REQUEST, "T003", "완료된 할 일은 수정 및 이동할 수 없습니다."),
    INVALID_DONE(HttpStatus.BAD_REQUEST, "T004", "이미 완료 처리된 할 일입니다."),

    // 타임 블럭 에러
    TIME_BLOCK_OVERLAP(HttpStatus.CONFLICT, "TB001", "해당 시간에 이미 타임 블럭이 존재합니다"),
    TASK_ALREADY_IN_TIME_BLOCK(HttpStatus.CONFLICT, "TB002", "이미 타임 블럭에 등록된 할 일입니다"),
    TIME_BLOCK_NOT_FOUND(HttpStatus.NOT_FOUND, "TB003", "해당 타임 블럭을 찾을 수 없습니다"),
    INVALID_TIME_RANGE(HttpStatus.BAD_REQUEST, "TB004", "종료 시간은 시작 시간보다 앞설 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

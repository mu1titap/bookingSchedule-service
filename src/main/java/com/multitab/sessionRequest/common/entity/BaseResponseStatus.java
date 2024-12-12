package com.multitab.sessionRequest.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    /**
     * 200: 요청 성공
     **/
    SUCCESS(HttpStatus.OK, true, 200, "요청에 성공하였습니다."),

    /**
     * 400 : security 에러
     */
    WRONG_JWT_TOKEN(HttpStatus.UNAUTHORIZED, false, 401, "다시 로그인 해주세요"),
    NO_SIGN_IN(HttpStatus.UNAUTHORIZED, false, 402, "로그인을 먼저 진행해주세요"),
    NO_ACCESS_AUTHORITY(HttpStatus.FORBIDDEN, false, 403, "접근 권한이 없습니다"),
    DISABLED_USER(HttpStatus.FORBIDDEN, false, 404, "비활성화된 계정입니다. 계정을 복구하시겠습니까?"),
    FAILED_TO_RESTORE(HttpStatus.INTERNAL_SERVER_ERROR, false, 405, "계정 복구에 실패했습니다. 관리자에게 문의해주세요."),
    NO_EXIST_OAUTH(HttpStatus.NOT_FOUND, false, 406, "소셜 로그인 정보가 존재하지 않습니다."),

    /**
     * 900: 기타 에러
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 900, "Internal server error"),
    SSE_SEND_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, false, 901, "알림 전송에 실패하였습니다."),

    /**
     * 2000: users service error
     */
    // token
    TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED, false, 2001, "토큰이 유효하지 않습니다."),

    // Users
    DUPLICATED_USER(HttpStatus.CONFLICT, false, 2101, "이미 가입된 멤버입니다."),
    FAILED_TO_LOGIN(HttpStatus.UNAUTHORIZED, false, 2102, "아이디 또는 패스워드를 다시 확인하세요."),
    DUPLICATED_SOCIAL_USER(HttpStatus.CONFLICT, false, 2103, "이미 소셜 연동된 계정입니다."),
    DUPLICATED_SOCIAL_PROVIDER_USER(HttpStatus.CONFLICT, false, 2104, "계정에 동일한 플랫폼이 이미 연동되어있습니다"),
    NO_EXIST_USER(HttpStatus.NOT_FOUND, false, 2105, "존재하지 않는 멤버 정보입니다."),
    PASSWORD_SAME_FAILED(HttpStatus.BAD_REQUEST, false, 2106, "현재 사용중인 비밀번호 입니다."),
    PASSWORD_CONTAIN_NUM_FAILED(HttpStatus.BAD_REQUEST, false, 2107, "휴대폰 번호를 포함한 비밀번호 입니다."),
    PASSWORD_MATCH_FAILED(HttpStatus.BAD_REQUEST, false, 2108, "패스워드를 다시 확인해주세요."),
    NO_SUPPORTED_PROVIDER(HttpStatus.BAD_REQUEST, false, 2109, "지원하지 않는 플랫폼입니다"),
    DUPLICATED_NICKNAME(HttpStatus.CONFLICT, false, 2010, "이미 사용중인 닉네임입니다."),
    SAME_NICKNAME(HttpStatus.CONFLICT, false, 2011, "현재 사용중인 닉네임입니다."),
    INVALID_EMAIL_ADDRESS(HttpStatus.BAD_REQUEST, false, 2012, "이메일을 다시 확인해주세요."),

    /**
     * 3000: product service error
     */

    // Product
    NO_EXIST_PRODUCT(HttpStatus.NOT_FOUND, false, 3001, "존재하지 않는 상품입니다"),
    NO_EXIST_OPTION(HttpStatus.NOT_FOUND, false, 3002, "존재하지 않는 옵션입니다"),
    NO_EXIST_CATEGORY(HttpStatus.NOT_FOUND, false, 3003, "존재하지 않는 카테고리입니다"),

    DUPLICATED_PRODUCT(HttpStatus.CONFLICT, false, 3004, "이미 등록된 상품입니다"),
    DUPLICATED_OPTION(HttpStatus.CONFLICT, false, 3005, "이미 등록된 옵션입니다"),
    DUPLICATED_CATEGORY(HttpStatus.CONFLICT, false, 3006, "이미 등록된 카테고리입니다"),

    NO_EXIST_OPTIONS_IN_PRODUCT(HttpStatus.NOT_FOUND, false, 3007, "해당 상품에 옵션이 존재하지 않습니다"),

    /**
     * 4000: comment service error
     */

    // Comment
    NO_EXIST_COMMENT(HttpStatus.NOT_FOUND, false, 4001, "존재하지 않는 댓글입니다"),
    NO_DELETE_COMMENT_AUTHORITY(HttpStatus.BAD_REQUEST, false, 4002, "댓글 삭제 권한이 없습니다"),
    NO_DELETE_RE_COMMENT_AUTHORITY(HttpStatus.BAD_REQUEST, false, 4003, "대댓글 삭제 권한이 없습니다"),
    NO_EXIST_RE_COMMENT(HttpStatus.NOT_FOUND, false, 4003, "존재하지 않는 대댓글입니다"),
    NO_EXIST_PIN_AUTHORITY(HttpStatus.BAD_REQUEST, false, 4004, "고정 권한이 없습니다"),

    /**
     * 5000: notification service error
     */

    // Notification
    NO_EXIST_NOTIFICATION_SETTING(HttpStatus.NOT_FOUND, false, 5001, "유저의 알림 설정이 존재하지 않습니다."),
    EXIST_NOTIFICATION_SETTING(HttpStatus.BAD_REQUEST, false, 5002, "유저의 알림 설정이 이미 존재합니다."),
    NO_EXIST_NOTIFICATION(HttpStatus.NOT_FOUND, false, 5003, "존재하지 않는 알림입니다."),
    CANNOT_SHARE(HttpStatus.BAD_REQUEST, false, 5004, "공유할 수 없는 유저입니다."),

    /**
     * 6000: gpt-api error
     */
    // Media
    NO_EXIST_MEDIA(HttpStatus.NOT_FOUND, false, 6001, "존재하지 않는 미디어입니다"),





    /**
     * 7000 : 세션 참가
     */
    IS_NOT_HISTORY(HttpStatus.BAD_REQUEST, false, 7001, "세션 참가 이력 없음"),

    DEAD_LINE_PASSED(HttpStatus.BAD_REQUEST, false, 7002, "예약 마감일 경과"),
    INVALID_SESSION_USER_STATUS(HttpStatus.BAD_REQUEST, false, 7003, "세션 참가 등록을 취소할 수 있는 상태가 아님"),
    ALREADY_CANCEL(HttpStatus.BAD_REQUEST, false, 7004, "이미 취소된 세션 신청"),
    ALREADY_CLOSE_SESSION(HttpStatus.BAD_REQUEST, false, 7005, "닫힌 세션"),
    FULL_SESSION_USER(HttpStatus.BAD_REQUEST, false, 7006, "최대 인원수 초과"),
    DUPLICATE_SESSION_REQUEST(HttpStatus.BAD_REQUEST, false, 7007, "중복신청"),
    NO_MENTORING_SESSION_INFORMATION(HttpStatus.BAD_REQUEST, false, 7008, "세션 정보 없음"),
    /**
     * feign client error
     */
    MENTORING_SERVICE_FEIGN_ERROR(HttpStatus.NOT_FOUND, false, 8000, "멘토링 서비스 페인 클라이언트 에러");


    private final HttpStatus httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;



}

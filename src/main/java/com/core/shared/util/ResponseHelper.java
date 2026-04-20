package com.core.shared.util;

import com.core.shared.dto.ApiResponse;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {

    public static <T> ResponseEntity<ApiResponse<T>> ok(T data, String msg) {
        return ResponseEntity.ok(ApiResponse.success(data, msg));
    }

    public static ResponseEntity<ApiResponse<?>> error(String msg, int status) {
        return ResponseEntity.status(status).body(ApiResponse.error(msg));
    }
}

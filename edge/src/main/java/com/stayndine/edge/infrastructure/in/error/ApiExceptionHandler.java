package com.stayndine.edge.infrastructure.in.error;

import com.stayndine.edge.infrastructure.error.DownstreamException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException ex) {
        var field = ex.getBindingResult().getFieldError();
        var msg = (field != null ? field.getField() + ": " + field.getDefaultMessage() : "Validation error");
        return ResponseEntity.badRequest().body(Map.of(
                "timestamp", Instant.now(),
                "status", 400,
                "error", "bad_request",
                "message", msg
        ));
    }

    @ExceptionHandler(DownstreamException.class)
    public ResponseEntity<Map<String,Object>> handleDownstream(DownstreamException ex) {
        var status = ex.getStatus();
        return ResponseEntity.status(status).body(Map.of(
                "timestamp", Instant.now(),
                "status", status.value(),
                "error", status.getReasonPhrase().toLowerCase().replace(' ', '_'),
                "message", ex.getMessage(),
                "downstream_service", ex.getService(),
                "downstream_body", ex.getBody()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleOther(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "timestamp", Instant.now(),
                "status", 500,
                "error", "internal_server_error",
                "message", ex.getMessage()
        ));
    }
}
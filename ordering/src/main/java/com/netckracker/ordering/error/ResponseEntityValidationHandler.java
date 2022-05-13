package com.netckracker.ordering.error;

import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResponseEntityValidationHandler extends ResponseEntityExceptionHandler {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    private static class ErrorResponse {
        private String timestamp;
        private Integer status;
        private HttpStatus error;
        private Map<String, String> fields;
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  @NonNull HttpHeaders headers, @NonNull HttpStatus status,
                                                                  @NonNull WebRequest request) {
        Map<String, String> fields = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((field ->
            fields.put(((FieldError) field).getField(), field.getDefaultMessage())
        ));

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                .format(Calendar.getInstance().getTime()));
        errorResponse.setStatus(status.value());
        errorResponse.setError(status);
        errorResponse.setFields(fields);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

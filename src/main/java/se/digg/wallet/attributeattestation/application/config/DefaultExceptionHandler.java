// SPDX-FileCopyrightText: 2025 diggsweden/wallet-attribute-attestation
//
// SPDX-License-Identifier: EUPL-1.2

package se.digg.wallet.attributeattestation.application.config;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import se.digg.wallet.attributeattestation.application.model.BadRequestDto;

@ControllerAdvice
public class DefaultExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

  private HttpServletRequest httpServletRequest;

  DefaultExceptionHandler(HttpServletRequest httpServletRequest) {
    this.httpServletRequest = httpServletRequest;
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(BAD_REQUEST)
  public void handleException(IllegalArgumentException e) {
    logger.error("Bad request: {}, responding with 400", e.getMessage(), e);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(BAD_REQUEST)
  public ResponseEntity<BadRequestDto> handleException(MethodArgumentNotValidException e) {
    String instance = httpServletRequest.getServletPath();
    BadRequestDto body = new BadRequestDto(
        null,
        "Validation error",
        HttpStatus.BAD_REQUEST.value(),
        getErrorsMap(e).toString(),
        instance);
    return ResponseEntity.badRequest().body(body);
  }

  @ExceptionHandler(Throwable.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public void handleException(Throwable t) {
    logger.error("Unhandled exception, responding with 500", t);
  }

  private Map<String, List<String>> getErrorsMap(MethodArgumentNotValidException e) {
    Map<String, List<String>> errorResponse = new HashMap<>();
    errorResponse.put("errors", e.getBindingResult().getFieldErrors()
        .stream().map(FieldError::getDefaultMessage).toList());
    return errorResponse;
  }
}

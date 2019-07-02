package fr.florentclarret.polytechtours.javaperformance.videogameapi.controller.advice;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public final class CommonAdvice {

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity businessException(final BusinessException businessException) {
        return ResponseEntity.status(businessException.getHttpStatus()).body(businessException.getMessage());
    }
}

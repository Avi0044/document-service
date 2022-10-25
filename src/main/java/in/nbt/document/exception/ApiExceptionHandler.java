package in.nbt.document.exception;

import in.nbt.document.controller.TemplateController;
import in.nbt.document.dto.responses.ApiError;
import in.nbt.document.dto.responses.ApiSubError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    protected static final Logger log = LogManager.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler({BadRequestException.class,UnexpectedTypeException.class})
    protected ResponseEntity<Object> handleEntityNotFound(
            RuntimeException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            NotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(TemplateException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            TemplateException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(UnAuthorizedException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            UnAuthorizedException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        //log.error("Error in handleMethodArgumentNotValid: {}", ex.getMessage());
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<ApiSubError> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        for (FieldError fieldError : fieldErrors) {
            ApiSubError error =
                    new ApiSubError(fieldError.getObjectName(),fieldError.getField(), fieldError.getCode(), fieldError.getDefaultMessage());
            errors.add(error);
            log.error("Exception Occurred: {}",error);
        }
        for (ObjectError objectError : globalErrors) {
            ApiSubError error = new ApiSubError(null,objectError.getObjectName(), objectError.getDefaultMessage(),
                    objectError.getCode());
            errors.add(error);
            log.info("Exception Occurred: {}",error);
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Please Enter Valid Template!");
        apiError.setSubErrors(errors);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage(ex.getMessage());
        log.info("Exception Occurred: {}",ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}

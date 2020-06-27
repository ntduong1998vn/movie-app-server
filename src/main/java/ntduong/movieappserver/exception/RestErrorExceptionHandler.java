package ntduong.movieappserver.exception;

import lombok.extern.slf4j.Slf4j;
import ntduong.movieappserver.dto.ApiResponse;
import ntduong.movieappserver.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
public class RestErrorExceptionHandler extends ResponseEntityExceptionHandler {

    public RestErrorExceptionHandler() {
        super();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse<String> handleBadRequest(Exception e) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
        apiResponse.setMessage(e.getMessage());
        return apiResponse;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Bad Request: {}", ex.getMessage());
        log.debug("Bad Request: ", ex);

        final BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        final ValidationErrorDTO dto = processFieldErrors(fieldErrors);

        return handleExceptionInternal(ex, dto, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleNoHandlerFoundException(RuntimeException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.warn("Not Found: {}", ex.getMessage());
        ApiResponse<String> apiResponse = new ApiResponse<>(HttpStatus.NOT_FOUND, ex.getMessage());
        return handleExceptionInternal(ex, apiResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception exp) {
        ApiResponse<String> err = new ApiResponse<>();
        err.setSuccess(HttpStatus.BAD_REQUEST);
        err.setMessage(exp.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    private ValidationErrorDTO processFieldErrors(final List<FieldError> fieldErrors) {
        final ValidationErrorDTO dto = new ValidationErrorDTO();

        for (final FieldError fieldError : fieldErrors) {
            final String localizedErrorMessage = fieldError.getDefaultMessage();
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }
        return dto;
    }
}

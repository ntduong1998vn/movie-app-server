package ntduong.movieappserver.exception;

import ntduong.movieappserver.dto.ApiResponse;
import ntduong.movieappserver.dto.ErrorResponse;
import ntduong.movieappserver.model.ApiErrorVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestErrorExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exp){
        ErrorResponse err = new ErrorResponse();
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(exp.getMessage());
        err.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleBadRequest(BadRequestException e){
        return new ApiResponse(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErrorVO handleValidationError(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String defaultMessage = fieldError.getDefaultMessage();
        return new ApiErrorVO("VALIDATION_FAILED", defaultMessage);
    }


}

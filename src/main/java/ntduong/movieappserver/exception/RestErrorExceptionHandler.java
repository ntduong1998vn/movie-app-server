package ntduong.movieappserver.exception;

import ntduong.movieappserver.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestErrorExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(MovieErrorException exp){
        ErrorResponse err = new ErrorResponse();
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(exp.getMessage());
        err.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }

}

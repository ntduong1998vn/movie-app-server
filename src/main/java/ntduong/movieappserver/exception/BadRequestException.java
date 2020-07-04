package ntduong.movieappserver.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class BadRequestException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}

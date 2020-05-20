package ntduong.movieappserver.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResponse {

    private HttpStatus success;
    private String message;

    public ApiResponse(HttpStatus success, String message) {
        this.success = success;
        this.message = message;
    }

}

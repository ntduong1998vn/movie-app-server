package ntduong.movieappserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FieldErrorDTO {

    private final String field;
    private final String message;

}
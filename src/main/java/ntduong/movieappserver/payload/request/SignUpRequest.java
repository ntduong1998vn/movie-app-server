/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignUpRequest {
    private String name;
    private String username;
    private String email;
    private String password;

}

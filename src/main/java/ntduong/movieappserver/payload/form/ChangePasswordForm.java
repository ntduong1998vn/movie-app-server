/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.payload.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordForm {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}

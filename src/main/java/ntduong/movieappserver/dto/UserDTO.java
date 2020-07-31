/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String email;
    @JsonProperty("image_url")
    private String imageUrl;
    private String name;
    private String username;
    private String provider;
    @JsonProperty("delete_flag")
    private boolean deleteFlag;
    private List<RoleDTO> roles;
}

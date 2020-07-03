/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActorForm {
    private int id;
    private String name;
    private String nation;
    private MultipartFile image;
    @JsonProperty("delete_character_list")
    List<Integer> deleteCharacterList = new ArrayList<>();
    @JsonProperty("add_character_list")
    List<Integer> insertCharacterList = new ArrayList<>();
}

package ntduong.movieappserver.util.searchMovie;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;
}

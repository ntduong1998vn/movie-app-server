package ntduong.movieappserver.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SourceDTO {
    private int id;
    private String src;
    private String label;
    private String server;
    private String resolution;
}

/*
 * @Author by NguyenTrieuDuong
 */

package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.ActorDTO;
import ntduong.movieappserver.payload.form.ActorForm;
import ntduong.movieappserver.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface IActorService {
    ActorDTO findById(int actorId);

    void add(ActorForm actorForm) throws IOException;

    void update(ActorForm actorForm) throws IOException, ResourceNotFoundException;

    void deleteOne(int actorId);

    void deleteList(List<Integer> list);

    Page<ActorDTO> getList(int page, int size);
}

/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.CharacterDTO;
import ntduong.movieappserver.exception.ResourceNotFoundException;

import java.util.List;

public interface ICharacterService {
    void addCharacter(CharacterDTO characterDTO);

    void delete(int characterId) throws ResourceNotFoundException;

    void deleteAll(List<Integer> deleteList) throws ResourceNotFoundException;

    List<CharacterDTO> findByActorId(int actorId);

    List<CharacterDTO> findByMovieId(int movieId);

    void deleteByActorId(int actorId);

    void deleteByMovieId(int movieId);
}

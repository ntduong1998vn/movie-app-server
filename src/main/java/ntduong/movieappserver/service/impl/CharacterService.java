/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.service.impl;

import ntduong.movieappserver.dto.CharacterDTO;
import ntduong.movieappserver.entity.CharacterEntity;
import ntduong.movieappserver.exception.ResourceNotFoundException;
import ntduong.movieappserver.repository.CharacterRepository;
import ntduong.movieappserver.service.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterService implements ICharacterService {
    @Autowired
    CharacterRepository characterRepository;

    @Override
    public void addCharacter(CharacterDTO characterDTO) {

    }

    @Override
    public void delete(int characterId) throws ResourceNotFoundException {

    }

    @Override
    public void deleteAll(List<Integer> deleteList) throws ResourceNotFoundException {
        List<CharacterEntity> characterList = new ArrayList<>();
        for (int id : deleteList) {
            CharacterEntity character = characterRepository.findById(id).orElse(null);
            if (character == null)
                throw new ResourceNotFoundException("Character", "id", id);
            characterList.add(character);
        }
        characterRepository.deleteAll(characterList);
    }

    @Override
    public List<CharacterDTO> findByActorId(int actorId) {
        return null;
    }

    @Override
    public List<CharacterDTO> findByMovieId(int movieId) {
        return null;
    }
}

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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterService implements ICharacterService {
    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    ModelMapper modelMapper;

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
        List<CharacterEntity> list = characterRepository.findByMovieId(movieId);
        return list.stream()
                .map(entity -> modelMapper.map(entity, CharacterDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByActorId(int actorId) {
        characterRepository.deleteByActorId(actorId);
    }

    @Override
    public void deleteByMovieId(int movieId) {
        characterRepository.deleteByMovieId(movieId);
    }
}

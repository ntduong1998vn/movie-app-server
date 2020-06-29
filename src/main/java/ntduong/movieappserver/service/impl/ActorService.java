/*
 * @Author by NguyenTrieuDuong
 */

package ntduong.movieappserver.service.impl;

import ntduong.movieappserver.constant.Constants;
import ntduong.movieappserver.dto.ActorDTO;
import ntduong.movieappserver.dto.ActorForm;
import ntduong.movieappserver.entity.ActorEntity;
import ntduong.movieappserver.entity.CharacterEntity;
import ntduong.movieappserver.exception.ResourceNotFoundException;
import ntduong.movieappserver.repository.ActorRepository;
import ntduong.movieappserver.service.IActorService;
import ntduong.movieappserver.service.ICharacterService;
import ntduong.movieappserver.util.ImageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ActorService implements IActorService {
    @Autowired
    ImageUtil imageUtil;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ICharacterService characterService;

    @Override
    public void add(ActorForm actorForm) throws IOException {
        MultipartFile imgFile = actorForm.getImage();
        if (imgFile != null) {
            imageUtil.uploadImage(Constants.AVATAR, imgFile.getOriginalFilename(), imgFile.getContentType(), imgFile.getInputStream());
            ActorEntity actorEntity = new ActorEntity();
            actorEntity.setName(actorForm.getName());
            actorEntity.setAvatar(imgFile.getOriginalFilename());
            actorEntity.setNation(actorForm.getNation());
            actorRepository.save(actorEntity);
        }
    }

    /*
        update function only delete logic with CharacterEntity, not add logic with CharacterEntity
    */
    @Override
    public void update(ActorForm actorForm) throws IOException, ResourceNotFoundException {
        ActorEntity actorEntity = actorRepository.findById(actorForm.getId()).orElse(null);
        if (actorEntity != null) {

            // Delete old image and update new image
            MultipartFile file = actorForm.getImage();
            if (actorForm.getImage() != null) {
                if (Objects.equals(file.getContentType(), Constants.JPEG) ||
                        Objects.equals(file.getContentType(), Constants.PNG)) {
                    if (imageUtil.deleteImage(Constants.AVATAR, actorEntity.getAvatar())) {
                        imageUtil.uploadImage(Constants.AVATAR, file.getOriginalFilename(), file.getContentType(), file.getInputStream());
                        actorEntity.setAvatar(file.getOriginalFilename());
                    }
                }
            }
            // delete logic with CharacterEntity
            if(!actorForm.getDeleteCharacterList().isEmpty()){
                characterService.deleteAll(actorForm.getDeleteCharacterList());
            }
            // TODO : Viet tiep
            actorEntity.setName(actorForm.getName());
            actorEntity.setNation(actorForm.getNation());
            actorRepository.save(actorEntity);
        } else throw new ResourceNotFoundException("Actor", "Id", actorForm.getId());
    }

    @Override
    public void deleteOne(int actorId) {

    }

    @Override
    public void deleteList(List<Integer> list) {

    }

    @Override
    public ActorDTO findById(int actorId) {
        Optional<ActorEntity> entityOptional = actorRepository.findById(actorId);
        return entityOptional.map(actorEntity -> modelMapper.map(actorEntity, ActorDTO.class)).orElse(null);
    }

    @Override
    public Page<ActorDTO> getList(int page, int size) {
        Page<ActorEntity> entityPage = actorRepository.findAll(PageRequest.of(page, size));
        return entityPage.map(actorEntity -> modelMapper.map(actorEntity, ActorDTO.class));
    }


}

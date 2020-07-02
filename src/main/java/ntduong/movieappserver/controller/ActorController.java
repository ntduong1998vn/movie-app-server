/*
 * @Author by NguyenTrieuDuong
 */

package ntduong.movieappserver.controller;

import io.swagger.annotations.ApiOperation;
import ntduong.movieappserver.dto.ActorDTO;
import ntduong.movieappserver.dto.form.ActorForm;
import ntduong.movieappserver.dto.ApiResponse;
import ntduong.movieappserver.exception.ResourceNotFoundException;
import ntduong.movieappserver.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    @Autowired
    IActorService actorService;

    @ApiOperation("GET LIST BY PAGE")
    @GetMapping("/")
    public Page<ActorDTO> getListActors(@RequestParam(name = "currentPage", defaultValue = "0") int currentPage,
                                        @RequestParam(name = "pageSize", defaultValue = "6") int pageSize) {
        return actorService.getList(currentPage, pageSize);
    }

    @ApiOperation("FIND ACTOR BY ID")
    @GetMapping("/{id}")
    public ApiResponse<ActorDTO> findById(@PathVariable("id") int id) {
        ApiResponse<ActorDTO> apiResponse = new ApiResponse<>();
        ActorDTO actorDTO = actorService.findById(id);
        if (actorDTO != null) {
            apiResponse.setResult(actorDTO);
            apiResponse.setSuccess(HttpStatus.OK);
        } else {
            apiResponse.setMessage("NOT FOUND ACTOR BY ID: " + id);
            apiResponse.setSuccess(HttpStatus.NOT_FOUND);
        }
        return apiResponse;
    }

    @ApiOperation("CREATE A ACTOR")
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> create(@ModelAttribute ActorForm actorForm) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        if (actorForm.getName().length() <= 50 && actorForm.getNation().length() <= 50) {
            try {
                actorService.add(actorForm);
                apiResponse.setSuccess(HttpStatus.CREATED);
                apiResponse.setMessage("Tạo thành công.");
            } catch (Exception e) {
                apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
                apiResponse.setMessage(e.getMessage());
            }
        } else {
            apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
            apiResponse.setMessage("Field INVALID.");
        }

        return apiResponse;
    }

    @ApiOperation("UPDATE A ACTOR BY ID")
    @PutMapping("/{id}")
    public ApiResponse<String> update(@PathVariable("id") int id,
                                      @ModelAttribute ActorForm actorForm) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        try {
            actorService.update(actorForm);
            apiResponse.setSuccess(HttpStatus.OK);
            apiResponse.setMessage("Cập nhật thành công.");
        } catch (Exception e) {
            apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @ApiOperation("DELETE A ACTOR")
    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable int id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        try {
            actorService.deleteOne(id);
            apiResponse.setSuccess(HttpStatus.OK);
            apiResponse.setMessage("Thành công.");
        } catch (ResourceNotFoundException e) {
            apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }
}

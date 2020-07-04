package ntduong.movieappserver.controller;

import ntduong.movieappserver.dto.ApiResponse;
import ntduong.movieappserver.dto.GenreDTO;
import ntduong.movieappserver.entity.GenreEntity;
import ntduong.movieappserver.service.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    private IGenreService service;

    @GetMapping("/")
    public ApiResponse<List<GenreDTO>> getAll() {
        ApiResponse<List<GenreDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(HttpStatus.OK);
        apiResponse.setResult(service.findAll());
        return apiResponse;
    }

    @PostMapping("/")
    public ApiResponse<String> addNew(@RequestBody @Valid GenreDTO genreForm) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        if (service.create(genreForm)) {
            apiResponse.setSuccess(HttpStatus.CREATED);
            apiResponse.setMessage("Tạo thành công");
        } else {
            apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
            apiResponse.setMessage("Xuất hiện lỗi");
        }
        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<GenreEntity> getOne(@PathVariable int id) {
        ApiResponse<GenreEntity> apiResponse = new ApiResponse<>();
        Optional<GenreEntity> result = service.findById(id);
        if (result.isPresent()) {
            apiResponse.setSuccess(HttpStatus.OK);
            apiResponse.setResult(result.get());
        } else {
            apiResponse.setSuccess(HttpStatus.NOT_FOUND);
            apiResponse.setMessage("Không tìm thấy thể loại với id = " + id);
        }
        return apiResponse;
    }

    @PutMapping("/{genreId}")
    public ApiResponse<GenreEntity> update(@PathVariable int genreId,
                                           @Valid @RequestBody GenreDTO genreDTO) {
        ApiResponse<GenreEntity> apiResponse = new ApiResponse<>();

        if (genreDTO.getName().trim().length() <= 45) {
            GenreEntity result = service.update(genreDTO);
            if (result == null) {
                apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
                apiResponse.setMessage("Cập nhật không thành công");
            } else {
                apiResponse.setSuccess(HttpStatus.OK);
                apiResponse.setMessage("Cập nhật thành công");
                apiResponse.setResult(result);
            }
        } else {
            apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
            apiResponse.setMessage("Tên thể loại chưa nhập hoặc dài hơn 45 ký tự");
        }
        return apiResponse;
    }

    @DeleteMapping("/{genreId}")
    public ApiResponse delete(@PathVariable int genreId) {
        boolean result = service.delete(genreId);
        if (result) return new ApiResponse(HttpStatus.OK, "Xoá thành công!");

        return new ApiResponse(HttpStatus.NOT_FOUND, "Xóa thất bại!");
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByName(@RequestParam(name = "name") String name) {
        List<GenreEntity> result = service.findByName(name);
        return ResponseEntity.ok(result);
    }

}

package ntduong.movieappserver.controller;

import ntduong.movieappserver.dto.ApiResponse;
import ntduong.movieappserver.dto.GenreDTO;
import ntduong.movieappserver.model.Genre;
import ntduong.movieappserver.repository.GenreRepository;
import ntduong.movieappserver.service.impl.GenreService;
import ntduong.movieappserver.service.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    private IGenreService service;

    @Autowired
    public GenreController(GenreService genreService) {
        this.service = genreService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Page<Genre> getAll(@RequestParam(name = "currentPage",defaultValue = "0")int page,
                                              @RequestParam(name = "pageSize",defaultValue = "10")int size) {
        if(page!=0) page -= 1;
        return service.findAll(page, size);
    }

    @PostMapping("/")
    public ResponseEntity<String> addNew(@RequestBody @Valid GenreDTO genreForm){
        Genre genre = new Genre();
        genre.setName(genreForm.getName());
        boolean rs = service.save(genre);

        if(rs) return ResponseEntity.status(HttpStatus.CREATED).body("Tạo thành công");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xuất hiện lỗi");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable int id) {
        Optional<Genre> result = service.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can't find genre with id=" + id);
    }

    @PutMapping("/{genreId}")
    public ResponseEntity<String> update(@PathVariable int genreId,
                                    @Valid @RequestBody GenreDTO genreDTO) {
        Genre result = service.update(genreId,genreDTO);
        if(result ==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can't find genre with id=" + genreId);
        return ResponseEntity.ok("Cập nhật thành công!");
    }

    @DeleteMapping("/{genreId}")
    public ApiResponse delete(@PathVariable int genreId) {
        boolean result = service.delete(genreId);
        if (result) return new ApiResponse(HttpStatus.OK,"Xoá thành công!");

        return new ApiResponse(HttpStatus.NOT_FOUND,"Xóa thất bại!");
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByName(@RequestParam(name = "name") String name) {
        List<Genre> result = service.findByName(name);
        return ResponseEntity.ok(result);
    }

}

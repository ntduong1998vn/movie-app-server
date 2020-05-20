package ntduong.movieappserver.controller;

import ntduong.movieappserver.dto.CommentDTO;
import ntduong.movieappserver.repository.CommentRepository;
import ntduong.movieappserver.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/comment")
public class CommentController {

    private ICommentService service;

    @Autowired
    public CommentController(ICommentService service) {
        this.service = service;
    }


    // localhost:8080/api/comment/?movieId=_&userId=_&currentPage=_&pageSize=_
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Page<CommentDTO> getCommentsByMovieID(@RequestParam(name = "movieId", defaultValue = "-1") int movieId,
                                                 @RequestParam(name = "userId", defaultValue = "-1") int userId,
                                                 @RequestParam(name = "currentPage", defaultValue = "0") int currentPage,
                                                 @RequestParam(name = "pageSize", defaultValue = "6") int pageSize) {
        if (userId <=0 && movieId <= 0)
            return null;
        else if (userId <= 0) {
            return service.findByMovieId(movieId,currentPage,pageSize);
        } else if (movieId <= 0) {
            return null;
        } else
            return service.findByMovieIdAndUserId(movieId, userId,currentPage,pageSize);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createComment(@RequestBody CommentDTO comment) {
        return "Thành công";
    }


}

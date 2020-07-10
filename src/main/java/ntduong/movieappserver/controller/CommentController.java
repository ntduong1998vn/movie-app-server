package ntduong.movieappserver.controller;

import io.swagger.annotations.ApiOperation;
import ntduong.movieappserver.payload.ApiResponse;
import ntduong.movieappserver.dto.CommentDTO;
import ntduong.movieappserver.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {

    @Autowired
    ICommentService commentService;

    // localhost:8080/api/comment/search?movieId=__&userId=__&currentPage=__&pageSize=__
    @ApiOperation("GET COMMENT LIST BY UserID , MovieID")
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<CommentDTO> getComments(@RequestParam(name = "movieId", defaultValue = "-1") int movieId,
                                        @RequestParam(name = "userId", defaultValue = "-1") int userId,
                                        @RequestParam(name = "currentPage", defaultValue = "0") int currentPage,
                                        @RequestParam(name = "pageSize", defaultValue = "6") int pageSize) {
        if (userId <= 0 && movieId <= 0)
            return null;
        else if (userId <= 0) {
            return commentService.findByMovieId(movieId, currentPage, pageSize);
        } else if (movieId <= 0) {
            return commentService.findByUserId(userId, currentPage, pageSize);
        } else
            return commentService.findByMovieIdAndUserId(movieId, userId, currentPage, pageSize);
    }

    @ApiOperation("ADD ONE COMMENT")
    @PostMapping("/")
    public ApiResponse<String> addComment(@RequestBody CommentDTO commentDTO) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        try {
            commentService.addComment(commentDTO);
            apiResponse.setSuccess(HttpStatus.CREATED);
            apiResponse.setMessage("Tạo thành công");
        } catch (ValidationException e) {
            apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @ApiOperation("REMOVE ONE COMMENT")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteOne(@PathVariable("id") int id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        commentService.deleteOne(id);
        apiResponse.setSuccess(HttpStatus.OK);
        apiResponse.setMessage("Xoá thành công.");
        return apiResponse;
    }

    @ApiOperation("REMOVE MANY COMMENT")
    @DeleteMapping("/delete-many")
    public ApiResponse<String> deleteMany(@RequestBody List<Integer> deleteList){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        commentService.deleteList(deleteList);
        return apiResponse;
    }


}

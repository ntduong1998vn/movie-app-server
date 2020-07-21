package ntduong.movieappserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ntduong.movieappserver.payload.ApiResponse;
import ntduong.movieappserver.dto.ReviewDTO;
import ntduong.movieappserver.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@Api("Review API")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    IReviewService reviewService;

    @ApiOperation("GET REVIEW LIST BY UserID , MovieID")
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<ReviewDTO> search(@RequestParam(name = "movieId", defaultValue = "-1") int movieId,
                                  @RequestParam(name = "userId", defaultValue = "-1") int userId,
                                  @RequestParam(name = "currentPage", defaultValue = "0") int currentPage,
                                  @RequestParam(name = "pageSize", defaultValue = "6") int pageSize) {
        if (userId <= 0 && movieId <= 0)
            return null;
        else if (userId <= 0) {
            return reviewService.findByMovieId(movieId, currentPage, pageSize);
        } else if (movieId <= 0) {
            return reviewService.findByUserId(userId, currentPage, pageSize);
        } else
            return reviewService.findByMovieIdAndUserId(movieId, userId, currentPage, pageSize);
    }

    @ApiOperation("ADD ONE REVIEW")
    @PostMapping("/")
    public ApiResponse<String> addComment(@RequestBody ReviewDTO reviewDTO) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        if (reviewDTO.getScore() > 10 || reviewDTO.getScore() < 0) {
            apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
            apiResponse.setMessage("Score must in range(0,10)");
        } else {
            try {
                reviewService.addReview(reviewDTO);
                apiResponse.setSuccess(HttpStatus.CREATED);
                apiResponse.setMessage("Tạo thành công");
            } catch (ValidationException e) {
                apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
                apiResponse.setMessage(e.getMessage());
            }
        }

        return apiResponse;
    }

    @ApiOperation("REMOVE ONE REVIEW")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteOne(@PathVariable("id") int id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        reviewService.deleteOne(id);
        apiResponse.setSuccess(HttpStatus.OK);
        apiResponse.setMessage("Xoá thành công.");
        return apiResponse;
    }

    @ApiOperation("REMOVE MANY REVIEW")
    @DeleteMapping("/delete-many")
    public ApiResponse<String> deleteMany(@RequestBody List<Integer> deleteList) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        reviewService.deleteList(deleteList);
        apiResponse.setSuccess(HttpStatus.OK);
        apiResponse.setMessage("Xóa thành công.");
        return apiResponse;
    }
}

/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import ntduong.movieappserver.payload.ApiResponse;
import ntduong.movieappserver.dto.FavoriteDTO;
import ntduong.movieappserver.service.IFavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api("FAVORITE API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {

    private final IFavoriteService favoriteService;

    @ApiOperation("ADD MOVIE TO FAVORITE LIST")
    @PostMapping("/")
    public ApiResponse<String> insertToList(@RequestBody FavoriteDTO favoriteDTO) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        try {
            favoriteService.add(favoriteDTO);
            apiResponse.setSuccess(HttpStatus.CREATED);
            apiResponse.setMessage("Thêm thành công.");
        } catch (Exception e) {
            apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @ApiOperation("GET FAVORITE LIST BY USERID")
    @GetMapping("/{userId}")
    public ApiResponse<List<FavoriteDTO>> getList(@PathVariable int userId) {
        ApiResponse<List<FavoriteDTO>> apiResponse = new ApiResponse<>();
        List<FavoriteDTO> favoriteDTOList = favoriteService.findAllByUserId(userId);
        if (favoriteDTOList != null) {
            apiResponse.setSuccess(HttpStatus.OK);
            apiResponse.setResult(favoriteService.findAllByUserId(userId));
        } else {
            apiResponse.setSuccess(HttpStatus.NOT_FOUND);
            apiResponse.setMessage("Không có danh sách phim yêu thích.");
        }
        return apiResponse;
    }

    @ApiOperation("DELETE LIST FAVORITE")
    @DeleteMapping("/")
    public ApiResponse<String> deleteList(@RequestBody List<Integer> deleteList) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        try {
            favoriteService.delete(deleteList);
            apiResponse.setSuccess(HttpStatus.OK);
            apiResponse.setMessage("Xóa thành công.");
        } catch (Exception e) {
            apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @ApiOperation("UPDATE CURRENT TIME BY USERID AND MOVIEID")
    @PutMapping("/{id}")
    public ApiResponse<String> updateCurrentTime(@PathVariable int id, @RequestBody FavoriteDTO favoriteDTO) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        try {
            favoriteService.updateCurrentTime(favoriteDTO);
            apiResponse.setSuccess(HttpStatus.OK);
            apiResponse.setMessage("Cập nhật currentTime thành công.");
        } catch (Exception e) {
            apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }
}

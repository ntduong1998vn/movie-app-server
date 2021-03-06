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
import ntduong.movieappserver.security.CurrentUser;
import ntduong.movieappserver.security.UserPrincipal;
import ntduong.movieappserver.service.IFavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api("FAVORITE API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {

    private final IFavoriteService favoriteService;

    @ApiOperation("Get current time by movieId and userId")
    @GetMapping("/{movieId}/current-time")
    @PreAuthorize("hasRole('USER') or hasRole('USER_VIP') or hasRole('ADMIN')")
    public int getCurrentTime(@CurrentUser UserPrincipal userPrincipal,
                              @PathVariable int movieId) {
        return favoriteService.getCurrentTime(movieId, userPrincipal.getId());
    }

    @ApiOperation("Check movie in favorite list")
    @GetMapping("/check-exist/{movieId}")
    @PreAuthorize(("hasRole('USER') or hasRole('USER_VIP') or hasRole('ADMIN') "))
    public ApiResponse<FavoriteDTO> checkMovieInFavoritList(
            @CurrentUser UserPrincipal userPrincipal,
            @PathVariable int movieId) {
        ApiResponse<FavoriteDTO> apiResponse = new ApiResponse<>();

        FavoriteDTO favoriteDTO = favoriteService.checkExistInFavorite(movieId, userPrincipal.getId());
        if (favoriteDTO != null) {
            apiResponse.setSuccess(HttpStatus.OK);
            apiResponse.setResult(favoriteDTO);
        } else {
            apiResponse.setSuccess(HttpStatus.NOT_FOUND);
            apiResponse.setMessage("Phim không có trong danh sách yêu thích!");
        }
        return apiResponse;
    }

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

    @ApiOperation("Remove movie")
    @DeleteMapping("/remove/{movieId}")
    @PreAuthorize("hasRole('USER') or hasRole('USER_VIP') or hasRole('ADMIN')")
    public ApiResponse<String> remove(@CurrentUser UserPrincipal userPrincipal,
                                      @PathVariable int movieId) {
        favoriteService.deleteByMovieId(movieId, userPrincipal.getId());
        return new ApiResponse<>(HttpStatus.OK, "Xóa phim yêu thích thành công!");
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

}

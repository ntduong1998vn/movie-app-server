/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import ntduong.movieappserver.dto.ApiResponse;
import ntduong.movieappserver.dto.FavoriteDTO;
import ntduong.movieappserver.service.IFavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api("FAVORITE API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    private final IFavoriteService favoriteService;

    @ApiOperation("ADD MOVIE TO FAVORITE LIST")
    @PostMapping("/")
    public ApiResponse<String> insertToList(@RequestBody FavoriteDTO favoriteDTO) {
        ApiResponse<String> apiResponse = new ApiResponse<>();

        try {

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
        apiResponse.setSuccess(HttpStatus.OK);
        apiResponse.setResult(favoriteService.findAllByUserId(userId));
        return apiResponse;
    }
}

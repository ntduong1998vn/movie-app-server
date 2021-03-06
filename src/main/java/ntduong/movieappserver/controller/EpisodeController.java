/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ntduong.movieappserver.payload.ApiResponse;
import ntduong.movieappserver.dto.EpisodeDTO;
import ntduong.movieappserver.exception.ResourceNotFoundException;
import ntduong.movieappserver.service.IEpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("EPISODE APIs")
@RequestMapping("/api/episode")
public class EpisodeController {

    @Autowired
    private IEpisodeService episodeService;

    @ApiOperation("GET EPISODE LIST BY MOVIEID")
    @GetMapping("/{movieId}")
    public List<EpisodeDTO> getListEpisode(@PathVariable int movieId) {
        List<EpisodeDTO> result = episodeService.findByMovieId(movieId);
        if (result != null)
            return result;
        else
            throw new ResourceNotFoundException("Episode", "movieId", movieId);
    }


    @ApiOperation("GET EPISODE BY PARAMS {movieId,episodeId}")
    @GetMapping("/")
    public EpisodeDTO get(
            @RequestParam(value = "movieId", defaultValue = "-1") int movieId,
            @RequestParam(value = "episodeId", defaultValue = "-1") int episodeId) {
        if (movieId > 0 && episodeId > 0)
            return episodeService.findByEpisodeId(episodeId, movieId);
        else
            throw new RuntimeException("Giá trị tìm kiếm không hợp lệ.");
    }

    @ApiOperation("ADD NEW EPISODE LIST TO MOVIE")
    @PostMapping("/")
    public ApiResponse<String> addNew(@RequestBody List<EpisodeDTO> episodeDTOList) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        episodeService.addAll(episodeDTOList);
        apiResponse.setMessage("Thêm thành công.");
        apiResponse.setSuccess(HttpStatus.OK);
        return apiResponse;
    }

    @ApiOperation("GET ALL EPISODE AND SOURCE LIST")
    @GetMapping("/getAll/{movieId}")
    public List<EpisodeDTO> getAll(@PathVariable int movieId) {
        return episodeService.getAll(movieId);
    }
}

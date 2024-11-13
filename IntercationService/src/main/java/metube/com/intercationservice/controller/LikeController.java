package metube.com.intercationservice.controller;

import lombok.RequiredArgsConstructor;
import metube.com.intercationservice.domian.dto.request.LIkeVideoReq;
import metube.com.intercationservice.domian.dto.request.LikeCommitReq;
import metube.com.intercationservice.domian.dto.response.LikeCommitRes;
import metube.com.intercationservice.domian.dto.response.LikeVideoRes;
import metube.com.intercationservice.service.like.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/createVideoLike")
    public ResponseEntity<LikeVideoRes> createVideoLike(@RequestBody LIkeVideoReq like) {
        LikeVideoRes like1 = likeService.createVideoLike(like);
        return ResponseEntity.ok(like1);
    }



    @PostMapping("/createCommitLike")
    public ResponseEntity<LikeCommitRes> createCommitLike(@RequestBody LikeCommitReq like) {
        LikeCommitRes like1 = likeService.createCommitLike(like);
        return ResponseEntity.ok(like1);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<LikeVideoRes> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(likeService.findById(id));
    }


    @GetMapping("/findAllLikeByVideoId/{id}")
    public ResponseEntity<List<LikeVideoRes>> findAllLikeByVideoId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(likeService.findAllByVideoId(id));
    }


    @GetMapping("/youLikeVideos/{id}")
    public ResponseEntity<List<UUID>> youLikeVideos(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(likeService.youLikeVideos(id));
    }
}

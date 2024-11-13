package metube.com.intercationservice.service.like;

import metube.com.intercationservice.domian.dto.request.LIkeVideoReq;
import metube.com.intercationservice.domian.dto.request.LikeCommitReq;
import metube.com.intercationservice.domian.dto.response.LikeCommitRes;
import metube.com.intercationservice.domian.dto.response.LikeVideoRes;

import java.util.List;
import java.util.UUID;

public interface LikeService {
    LikeVideoRes createVideoLike(LIkeVideoReq lIkeVideoReq);
    LikeCommitRes createCommitLike(LikeCommitReq likeCommitReq);
    LikeVideoRes findById(UUID id);
    List<LikeVideoRes> findAllByVideoId(UUID videoId);

    List<UUID> youLikeVideos(UUID id);
}

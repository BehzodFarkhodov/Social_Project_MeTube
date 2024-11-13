package metube.com.intercationservice.service.commit;

import lombok.RequiredArgsConstructor;
import metube.com.intercationservice.clients.VideoServiceClient;
import metube.com.intercationservice.domian.dto.request.CommitReq;
import metube.com.intercationservice.domian.dto.response.CommitRes;
import metube.com.intercationservice.domian.dto.response.VideoResponse;
import metube.com.intercationservice.domian.entity.CommitEntity;
import metube.com.intercationservice.exception.BaseException;
import metube.com.intercationservice.repository.CommitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommitServiceImpl implements CommitService{


    private final CommitRepository commitRepository;
    private final VideoServiceClient videoServiceClient;
    @Override
    public CommitRes createCommit(CommitReq commitReq) {

        checkVideoId(commitReq.getVideoId());

        CommitEntity build = CommitEntity.builder()
                .userId(commitReq.getUserId())
                .comment(commitReq.getComment())
                .videoId(commitReq.getVideoId())
                .build();
        CommitEntity save = commitRepository.save(build);

        return mapToCommitRes(save);
    }



    @Override
    public CommitRes findById(UUID id) {
        CommitEntity commit = commitRepository.findById(id)
                .orElseThrow(() -> new BaseException("Commit not found", HttpStatus.NOT_FOUND.value()));

        checkVideoId(commit.getVideoId());

        return mapToCommitRes(commit);
    }


    @Override
    public void updateCommit(UUID id, String commitStr) {
        CommitEntity commit = commitRepository.findById(id)
                .orElseThrow(() -> new BaseException("Commit not found", HttpStatus.NOT_FOUND.value()));

        checkVideoId(commit.getVideoId());

        commit.setComment(commitStr);

        commitRepository.save(commit);
    }

    @Override
    public void deleteCommit(UUID id) {
        CommitRes byId = findById(id);

        checkVideoId(byId.getVideoId());

        commitRepository.deleteById(id);
    }

    @Override
    public List<CommitRes> findByAllCommitsVideoId(UUID videoId) {

        checkVideoId(videoId);

        List<CommitEntity> commits = commitRepository.findAllByVideoId(videoId);

        return commits.stream()
                .map(this::mapToCommitRes)
                .collect(Collectors.toList());
    }



    // map qilish u-n kk
    private CommitRes mapToCommitRes(CommitEntity commitEntity) {
        CommitRes commitRes = new CommitRes();
        commitRes.setUserId(commitEntity.getUserId());
        commitRes.setComment(commitEntity.getComment());
        commitRes.setVideoId(commitEntity.getVideoId());
        commitRes.setLikes(commitEntity.getLikes());
        return commitRes;
    }


    private void checkVideoId(UUID videoId) {
        VideoResponse videoResponse = videoServiceClient.getVideo(videoId);
        if (videoResponse == null) {
            throw new BaseException("Video not found", HttpStatus.NOT_FOUND.value());
        }
    }

    public void deleteVideoAllCommits(UUID videoId) {
        commitRepository.deleteByVideoId(videoId);
    }
}

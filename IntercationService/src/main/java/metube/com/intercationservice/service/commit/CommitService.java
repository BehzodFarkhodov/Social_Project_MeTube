package metube.com.intercationservice.service.commit;

import metube.com.intercationservice.domian.dto.request.CommitReq;
import metube.com.intercationservice.domian.dto.response.CommitRes;

import java.util.List;
import java.util.UUID;

public interface CommitService {
    CommitRes createCommit(CommitReq commitReq);
    CommitRes findById(UUID id);
    void updateCommit(UUID id, String commitReq);
    void deleteCommit(UUID id);
    List<CommitRes> findByAllCommitsVideoId(UUID videoId);
}

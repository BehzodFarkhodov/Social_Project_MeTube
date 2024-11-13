package metube.com.intercationservice.service.report;

import lombok.RequiredArgsConstructor;
import metube.com.intercationservice.clients.VideoServiceClient;
import metube.com.intercationservice.domian.dto.request.ReportReq;
import metube.com.intercationservice.domian.dto.response.ReportRes;
import metube.com.intercationservice.domian.dto.response.VideoResponse;
import metube.com.intercationservice.domian.entity.ReportEntity;
import metube.com.intercationservice.exception.BaseException;
import metube.com.intercationservice.repository.ReportRepository;
import metube.com.intercationservice.service.commit.CommitServiceImpl;
import metube.com.intercationservice.service.like.LikeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
    private final ReportRepository reportRepository;
    private final VideoServiceClient videoServiceClient;
    private final LikeServiceImpl likeServiceImpl;
    private final CommitServiceImpl commitServiceImpl;

    @Override
    public ReportRes createReport(ReportReq reportReq) {

        checkUserReport(reportReq);

        checkVideoId(reportReq.getVideoId());

        checkReport(reportReq.getVideoId());

        ReportEntity build = ReportEntity.builder()
                .userId(reportReq.getUserId())
                .videoId(reportReq.getVideoId())
                .type(reportReq.getType())
                .build();
        ReportEntity save = reportRepository.save(build);
        return mapToReportRes(save);
    }

    private void checkUserReport(ReportReq reportReq) {
        reportRepository.findByVideoIdAndUserId(reportReq.getVideoId(), reportReq.getUserId())
                .ifPresent(report -> {
                    throw new RuntimeException("User has already reported this video");
                });
    }


    @Override
    public ReportEntity findReportById(UUID id) {
        ReportEntity reportNotFound = reportRepository.findById(id)
                .orElseThrow(() -> new BaseException("Report not found", HttpStatus.NOT_FOUND.value()));

        checkVideoId(reportNotFound.getVideoId());

        return reportNotFound;
    }

    @Override
    public List<ReportRes> findAllByRepostsByVideoId(UUID videoId) {

        checkVideoId(videoId);

        List<ReportEntity> list = reportRepository.findAllByVideoId(videoId);
        return list.stream()
                .map(this::mapToReportRes)
                .collect(Collectors.toList());
    }

    private void checkReport(UUID videoId) {
        List<ReportEntity> list = reportRepository.findAllByVideoId(videoId);
        if(list.size() >= 5){

            likeServiceImpl.deleteVideoLikes(videoId);

            commitServiceImpl.deleteVideoAllCommits(videoId);

            videoServiceClient.delete(videoId);
        }
    }


    private ReportRes mapToReportRes(ReportEntity reportEntity) {
        ReportRes reportRes = new ReportRes();
        reportRes.setUserId(reportEntity.getUserId());
        reportRes.setVideoId(reportEntity.getVideoId());
        reportRes.setType(reportEntity.getType());
        return reportRes;
    }

    private void checkVideoId(UUID videoId) {
        VideoResponse videoResponse = videoServiceClient.getVideo(videoId);
        if (videoResponse == null) {
            throw new BaseException("Video not found", HttpStatus.NOT_FOUND.value());
        }
    }
}
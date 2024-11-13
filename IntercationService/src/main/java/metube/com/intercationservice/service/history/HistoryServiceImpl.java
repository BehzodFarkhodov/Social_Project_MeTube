package metube.com.intercationservice.service.history;

import lombok.RequiredArgsConstructor;
import metube.com.intercationservice.clients.VideoServiceClient;
import metube.com.intercationservice.domian.dto.request.HistoryReq;
import metube.com.intercationservice.domian.dto.response.HistoryRes;
import metube.com.intercationservice.domian.dto.response.VideoResponse;
import metube.com.intercationservice.domian.entity.HistoryEntity;
import metube.com.intercationservice.exception.BaseException;
import metube.com.intercationservice.repository.HistoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final VideoServiceClient videoServiceClient;

    @Override
    public HistoryRes createHistory(HistoryReq historyReq) {

        checkVideoId(historyReq.getVideoId());

        // Agar foydalanuvchi bu videoni oldin ko'rgan bo'lsa, exception tashlash
        historyRepository.findByUserIdAndVideoId(historyReq.getUserId(), historyReq.getVideoId())
                .ifPresent(history -> {
                    throw new BaseException("This video was previously archived", HttpStatus.ALREADY_REPORTED.value());
                });

        // Agar videoni oldin ko'rmagan bo'lsa, yangisini yaratib saqlash
        HistoryEntity newHistory = HistoryEntity.builder()
                .userId(historyReq.getUserId())
                .videoId(historyReq.getVideoId())
                .watchedTime(historyReq.getWatchedTime())
                .build();

        HistoryEntity save = historyRepository.save(newHistory);

        return mapToHistoryRes(save);
    }






    @Override
    public HistoryRes findHistoryById(UUID id) {
        HistoryEntity history = historyRepository.findById(id)
                .orElseThrow(() -> new BaseException("History not found", HttpStatus.NOT_FOUND.value()));

        checkVideoId(history.getVideoId());

         return mapToHistoryRes(history);
    }



    @Override
    public HistoryRes deleteHistory(UUID id) {
        HistoryRes historyById = findHistoryById(id);

        checkVideoId(historyById.getVideoId());

        historyRepository.deleteById(id);
        return historyById;
    }

    @Override
    public List<HistoryRes> findAllHistoryByUserId(UUID userId) {
        List<HistoryEntity> list = historyRepository.findAllByUserId(userId);

        return list.stream()
                .map(this::mapToHistoryRes)
                .collect(Collectors.toList());
    }


    private HistoryRes mapToHistoryRes(HistoryEntity historyEntity) {
        HistoryRes historyRes = new HistoryRes();

        historyRes.setUserId(historyEntity.getUserId());
        historyRes.setVideoId(historyEntity.getVideoId());
        historyRes.setWatchedTime(historyEntity.getWatchedTime());
        return historyRes;
    }

    private void checkVideoId(UUID videoId) {
        VideoResponse videoResponse = videoServiceClient.getVideo(videoId);
        if (videoResponse == null) {
            throw new BaseException("Video not found", HttpStatus.NOT_FOUND.value());
        }
    }
}

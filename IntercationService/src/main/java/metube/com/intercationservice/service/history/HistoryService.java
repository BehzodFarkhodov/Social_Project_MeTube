package metube.com.intercationservice.service.history;

import metube.com.intercationservice.domian.dto.request.HistoryReq;
import metube.com.intercationservice.domian.dto.response.HistoryRes;

import java.util.List;
import java.util.UUID;

public interface HistoryService {
    HistoryRes createHistory(HistoryReq historyReq);
    HistoryRes findHistoryById(UUID id);
    HistoryRes deleteHistory(UUID id);
    List<HistoryRes> findAllHistoryByUserId(UUID userId);
}

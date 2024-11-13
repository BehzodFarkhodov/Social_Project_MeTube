package metube.com.intercationservice.service.report;


import metube.com.intercationservice.domian.dto.request.ReportReq;
import metube.com.intercationservice.domian.dto.response.ReportRes;
import metube.com.intercationservice.domian.entity.ReportEntity;

import java.util.List;
import java.util.UUID;

public interface ReportService {
    ReportRes createReport(ReportReq reportReq);
    ReportEntity findReportById(UUID id);
    List<ReportRes> findAllByRepostsByVideoId(UUID videoId);
}

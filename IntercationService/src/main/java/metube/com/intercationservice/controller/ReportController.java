package metube.com.intercationservice.controller;

import lombok.RequiredArgsConstructor;
import metube.com.intercationservice.domian.dto.request.ReportReq;
import metube.com.intercationservice.domian.dto.response.ReportRes;
import metube.com.intercationservice.domian.entity.ReportEntity;
import metube.com.intercationservice.service.report.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/create")
    public ResponseEntity<ReportRes> create(@RequestBody ReportReq report) {
        ReportRes report1 = reportService.createReport(report);
        return ResponseEntity.ok(report1);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ReportEntity> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(reportService.findReportById(id));
    }


    @GetMapping("/findAllByRepostsByVideoId/{id}")
    public ResponseEntity<List<ReportRes>> findAllByRepostsByVideoId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(reportService.findAllByRepostsByVideoId(id));
    }
}

package metube.com.intercationservice.controller;

import lombok.RequiredArgsConstructor;
import metube.com.intercationservice.domian.dto.request.HistoryReq;
import metube.com.intercationservice.domian.dto.response.HistoryRes;
import metube.com.intercationservice.service.history.HistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @PostMapping("/create")
    public ResponseEntity<HistoryRes> create(@RequestBody HistoryReq history) {
        HistoryRes history1 = historyService.createHistory(history);
        return ResponseEntity.ok(history1);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<HistoryRes> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(historyService.findHistoryById(id));
    }

    @GetMapping("/findAllHistoryByUserId/{id}")
    public ResponseEntity<List<HistoryRes>> findAllHistoryByUserId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(historyService.findAllHistoryByUserId(id));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        historyService.deleteHistory(id);
        return ResponseEntity.noContent().build();
    }
}

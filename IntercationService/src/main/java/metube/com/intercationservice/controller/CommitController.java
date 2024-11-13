package metube.com.intercationservice.controller;

import lombok.RequiredArgsConstructor;
import metube.com.intercationservice.domian.dto.request.CommitReq;
import metube.com.intercationservice.domian.dto.response.CommitRes;
import metube.com.intercationservice.service.commit.CommitServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/commit")
@RequiredArgsConstructor
public class CommitController {
    private final CommitServiceImpl commitService;

    @PostMapping("/create")
    public ResponseEntity<CommitRes> create(@RequestBody CommitReq commitReq) {
        CommitRes commitRes = commitService.createCommit(commitReq);
        return ResponseEntity.ok(commitRes);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<CommitRes> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(commitService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestParam("commit") String commit) {
        commitService.updateCommit(id, commit);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/findByAllCommitsVideoId/{id}")
    public ResponseEntity<List<CommitRes>> findByAllCommitsVideoId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(commitService.findByAllCommitsVideoId(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        commitService.deleteCommit(id);
        return ResponseEntity.noContent().build();
    }

}

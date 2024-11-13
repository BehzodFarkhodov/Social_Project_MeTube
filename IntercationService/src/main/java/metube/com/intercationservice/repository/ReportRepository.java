package metube.com.intercationservice.repository;

import metube.com.intercationservice.domian.dto.response.ReportRes;
import metube.com.intercationservice.domian.entity.ReportEntity;
import metube.com.intercationservice.domian.enums.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, UUID> {
    List<ReportEntity> findAllByVideoId(UUID videoId);

    Optional<ReportEntity> findByVideoIdAndUserId(UUID videoId, UUID userId);
}

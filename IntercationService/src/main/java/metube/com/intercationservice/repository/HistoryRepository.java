package metube.com.intercationservice.repository;

import metube.com.intercationservice.domian.dto.response.HistoryRes;
import metube.com.intercationservice.domian.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, UUID> {
    Optional<HistoryEntity> findByUserIdAndVideoId(UUID userId, UUID videoId);

    List<HistoryEntity> findAllByUserId(UUID userId);
}

package metube.com.intercationservice.repository;

import metube.com.intercationservice.domian.dto.response.CommitRes;
import metube.com.intercationservice.domian.entity.CommitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommitRepository extends JpaRepository<CommitEntity, UUID> {
    List<CommitEntity> findAllByVideoId(UUID videoId);

    @Modifying
    @Query("DELETE FROM CommitEntity c WHERE c.videoId = :videoId")
    void deleteByVideoId(@Param("videoId") UUID videoId);

}

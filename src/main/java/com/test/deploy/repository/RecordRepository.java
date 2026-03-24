package com.test.deploy.repository;

import com.test.deploy.model.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {

    List<RecordEntity> findByTitleContainingIgnoreCase(String title);

    List<RecordEntity> findByPurposeContainingIgnoreCase(String purpose);
}

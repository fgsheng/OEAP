package com.oeap.backend.infrastructure.expert;

import com.oeap.backend.domain.expert.ExpertIntakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertIntakeRepository extends JpaRepository<ExpertIntakeEntity, String> {
}

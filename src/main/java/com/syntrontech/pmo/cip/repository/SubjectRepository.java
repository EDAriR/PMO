package com.syntrontech.pmo.cip.repository;

import com.syntrontech.pmo.model.common.ModelStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import com.syntrontech.pmo.cip.model.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

    Optional<Subject> findByIdAndTenantIdAndStatusNot(String id, String tenantId, ModelStatus status);
    Optional<Subject> findByIdAndUserIdAndTenantIdAndStatusNot(String id, String userId, String tenantId, ModelStatus status);
    List<Subject> findByUserIdAndStatusNot(String userId, ModelStatus status);
    List<Subject> findByUnitIdAndTenantIdAndStatusNot(String unitId, String tenantId, ModelStatus status);
    List<Subject> findByStatusNot(ModelStatus status);
}

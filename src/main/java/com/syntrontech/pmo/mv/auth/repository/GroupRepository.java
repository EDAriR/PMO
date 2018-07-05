package com.syntrontech.pmo.mv.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.syntrontech.auth.model.Group;
import com.syntrontech.auth.model.common.ModelStatus;

public interface GroupRepository extends CrudRepository<Group, Long> {

	Optional<Group> findByIdAndTenantIdAndStatusNot(String id, String tenantId, ModelStatus status);

	List<Group> findByTenantIdAndStatusNot(String tenantId, ModelStatus status);
	
	List<Group> findByStatusNot(ModelStatus status);
	
	// 查詢陣列欄位中擁有相同userId的Group
	@Modifying
	@Query(nativeQuery = true, value = "select * from groups g where :userId = ANY(g.user_ids) and :status != g.status and :tenantId = g.tenant_id")
	List<Group> findByUserIdAndStatusNotAndTenantId(@Param("userId") String userId, @Param("status") String status, @Param("tenantId") String tenantId);

	// 更改Group的userId欄位
	@Modifying
	@Transactional(readOnly = false)
	@Query(nativeQuery = true, value = "update groups g set user_ids = array_remove(user_ids, :userId) where :status != g.status and :tenantId = g.tenant_id")
	int deleteUserByStatusNotAndTenantId(@Param("userId") String userId, @Param("status") String status, @Param("tenantId") String tenantId);
}

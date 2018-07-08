package com.syntrontech.pmo.auth.repository;

import java.util.List;
import java.util.Optional;

import com.syntrontech.pmo.auth.model.Role;
import com.syntrontech.pmo.model.common.ModelStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Optional<Role> findByIdAndTenantIdAndStatusNot(String id, String tenantId, ModelStatus status);

	List<Role> findByTenantIdAndStatusNot(String tenantId, ModelStatus status);
	
	List<Role> findByStatusNot(ModelStatus status);

	// 查詢哪些角色的權限不全包含要查詢的權限裡
	@Modifying
	@Query(nativeQuery = true, value = "select * from role r where not r.permission_ids <@ :permissionIds \\:\\:varchar[]"
										+"and :status != r.status"
										+" and :tenantId = r.tenant_id")
	List<Role> findByPermissionIdsAndStatusNotAndTenantId(@Param("permissionIds") String permissionIds
            , @Param("status") String status, @Param("tenantId") String tenantId);

}

package com.syntrontech.pmo.mv.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.syntrontech.auth.model.User;
import com.syntrontech.auth.model.common.ModelUserStatus;

public interface UserRepository extends CrudRepository<User, Long>{
	
	Optional<User> findByIdAndStatusNot(String id, ModelUserStatus status);
	
	Optional<User> findByIdAndTenantIdAndStatusNot(String id, String tenantId, ModelUserStatus status);
	
	List<User> findByTenantIdAndStatusNot(String tenantId, ModelUserStatus status);
	
	List<User> findByStatusNot(ModelUserStatus status);
	
	//查詢陣列欄位中擁有相同card的User
	@Modifying
	@Query(nativeQuery = true, value = "select * from users us where :card = ANY(us.cards) and :status != us.status")
	List<User> findByCardAndStatusNot(@Param("card") String email, @Param("status") String status);
	
	//查詢陣列欄位中擁有相同email的User
	@Modifying
	@Query(nativeQuery = true, value = "select * from users us where :email = ANY(us.emails) and :status != us.status")
	List<User> findByEmailAndStatusNot(@Param("email") String email, @Param("status") String status);
	
	//查詢陣列欄位中擁有相同mobilcePhone的User
	@Modifying
	@Query(nativeQuery = true, value = "select * from users us where :mobilePhone = ANY(us.mobilephones) and :status != us.status")
	List<User> findByMobilePhoneAndStatusNot(@Param("mobilePhone") String mobilePhone, @Param("status") String status);
	
	//查詢陣列欄位中擁有相同role的User
	@Modifying
	@Query(nativeQuery = true, value = "select * from users us where :roleId = ANY(us.role_ids) and :status != us.status and :tenantId = us.tenant_id")
	List<User> findByRoleIdAndStatusNotAndTenantId(@Param("roleId") String roleId, @Param("status") String status, @Param("tenantId") String tenantId);
	
	//更改User的role欄位
	@Modifying
	@Transactional(readOnly = false)
	@Query(nativeQuery = true, value = "update users us set role_ids = array_remove(role_ids, :roleId) where :status != us.status and :tenantId = us.tenant_id")
	int deleteRoleByStatusNotAndTenantId(@Param("roleId") String roleId, @Param("status") String status, @Param("tenantId") String tenantId);
	
	//查詢陣列欄位中擁有相同unit的User
	@Modifying
	@Query(nativeQuery = true, value = "select * from users us where :unitId = ANY(us.unit_ids) and :status != us.status and :tenantId = us.tenant_id")
	List<User> findByUnitIdAndStatusNotAndTenantId(@Param("unitId") String unitId, @Param("status") String status, @Param("tenantId") String tenantId);
	
	//更改User的unit欄位
	@Modifying
	@Transactional(readOnly = false)
	@Query(nativeQuery = true, value = "update users us set unit_ids = array_remove(unit_ids, :unitId) where :status != us.status and :tenantId = us.tenant_id")
	int deleteUnitByStatusNotAndTenantId(@Param("unitId") String unitId, @Param("status") String status, @Param("tenantId") String tenantId);
		
}

package com.syntrontech.pmo.syncare1.repository;

import com.syntrontech.pmo.syncare1.model.entity.AreaCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AreaCodeRepository extends JpaRepository<AreaCode, Long> {
	public List<AreaCode> queryAreaCodes(String areaCode);

	List<String> queryAreaNames(String areaCode);
}

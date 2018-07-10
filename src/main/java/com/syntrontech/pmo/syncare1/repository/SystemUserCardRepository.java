package com.syntrontech.pmo.syncare1.repository;

import com.syntrontech.pmo.syncare1.model.entity.SystemUserCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SystemUserCardRepository extends JpaRepository<SystemUserCard, Long> {
	public Optional<SystemUserCard> getByCardId(String cardId);
}

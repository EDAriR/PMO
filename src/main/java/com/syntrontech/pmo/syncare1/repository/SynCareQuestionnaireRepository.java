package com.syntrontech.pmo.syncare1.repository;

import com.syntrontech.pmo.syncare1.model.SynCareQuestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface SynCareQuestionnaireRepository extends JpaRepository<SynCareQuestionnaire, Long> {
	public Optional<SynCareQuestionnaire> getByName(String name);

	public List<SynCareQuestionnaire> find();
}

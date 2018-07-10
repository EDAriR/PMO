package com.syntrontech.pmo.syncare1.repository;


import com.syntrontech.pmo.syncare1.model.entity.SynCareQuestionnaireAnswers;
import com.syntrontech.pmo.syncare1.model.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SynCareQuestionnaireAnswersRepository extends JpaRepository<SynCareQuestionnaireAnswers, Long> {

	public void removeOldAnswers(SystemUser user);
}

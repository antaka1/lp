package ro.lsacbucuresti.lanpartyquiz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.lsacbucuresti.lanpartyquiz.model.Question;
import ro.lsacbucuresti.lanpartyquiz.model.QuestionSubmit;
import ro.lsacbucuresti.lanpartyquiz.model.User;
import ro.lsacbucuresti.lanpartyquiz.service.QuestionService;

/**
 * Created by cristi on 14 - October - 2017
 */
@Repository
public interface QuestionSubmitRepository extends CrudRepository<QuestionSubmit,Long> {

    QuestionSubmit findByQuestionAndUser(Question question, User user);

}

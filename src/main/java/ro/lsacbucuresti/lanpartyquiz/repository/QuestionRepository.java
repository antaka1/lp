package ro.lsacbucuresti.lanpartyquiz.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.lsacbucuresti.lanpartyquiz.model.Question;

import java.util.Date;
import java.util.List;

/**
 * Created by cristi on 05 - October - 2017
 */
@Repository
public interface QuestionRepository extends CrudRepository<Question,Long> {

    @Query(" from Question q where DATE(q.date) = :date")
    List<Question> findByDate(@Param("date") Date date);

}

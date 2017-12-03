package ro.lsacbucuresti.lanpartyquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.lsacbucuresti.lanpartyquiz.model.Question;
import ro.lsacbucuresti.lanpartyquiz.model.QuestionSubmit;
import ro.lsacbucuresti.lanpartyquiz.model.User;
import ro.lsacbucuresti.lanpartyquiz.repository.QuestionRepository;
import ro.lsacbucuresti.lanpartyquiz.repository.QuestionSubmitRepository;
import ro.lsacbucuresti.lanpartyquiz.repository.UserRepository;

import java.util.Date;

/**
 * Created by cristi on 14 - October - 2017
 */
@Service
public class QuestionSubmitService {

    @Autowired
    private QuestionSubmitRepository questionSubmitRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public boolean hasAlreadyAnswer(String username, Long questionId){
        User user = userRepository.findByUsername(username);
        Question question = questionRepository.findOne(questionId);
        QuestionSubmit questionSubmit = questionSubmitRepository.findByQuestionAndUser(question,user);
        return questionSubmit != null;
    }

    public boolean userStartsAnswering(String username, Long questionId){
        if(hasAlreadyAnswer(username,questionId)){
            return false;
        }
        User user = userRepository.findByUsername(username);
        Question question = questionRepository.findOne(questionId);
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setQuestion(question);
        questionSubmit.setUser(user);
        questionSubmit.setShownAt(new Date());
        try{
            questionSubmitRepository.save(questionSubmit);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}

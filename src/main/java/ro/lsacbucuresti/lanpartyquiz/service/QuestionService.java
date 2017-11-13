package ro.lsacbucuresti.lanpartyquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ro.lsacbucuresti.lanpartyquiz.dto.AddQuestionDTO;
import ro.lsacbucuresti.lanpartyquiz.dto.QuestionDTO;
import ro.lsacbucuresti.lanpartyquiz.model.Question;
import ro.lsacbucuresti.lanpartyquiz.model.QuestionSubmit;
import ro.lsacbucuresti.lanpartyquiz.model.User;
import ro.lsacbucuresti.lanpartyquiz.model.UserPoints;
import ro.lsacbucuresti.lanpartyquiz.repository.QuestionRepository;
import ro.lsacbucuresti.lanpartyquiz.repository.QuestionSubmitRepository;
import ro.lsacbucuresti.lanpartyquiz.repository.UserPointsRepository;
import ro.lsacbucuresti.lanpartyquiz.repository.UserRepository;

import javax.json.Json;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by cristi on 05 - October - 2017
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserPointsService userPointsService;

    @Autowired
    private QuestionSubmitService questionSubmitService;

    @Autowired
    private QuestionSubmitRepository questionSubmitRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPointsRepository userPointsRepository;


    public QuestionDTO findOne(Long id){
        Question question = questionRepository.findOne(id);
        if(question != null){
            return QuestionDTO.fromQuestion(question);
        }
        return  null;
    }

    public QuestionDTO findByDate(Date date){
        List<Question> questions = (List<Question>) questionRepository.findByDate(date);
        List<Question> questionFilter = questions.stream().filter(q -> q.getDate().getHours() == date.getHours()).collect(toList());
        if(questionFilter != null && !questionFilter.isEmpty()) {
            Question question = questionFilter.get(0);
            QuestionDTO questionDTO = new QuestionDTO();
            boolean expired = date.getTime() - question.getDate().getTime() >= question.getMinutesToRespond() * 60 * 1000;
            String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            boolean hasAnswer = questionSubmitService.hasAlreadyAnswer(username, question.getId());
            if(expired){
                questionDTO.setExpired(true);
                return questionDTO;
            }
            if(hasAnswer){
                questionDTO.setHasAlreadyAnswer(true);
                return questionDTO;
            }
            return QuestionDTO.fromQuestion(question);
        }
        return null;
    }

    public String submitAnswer(Long questionId, String response, String userName){
        Question question = questionRepository.findOne(questionId);
        User user = userRepository.findByUsername(userName);
        //TODO check response time
        QuestionSubmit questionSubmit = questionSubmitRepository.findByQuestionAndUser(question,user);
        if(questionSubmit == null ){
            return Json.createObjectBuilder()
                    .add("status", "wrong")
                    .add("message", "Ai incercat sa pacalesti serverul ... pacat")
                    .build().toString();
        }
        if(new Date().getTime() - questionSubmit.getShownAt().getTime() >= question.getNrOfSeconds()*1000){
            questionSubmit.setSubmittedAt(new Date());
            questionSubmitRepository.save(questionSubmit);
            return Json.createObjectBuilder()
                    .add("status", "wrong")
                    .add("message", "Ai incercat sa pacalesti timerul ... pacat")
                    .build().toString();
        }
        questionSubmit.setSubmittedAt(new Date());
        questionSubmitRepository.save(questionSubmit);
        if(isCorrect(response,question.getCorrectAnswer())){
            userPointsService.addPoints(userName,question.getNrOfPoints(),questionId);
            return Json.createObjectBuilder()
                    .add("status", "success")
                    .add("message", "Raspunsul tau a fost inregistrat si ai acumulat "+question.getNrOfPoints()+ " puncte")
                    .build()
                    .toString();
        } else {
            userPointsService.addPoints(userName,0,questionId);
            return Json.createObjectBuilder()
                    .add("status", "wrong")
                    .add("message", "Raspunsul tau a fost inregistrat. Din pacate nu a fost raspunsul corect :( ")
                    .build()
                    .toString();
        }

    }

    private boolean isCorrect(String givenAnswer, String correctAnswer) {
        return !givenAnswer.isEmpty() && correctAnswer.equals(givenAnswer);
    }

    public void timeExpired(Long questionId, String username) {
        User user = userRepository.findByUsername(username);
        Question question = questionRepository.findOne(questionId);
        QuestionSubmit questionSubmit = questionSubmitRepository.findByQuestionAndUser(question,user);
        if(questionSubmit != null){
            questionSubmit.setSubmittedAt(new Date());
            questionSubmitRepository.save(questionSubmit);
            UserPoints userPoints = new UserPoints();
            userPoints.setQuestion(question);
            userPoints.setUser(user);
            userPoints.setPoints(0);
            userPointsRepository.save(userPoints);
        }
    }

    public String addQuestion(AddQuestionDTO questionDTO) {
        Question question = new Question();
        Date date = questionDTO.getDate();
        date.setHours(questionDTO.getHours());
//        LocalDate date = LocalDate.parse(questionDTO.getDate().toString());
//        date.atTime(questionDTO.getHours(),0);
//        question.setDate(java.sql.Date.valueOf(date));
        question.setDate(date);
        question.setCorrectAnswer(questionDTO.getCorrectAnswer());
        question.setFirstAnswer(questionDTO.getFirstAnswer());
        question.setMinutesToRespond(questionDTO.getMinutesToRespond());
        question.setNrOfPoints(questionDTO.getNrOfPoints());
        question.setSecondAnswer(questionDTO.getSecondAnswer());
        question.setQuestionText(questionDTO.getQuestionText());
        question.setThirdAnswer(questionDTO.getThirdAnswer());
        question.setNrOfSeconds(questionDTO.getNrOfSeconds());

        try {
            questionRepository.save(question);
            return Json.createObjectBuilder().add("status","success").build().toString();
        } catch(Exception e){
            e.printStackTrace();
            return Json.createObjectBuilder().add("status","fail").build().toString();
        }
    }
}

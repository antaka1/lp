package ro.lsacbucuresti.lanpartyquiz.dto;

import ro.lsacbucuresti.lanpartyquiz.model.Question;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by cristi on 05 - October - 2017
 */
public class QuestionDTO {

    private Long id;

    private String questionText;

    private String firstAnswer;

    private String secondAnswer;

    private String thirdAnswer;

    private Integer nrOfSeconds;

    private boolean expired;

    private boolean hasAlreadyAnswer;


    public QuestionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getFirstAnswer() {
        return firstAnswer;
    }

    public void setFirstAnswer(String firstAnswer) {
        this.firstAnswer = firstAnswer;
    }

    public String getSecondAnswer() {
        return secondAnswer;
    }

    public void setSecondAnswer(String secondAnswer) {
        this.secondAnswer = secondAnswer;
    }

    public String getThirdAnswer() {
        return thirdAnswer;
    }

    public void setThirdAnswer(String thirdAnswer) {
        this.thirdAnswer = thirdAnswer;
    }

    public Integer getNrOfSeconds() {
        return nrOfSeconds;
    }

    public void setNrOfSeconds(Integer nrOfSeconds) {
        this.nrOfSeconds = nrOfSeconds;
    }

    public static QuestionDTO fromQuestion(Question question){
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setQuestionText(question.getQuestionText());
        List<String> answers = Arrays.asList(question.getFirstAnswer(),question.getSecondAnswer(),question.getThirdAnswer());
        Collections.shuffle(answers);
        questionDTO.setFirstAnswer(answers.get(0));
        questionDTO.setSecondAnswer(answers.get(1));
        questionDTO.setThirdAnswer(answers.get(2));
        questionDTO.setNrOfSeconds(question.getNrOfSeconds());
        return questionDTO;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isHasAlreadyAnswer() {
        return hasAlreadyAnswer;
    }

    public void setHasAlreadyAnswer(boolean hasAlreadyAnswer) {
        this.hasAlreadyAnswer = hasAlreadyAnswer;
    }
}

package ro.lsacbucuresti.lanpartyquiz.dto;

import ro.lsacbucuresti.lanpartyquiz.model.Question;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by cristi on 05 - October - 2017
 */
public class AddQuestionDTO {

    private Long id;

    private String questionText;

    private String firstAnswer;

    private String secondAnswer;

    private String thirdAnswer;

    private Integer nrOfSeconds;

    private boolean expired;

    private boolean hasAlreadyAnswer;

    private Date date;

    private String correctAnswer;

    private Integer minutesToRespond;

    private Integer nrOfPoints;

    private Integer hours;


    public AddQuestionDTO() {
    }

    public Integer getNrOfPoints() {
        return nrOfPoints;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public void setNrOfPoints(Integer nrOfPoints) {
        this.nrOfPoints = nrOfPoints;
    }

    public Integer getMinutesToRespond() {
        return minutesToRespond;
    }

    public void setMinutesToRespond(Integer minutesToRespond) {
        this.minutesToRespond = minutesToRespond;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
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

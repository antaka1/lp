package ro.lsacbucuresti.lanpartyquiz.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cristi on 05 - October - 2017
 */
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private String questionText;

    private String firstAnswer;

    private String secondAnswer;

    private String thirdAnswer;

    private String correctAnswer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private Integer nrOfPoints;

    private Integer nrOfSeconds;

    private Integer minutesToRespond;

    public Question() {
    }

    public Question(String questionText, String firstAnswer, String secondAnswer, String thirdAnswer, String correctAnswer, Date date, Integer nrOfPoints, Integer nrOfSeconds, Integer minutesToRespond) {
        this.questionText = questionText;
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.correctAnswer = correctAnswer;
        this.date = date;
        this.nrOfPoints = nrOfPoints;
        this.nrOfSeconds = nrOfSeconds;
        this.minutesToRespond = minutesToRespond;
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

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNrOfPoints() {
        return nrOfPoints;
    }

    public void setNrOfPoints(Integer nrOfPoints) {
        this.nrOfPoints = nrOfPoints;
    }

    public Integer getNrOfSeconds() {
        return nrOfSeconds;
    }

    public void setNrOfSeconds(Integer nrOfSeconds) {
        this.nrOfSeconds = nrOfSeconds;
    }

    public Integer getMinutesToRespond() {
        return minutesToRespond;
    }

    public void setMinutesToRespond(Integer minutesToRespond) {
        this.minutesToRespond = minutesToRespond;
    }

}

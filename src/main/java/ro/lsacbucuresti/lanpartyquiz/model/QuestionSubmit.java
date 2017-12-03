package ro.lsacbucuresti.lanpartyquiz.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cristi on 14 - October - 2017
 */
@Entity
@Table(name = "question_submit")
public class QuestionSubmit {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private Date shownAt;

    private Date submittedAt;

    public QuestionSubmit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Date getShownAt() {
        return shownAt;
    }

    public void setShownAt(Date shownAt) {
        this.shownAt = shownAt;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }
}

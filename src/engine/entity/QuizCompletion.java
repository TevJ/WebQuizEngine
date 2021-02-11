package engine.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class QuizCompletion {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "QuizId")
    private Quiz quiz;
    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;
    private LocalDateTime completedAt;

    public QuizCompletion() {}

    public QuizCompletion(Quiz quiz, User user, LocalDateTime completedAt) {
        this.quiz = quiz;
        this.user = user;
        this.completedAt = completedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}

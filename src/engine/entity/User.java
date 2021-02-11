package engine.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.security.PublicKey;
import java.util.List;

@Entity
public class User {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String email;

    @Column(length = 60)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Quiz> quizzes;

    @OneToMany(mappedBy = "user")
    private List<QuizCompletion> quizCompletions;

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public List<QuizCompletion> getQuizCompletions() {
        return quizCompletions;
    }

    public void setQuizCompletions(List<QuizCompletion> quizCompletions) {
        this.quizCompletions = quizCompletions;
    }
}

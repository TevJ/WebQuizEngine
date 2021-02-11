package engine.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Convert(converter = IntegerListToStringConverter.class)
    private List<Integer> answer;
    private String title;
    private String text;
    @Convert(converter = StringListToStringConverter.class)
    private List<String> options;
    @ManyToOne
    @JoinColumn(name = "UserID")
    @Cascade(value = org.hibernate.annotations.CascadeType.PERSIST)
    private User user;

    public Quiz() {}

    public Quiz(List<Integer> answer, String title, String text, List<String> options) {
        this.answer = answer;
        this.title = title;
        this.text = text;
        this.options = options;
    }

    public Boolean isCorrectAnswer(List<Integer> answerIndex) {
        return answer.containsAll(answerIndex) && answerIndex.containsAll(answer);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package engine.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class QuizDto {

    private long id;
    private final List<Integer> answer;
    @NotBlank(message = "Title is required")
    private final String title;
    @NotBlank(message = "Text is required")
    private final String text;
    @NotNull
    @Size(min = 2)
    private final List<String> options;

    public QuizDto(
            @JsonProperty("title") String title,
            @JsonProperty("text") String text,
            @JsonProperty("options") List<String> options,
            @JsonProperty("answer") List<Integer> answer
    ) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = Objects.requireNonNullElseGet(answer, List::of);
    }

    public Boolean isCorrectAnswer(List<Integer> answerIndex) {
        return answer.containsAll(answerIndex) && answerIndex.containsAll(answer);
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    @JsonIgnore
    public List<Integer> getAnswer() {
        return answer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

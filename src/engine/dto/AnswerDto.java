package engine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AnswerDto {
    private final List<Integer> answer;

    public AnswerDto(@JsonProperty("answer") List<Integer> answer) {
        this.answer = answer;
    }

    public List<Integer> getAnswer() {
        return answer;
    }
}

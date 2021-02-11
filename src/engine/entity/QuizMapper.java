package engine.entity;

import engine.dto.QuizDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    public Quiz mapQuizDtoToQuiz(QuizDto quizDto) {
        return new Quiz(
                quizDto.getAnswer(),
                quizDto.getTitle(),
                quizDto.getText(),
                quizDto.getOptions()
        );
    }

    public QuizDto mapQuizToQuizDto(Quiz quiz) {
        var quizDto = new QuizDto(
                quiz.getTitle(),
                quiz.getText(),
                quiz.getOptions(),
                quiz.getAnswer()
        );
        quizDto.setId(quiz.getId());
        return quizDto;
    }
}

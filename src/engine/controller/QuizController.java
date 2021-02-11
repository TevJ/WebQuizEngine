package engine.controller;

import engine.dto.AnswerDto;
import engine.dto.AnswerFeedbackDto;
import engine.dto.QuizCompletionDto;
import engine.dto.QuizDto;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping(value = "api/quizzes", consumes = "application/json")
    public QuizDto addQuiz(@Valid @RequestBody QuizDto quizDto) {
        return quizService.createQuiz(quizDto);
    }

    @DeleteMapping("api/quizzes/{id}")
    public ResponseEntity<Object> deleteQuiz(@PathVariable long id) {
        return quizService.deleteQuiz(id);
    }

    @GetMapping("api/quizzes/{id}")
    public QuizDto getQuiz(@PathVariable int id) {
        return quizService.getQuiz(id);
    }

    @GetMapping("api/quizzes")
    public Page<QuizDto> getQuizzes(@RequestParam(defaultValue = "0") int page) {
        return quizService.getQuizzes(page);
    }

    @PostMapping("api/quizzes/{id}/solve")
    public AnswerFeedbackDto solveQuiz(@PathVariable int id, @RequestBody AnswerDto answerDto) {
        return quizService.solveQuiz(id, answerDto);
    }

    @GetMapping("api/quiz")
    public QuizDto getQuiz() {
        return quizService.getStaticQuiz();
    }

    @PostMapping("api/quiz")
    public AnswerFeedbackDto giveQuizAnswer(@RequestBody AnswerDto answerDto) {
        return quizService.solveStaticQuiz(answerDto);
    }

    @GetMapping("api/quizzes/completed")
    public Page<QuizCompletionDto> getCompletedQuizzes(@RequestParam(defaultValue = "0") int page) {
        return quizService.getQuizCompletions(page);
    }
}

package engine.service;

import engine.authentication.AuthenticationFacade;
import engine.dto.AnswerDto;
import engine.dto.AnswerFeedbackDto;
import engine.dto.QuizCompletionDto;
import engine.dto.QuizDto;
import engine.entity.Quiz;
import engine.entity.QuizCompletion;
import engine.entity.QuizMapper;
import engine.entity.User;
import engine.repository.QuizCompletionRepository;
import engine.repository.QuizRepository;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private static final int PAGE_SIZE = 10;

    private final QuizDto staticQuiz = new QuizDto(
            "The Java Logo",
            "What is depicted on the Java logo?",
            Arrays.asList("Robot","Tea leaf","Cup of coffee","Bug"),
            List.of(2)
    );

    private final QuizRepository quizRepository;

    private final UserRepository userRepository;

    private final QuizCompletionRepository quizCompletionRepository;

    private final QuizMapper quizMapper;

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public QuizService(
            QuizRepository quizRepository,
            QuizMapper quizMapper,
            UserRepository userRepository,
            AuthenticationFacade authenticationFacade,
            QuizCompletionRepository quizCompletionRepository
    ) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
        this.userRepository = userRepository;
        this.authenticationFacade = authenticationFacade;
        this.quizCompletionRepository = quizCompletionRepository;
    }

    public QuizDto createQuiz(@NonNull QuizDto quizDto) {
        User user = getLoggedInUser();
        ArrayList<Quiz> quizzes = new ArrayList<>(user.getQuizzes());
        var quiz = quizMapper.mapQuizDtoToQuiz(quizDto);
        quiz.setUser(user);
        var savedQuiz = quizRepository.save(quiz);
        quizzes.add(savedQuiz);
        user.setQuizzes(quizzes);
        userRepository.save(user);
        return quizMapper.mapQuizToQuizDto(savedQuiz);
    }

    public QuizDto getQuiz(int id) {
        return quizMapper.mapQuizToQuizDto(quizRepository.findById((long) id)
                .orElseThrow(IndexOutOfBoundsException::new));
    }

    public ResponseEntity<Object> deleteQuiz(long id) {
        User user = getLoggedInUser();
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (user.getQuizzes().stream().anyMatch(quizMatch -> quizMatch.getId() == id)) {
            ArrayList<Quiz> quizzes = new ArrayList<>(user.getQuizzes());
            quizzes.remove(quiz.get());
            user.setQuizzes(quizzes);
            userRepository.save(user);
            quizRepository.delete(quiz.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    private User getLoggedInUser() {
        String userEmail = authenticationFacade.getAuthentication().getName();
        return userRepository.findByEmail(userEmail);
    }

    public Page<QuizDto> getQuizzes(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        Page<Quiz> pagedResult = quizRepository.findAll(pageable);
        return pagedResult.map(quizMapper::mapQuizToQuizDto);
    }

    public AnswerFeedbackDto solveQuiz(int id, AnswerDto answerDto) {
        var quiz = quizRepository.findById((long) id).orElseThrow(IndexOutOfBoundsException::new);
        if (quiz.isCorrectAnswer(answerDto.getAnswer())) {
            User user = getLoggedInUser();
            QuizCompletion quizCompletion = new QuizCompletion(quiz, user, LocalDateTime.now());
            quizCompletionRepository.save(quizCompletion);
        }
        return new AnswerFeedbackDto(quiz.isCorrectAnswer(answerDto.getAnswer()));
    }

    public QuizDto getStaticQuiz() {
        return staticQuiz;
    }

    public AnswerFeedbackDto solveStaticQuiz(AnswerDto answerDto) {
        return new AnswerFeedbackDto(staticQuiz.isCorrectAnswer(answerDto.getAnswer()));
    }

    @SuppressWarnings("CodeBlock2Expr")
    public Page<QuizCompletionDto> getQuizCompletions(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("completedAt").descending());
        User user = getLoggedInUser();
        Page<QuizCompletion> pagedResult = quizCompletionRepository.findAllByUser(user, pageable);
        return pagedResult.map(quizCompletion -> {
            return new QuizCompletionDto(quizCompletion.getQuiz().getId(), quizCompletion.getCompletedAt());
        });
    }
}

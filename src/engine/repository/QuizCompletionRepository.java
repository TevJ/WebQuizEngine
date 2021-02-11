package engine.repository;

import engine.entity.QuizCompletion;
import engine.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuizCompletionRepository extends PagingAndSortingRepository<QuizCompletion, Long> {
    Page<QuizCompletion> findAllByUser(User user, Pageable pageable);
}

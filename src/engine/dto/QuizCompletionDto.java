package engine.dto;

import java.time.LocalDateTime;

public class QuizCompletionDto {
    private final long id;
    private final LocalDateTime completedAt;

    public QuizCompletionDto(long id, LocalDateTime completedAt) {
        this.id = id;
        this.completedAt = completedAt;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
}

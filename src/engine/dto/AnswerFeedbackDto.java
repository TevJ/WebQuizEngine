package engine.dto;

public class AnswerFeedbackDto {

    private static final String CORRECT_FEEDBACK = "Congratulations, you're right!";
    private static final String INCORRECT_FEEDBACK = "Wrong answer! Please, try again.";

    private final boolean success;
    private final String feedback;

    public AnswerFeedbackDto(boolean isSuccessful) {
        this.success = isSuccessful;
        if (isSuccessful) {
            feedback = CORRECT_FEEDBACK;
        } else {
            feedback = INCORRECT_FEEDBACK;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}


class WrittenQuestion {
    private String question;
    private String answer;

    public WrittenQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer.trim().toLowerCase();
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean checkAnswer(String userAnswer) {
        return userAnswer.trim().toLowerCase().equals(answer);
    }
}
package jwp.model;

import java.sql.Timestamp;

public class Answer {
    private Long answerId;
    private String writer;
    private String content;
    private Timestamp createdDate;
    private Long questionId;

    public Answer(Long answerId, String writer, String contents, Timestamp createdDate, Long questionId) {
        this.answerId = answerId;
        this.writer = writer;
        this.content = contents;
        this.createdDate = createdDate;
        this.questionId = questionId;
    }

    public Answer(String writer, String contents, Long questionId) {
        this.writer = writer;
        this.content = contents;
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Long getQuestionId() {
        return questionId;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", writer='" + writer + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", questionId=" + questionId +
                '}';
    }
}

package com.khr.justquitit;

public class FeedbackData {
    private String email;
    private String feedback;
    public FeedbackData(){}
    public FeedbackData(String email, String feedback) {
        this.email = email;
        this.feedback = feedback;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}

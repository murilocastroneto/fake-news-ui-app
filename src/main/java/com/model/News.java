package com.model;

public class News {

    private String news_id;
    private String title;
    private String desc;
    private String[] sources;
    private boolean validated;
    private boolean validationResult;
    private Vote[] votes;

    public String getId() {
        return news_id;
    }

    public void setId(String id) {
        this.news_id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return desc;
    }

    public void setDescription(String description) {
        this.desc = description;
    }

    public String[] getSources() {
        return sources;
    }

    public void setSources(String[] sources) {
        this.sources = sources;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public boolean isValidationResult() {
        return validationResult;
    }

    public void setValidationResult(boolean validationResult) {
        this.validationResult = validationResult;
    }

    public Vote[] getVotes() {
        return votes;
    }

    public void setVotes(Vote[] votes) {
        this.votes = votes;
    }
}

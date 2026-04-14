package com.aiplatform.tracking_service.model;

import java.util.List;

public class OpenAIResponse {
    private List<Choice> choices;

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}

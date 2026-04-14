package com.aiplatform.tracking_service.ai.engine;

import java.util.List;

import org.springframework.stereotype.Component;

import com.aiplatform.tracking_service.entity.DietEntity;

@Component
public class PromptBuilder {

    public String buildPrompt(List<DietEntity> entries, String question) {

        double totalCalories = 0;
        double protein = 0;
        double carbs = 0;
        double fat = 0;

        StringBuilder meals = new StringBuilder();

        for (DietEntity e : entries) {
            totalCalories += e.getCalories();
            protein += e.getProtein();
            carbs += e.getCarbs();
            fat += e.getFat();

            meals.append("- ").append(e.getFoodName()).append("\n");
        }

        return """
                You are a fitness and diet coach.

                User diet summary:
                Calories: %f
                Protein: %fg
                Carbs: %fg
                Fat: %fg

                Meals:
                %s

                User question:
                "%s"

                Give:
                - Clear answer
                - Simple explanation
                - Actionable suggestions
                """.formatted(
                totalCalories,
                protein,
                carbs,
                fat,
                meals.toString(),
                question);
    }
}
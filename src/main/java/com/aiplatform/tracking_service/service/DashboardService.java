package com.aiplatform.tracking_service.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aiplatform.tracking_service.client.AuthClient;
import com.aiplatform.tracking_service.dto.DashboardResponse;
import com.aiplatform.tracking_service.dto.DietSummary;
import com.aiplatform.tracking_service.dto.StreakSummary;
import com.aiplatform.tracking_service.dto.UserSummary;
import com.aiplatform.tracking_service.entity.DietEntity;
import com.aiplatform.tracking_service.repository.DietRepository;

@Service
public class DashboardService {
    private final DietRepository dietRepository;
    private final AuthClient authClient;

    public DashboardService(DietRepository dietRepository, AuthClient authClient) {
        this.dietRepository = dietRepository;
        this.authClient = authClient;
    }

    private int calculateStreak(List<DietEntity> entries) {
        Set<LocalDate> dates = entries.stream()
                .map(e -> e.getTimestamp().toLocalDate())
                .collect(Collectors.toSet());

        int streak = 0;
        LocalDate current = LocalDate.now();

        while (dates.contains(current)) {
            streak++;
            current = current.minusDays(1);
        }
        return streak;
    }

    private List<String> generateSuggestions(int calories, int streak) {

        List<String> suggestions = new ArrayList<>();

        if (streak >= 3) {
            suggestions.add("You're on a " + streak + "-day streak 🔥 Keep going!");
        }

        if (calories < 1500) {
            suggestions.add("You are under-eating today. Consider a balanced meal.");
        }

        if (calories > 2500) {
            suggestions.add("High calorie intake today. Try balancing meals.");
        }

        if (suggestions.isEmpty()) {
            suggestions.add("Good job! Stay consistent.");
        }

        return suggestions;
    }

    public DashboardResponse getSummary(String authHeader) {

        String userEmail = authClient.validateToken(authHeader);

        List<DietEntity> entries = dietRepository.findByUserEmail(userEmail);

        // 🔹 Today calories
        int todayCalories = entries.stream()
                .filter(e -> e.getTimestamp().toLocalDate().equals(LocalDate.now()))
                .mapToInt(DietEntity::getCalories)
                .sum();

        // 🔹 Today entries count
        int todayEntries = (int) entries.stream()
                .filter(e -> e.getTimestamp().toLocalDate().equals(LocalDate.now()))
                .count();

        // 🔹 Streak
        int dietStreak = calculateStreak(entries);

        // 🔹 AI Suggestions
        List<String> suggestions = generateSuggestions(todayCalories, dietStreak);

        // 🔹 Build response

        UserSummary user = new UserSummary();
        user.setEmail(userEmail);

        DietSummary diet = new DietSummary();
        diet.setTodayCalories(todayCalories);
        diet.setGoal(2200); // static for now
        diet.setEntriesCount(todayEntries);

        StreakSummary streaks = new StreakSummary();
        streaks.setDiet(dietStreak);

        DashboardResponse response = new DashboardResponse();
        response.setUser(user);
        response.setDiet(diet);
        response.setStreaks(streaks);
        response.setAiSuggestions(suggestions);

        return response;
    }
}

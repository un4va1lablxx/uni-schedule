package app.uni.schedule.controllers;

import app.uni.schedule.dto.SuggestionDto;
import app.uni.schedule.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SuggestionApiController {

    @Autowired
    private SuggestionService suggestionService;

    @GetMapping("/api/suggestions")
    public List<SuggestionDto> getSuggestions(@RequestParam String query, @RequestParam(defaultValue = "10") int limit) {
        if (query == null || query.trim().isEmpty()) {
            return List.of();
        }
        return suggestionService.getSuggestions(query.trim(), limit);
    }
}
package com.ahmet.demo.model;

public enum CategoryType {
    TECHNOLOGY("Technology", "Posts related to tech news, gadgets, software, etc."),
    HEALTH("Health", "Posts about health tips, medical news, fitness, etc."),
    EDUCATION("Education", "Posts about learning resources, educational news, study tips, etc."),
    ENTERTAINMENT("Entertainment", "Posts about movies, music, TV shows, etc."),
    TRAVEL("Travel", "Posts about travel destinations, tips, experiences, etc."),
    FOOD("Food", "Posts about recipes, restaurants, food reviews, etc."),
    LIFESTYLE("Lifestyle", "Posts about daily life, fashion, hobbies, etc."),
    BUSINESS("Business", "Posts about business news, entrepreneurship, market trends, etc."),
    SPORTS("Sports", "Posts about sports news, events, player profiles, etc."),
    SCIENCE("Science", "Posts about scientific discoveries, research, experiments, etc.");

    private final String name;
    private final String description;

    CategoryType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
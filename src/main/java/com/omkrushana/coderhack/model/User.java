package com.omkrushana.coderhack.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String userId;
    private String username;
    private int score = 0;

    private Set<Badges> badges = new HashSet<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Set<Badges> getBadges() {
        return badges;
    }

    public void setBadges(Badges badges) {
        this.badges.add(badges);
    }

    public void setBadges(Set<Badges> badges) {
        this.badges = badges;
    }
}
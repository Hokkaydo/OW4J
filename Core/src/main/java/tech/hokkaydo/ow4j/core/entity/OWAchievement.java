package tech.hokkaydo.ow4j.core.entity;

import tech.hokkaydo.ow4j.api.player.achievement.Achievement;
import tech.hokkaydo.ow4j.api.player.achievement.AchievementType;

import java.net.URL;

public class OWAchievement implements Achievement {

    private final AchievementType achievementType;

    private final String name;
    private final String description;
    private final URL image;

    private final boolean completed;

    public OWAchievement(AchievementType achievementType, String name, String description, URL image, boolean completed) {
        this.achievementType = achievementType;
        this.name = name;
        this.description = description;
        this.image = image;
        this.completed = completed;
    }

    @Override
    public AchievementType getAchievementType() {
        return achievementType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public URL getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "OWAchievement{" +
                "achievementType=" + achievementType +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", completed=" + completed +
                '}';
    }
}

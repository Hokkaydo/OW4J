package tech.hokkaydo.ow4j.api.player.achievement;

import java.util.Arrays;
import java.util.Optional;

public enum AchievementType {

    GENERAl("General"),
    DAMAGE("Damage"),
    TANK("Tank"),
    SUPPORT("Support"),
    MAPS("Maps"),
    SPECIAL("Special");

    private final String name;

    AchievementType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<AchievementType> getByName(String name){
        return Arrays.stream(values()).filter(achievementType -> achievementType.name.equalsIgnoreCase(name)).findFirst();
    }

    @Override
    public String toString() {
        return "AchievementType{" +
                "name='" + name + '\'' +
                '}';
    }
}

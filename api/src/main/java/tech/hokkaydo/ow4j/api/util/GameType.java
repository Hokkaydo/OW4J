package tech.hokkaydo.ow4j.api.util;

import java.util.Arrays;
import java.util.Optional;

public enum GameType {
    QUICKPLAY("Quickplay"),
    COMPETITIVE("Competitive");

    private final String name;

    GameType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<GameType> getByName(String name){
        return Arrays.stream(values()).filter(gameType -> gameType.name.equalsIgnoreCase(name)).findFirst();
    }

    @Override
    public String toString() {
        return "GameType{" +
                "name='" + name + '\'' +
                '}';
    }
}

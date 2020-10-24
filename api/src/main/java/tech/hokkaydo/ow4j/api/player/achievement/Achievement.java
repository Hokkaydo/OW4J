package tech.hokkaydo.ow4j.api.player.achievement;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Optional;

public interface Achievement {

    /**
     * Get achievement type
     *
     * @return a {@link AchievementType} representing achievement type
     * */
    AchievementType getAchievementType();

    /**
     * Get achievement name
     *
     * @return a {@link String} containing this achievement's name
     * */
    String getName();


    /**
     * Get achievement description
     *
     * @return a {@link String} containing achievement's description
     * */
    String getDescription();

    /**
     * Get achievement complete state
     *
     * @return true if this achievement is completed, false otherwise
     * */
    boolean isCompleted();

    /**
     * Get achievement illustration
     *
     * @return the illustration's {@link URL}
     * */
    URL getImage();

}

package tech.hokkaydo.ow4j.api.statistic;

import tech.hokkaydo.ow4j.api.player.hero.HeroType;
import tech.hokkaydo.ow4j.api.util.GameType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface StatisticContainer {

    /**
     * Get statistic container scope
     *
     * @return a {@link HeroType} containing statistic container's scope
     * */
    HeroType getScope();

    /**
     * Get a statistic value.
     * If the desired statistic is a time, it is returned in seconds
     *
     * @param statisticName the statistic name
     *
     * @return an {@link Optional} containing the desired statistic if found, empty otherwise
     * */
    Optional<Statistic> getStatistic(String statisticName);

    /**
     * Get statistics withing a {@link Category}
     *
     * @param category desired category
     * @return a {@link java.util.List} of {@link Statistic}
     * */
    List<Statistic> getStatistics(Category category);

    /**
     * Get game type
     * @return current {@link GameType}
     * */
    GameType getGameType();

    enum Category {
        BEST("BEST"),
        ASSISTS("ASSISTS"),
        COMBAT("COMBAT"),
        GAME("GAME"),
        AVERAGE("AVERAGE"),
        MATCH_AWARDS("MATCH AWARDS"),
        MISCELLANEOUS("MISCELLANEOUS");

        private final String name;
        Category(String name){
            this.name = name;
        }

        public static Optional<Category> getByName(String name){
            return Arrays.stream(values()).filter(v -> v.name.equalsIgnoreCase(name)).findFirst();
        }
    }
}

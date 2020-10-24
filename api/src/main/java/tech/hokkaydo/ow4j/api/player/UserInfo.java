package tech.hokkaydo.ow4j.api.player;

import tech.hokkaydo.ow4j.api.player.achievement.Achievement;
import tech.hokkaydo.ow4j.api.player.hero.Hero;
import tech.hokkaydo.ow4j.api.player.hero.HeroType;
import tech.hokkaydo.ow4j.api.statistic.StatisticContainer;
import tech.hokkaydo.ow4j.api.util.GameType;

import java.util.List;
import java.util.Map;

public interface UserInfo {


    /**
     * Get player's name
     *
     * @return a {@link String} containing player's name
     * */
    String getName();

    /**
     * Get player's battle tag
     *
     * @return a {@link String} containing player's battle tag
     * */
    String getBattleTag();

    /**
     * Get player hero information
     *
     * @param heroType: Mode of hero we want to retrieve information from.
     *
     * @throws IllegalArgumentException if the specified heroType is equal to {@link HeroType#ALL_HEROES}. Please use the {@link #getHeroes(HeroType...)} method instead.
     *
     * @return a {@link Hero} representing retrieved information
     * */
    Hero getHero(HeroType heroType) throws IllegalArgumentException;

    /**
     * Get player heroes information
     *
     * @param heroTypes: an {@link java.lang.reflect.Array} of heroes type we want to retrieve information from.  If one of specified heroTypes is equal to {@link HeroType#ALL_HEROES}, all other arguments will get ignored.
     *
     * @return a {@link Map} associating a {@link HeroType} with his {@link Hero} information representation
     * */
    Map<HeroType, Hero> getHeroes(HeroType... heroTypes);

    /**
     * Get player achievements <br>
     * Including achieved and non-achieved achievements
     *
     * @return a {@link List} of {@link Achievement} representing player achieved and non achieved achievements
     */
    List<Achievement> getAchievements();

    /**
     * Get player achieved achievements
     *
     * @return a {@link List} of {@link Achievement} representing player achieved achievements
     * */
    List<Achievement> getCompletedAchievements();

    /**
     * Get player non achieved achievements
     *
     * @return a {@link List} of {@link Achievement} representing player achieved and non achieved achievements
     * */
    List<Achievement> getNonCompletedAchievements();

    /**
     * Get the statistic containers associated with specified {@link HeroType}
     *
     * @param heroType the hero whose statistics we want to retrieve
     * @throws IllegalArgumentException if the specified heroType is equal to {@link HeroType#ALL_HEROES}. Please use the {@link #getHeroes(HeroType...)} method instead.
     *
     * @return a {@link Map} associating the specified {@link HeroType} with his {@link StatisticContainer}s stored by {@link GameType}
     * */
    Map<GameType, StatisticContainer> getStatisticContainer(HeroType heroType) throws IllegalArgumentException;

    /**
     * Get the statistic containers associated with specified {@link GameType}
     *
     * @param gameType the hero whose statistics we want to retrieve
     *
     * @return a {@link Map} associating a {@link HeroType} with his {@link StatisticContainer} for the speicifed {@link GameType}
     * */
    Map<HeroType, StatisticContainer> getStatisticContainer(GameType gameType);

    /**
     * Get player heroes statistics
     *
     * @param heroTypes an {@link java.lang.reflect.Array} of heroes whose statistics we want to retrieve.  If one of specified heroTypes is equal to {@link HeroType#ALL_HEROES}, all other arguments will get ignored.
     *
     * @return a {@link Map} containing one association {@link HeroType} - {@link StatisticContainer} per {@link GameType}
     * */
    Map<GameType, Map<HeroType, StatisticContainer>> getStatisticContainers(HeroType... heroTypes);

    /**
     * Get the statistic container matching specified {@link GameType}
     *
     * @param gameType the statistic container {@link GameType} ({@link GameType#QUICKPLAY} or {@link GameType#COMPETITIVE})
     * @param heroType the hero whose statistics we want to retrieve
     * @throws IllegalArgumentException if the specified heroType is equal to {@link HeroType#ALL_HEROES}. Please use the {@link #getHeroes(HeroType...)} method instead.
     *
     * @return the {@link StatisticContainer} matching specified {@link GameType}
     * */
    StatisticContainer getStatisticContainer(GameType gameType, HeroType heroType) throws IllegalArgumentException;

    /**
     * Get player heroes statistics for a specific {@link GameType}
     *
     * @param heroTypes an {@link java.lang.reflect.Array} of heroes whose statistics we want to retrieve.   If one of specified heroTypes is equal to {@link HeroType#ALL_HEROES}, all other arguments will get ignored.
     * @param gameType the statistic container {@link GameType} ({@link GameType#QUICKPLAY} or {@link GameType#COMPETITIVE})
     * @return a {@link Map} associating a {@link HeroType} with his {@link StatisticContainer} matching specified {@link GameType}
     * */
    Map<HeroType, StatisticContainer> getStatisticContainers(GameType gameType, HeroType... heroTypes);


}

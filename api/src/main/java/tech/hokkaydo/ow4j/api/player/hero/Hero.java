package tech.hokkaydo.ow4j.api.player.hero;

import tech.hokkaydo.ow4j.api.statistic.Statistic;
import tech.hokkaydo.ow4j.api.util.GameType;
import tech.hokkaydo.ow4j.api.util.HeroStatistic;

public interface Hero {

    /**
     * Get hero's information
     *
     * @return a {@link HeroType} containing hero's generic information
     * */
    HeroType getHeroInfo();


    /**
     * Get hero's statistic
     *
     * @param heroStatistic : specify which statistic we want to retrieve
     * @param gameType : specify from which GameType statistic has be retrieved
     * @return a {@link Statistic} containing the desired statistic
     * */
    Statistic getStatistic(HeroStatistic heroStatistic, GameType gameType);

}

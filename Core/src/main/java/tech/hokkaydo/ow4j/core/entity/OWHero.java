package tech.hokkaydo.ow4j.core.entity;

import tech.hokkaydo.ow4j.api.player.hero.Hero;
import tech.hokkaydo.ow4j.api.player.hero.HeroType;
import tech.hokkaydo.ow4j.api.statistic.Statistic;
import tech.hokkaydo.ow4j.api.util.GameType;
import tech.hokkaydo.ow4j.api.util.HeroStatistic;

import java.util.Map;

public class OWHero implements Hero {

    private final HeroType heroType;
    private final Map<GameType, Map<HeroStatistic, Statistic>> statistics;

    public OWHero(HeroType heroType, Map<GameType, Map<HeroStatistic, Statistic>> statistics){
        this.heroType = heroType;
        this.statistics = statistics;
    }

    @Override
    public HeroType getHeroInfo() {
        return heroType;
    }

    @Override
    public Statistic getStatistic(HeroStatistic statistic, GameType gameType) {
        return statistics.get(gameType).get(statistic);
    }

}

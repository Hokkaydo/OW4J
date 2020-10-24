package tech.hokkaydo.ow4j.core.entity;

import tech.hokkaydo.ow4j.api.player.hero.HeroType;
import tech.hokkaydo.ow4j.api.statistic.Statistic;
import tech.hokkaydo.ow4j.api.statistic.StatisticContainer;
import tech.hokkaydo.ow4j.api.util.GameType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OWStatisticContainer implements StatisticContainer {

    private final HeroType heroType;
    private final Map<Category, List<Statistic>> statistics;
    private final GameType gameType;

    public OWStatisticContainer(HeroType heroType, Map<Category, List<Statistic>> statistics, GameType gameType) {
        this.heroType = heroType;
        this.statistics = statistics;
        this.gameType = gameType;
    }

    @Override
    public HeroType getScope() {
        return heroType;
    }

    @Override
    public Optional<Statistic> getStatistic(String name) throws IllegalArgumentException {
        for (List<Statistic> value : statistics.values()) {
            for (Statistic statistic : value) {
                if(statistic.getName().equalsIgnoreCase(name)) return Optional.of(statistic);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Statistic> getStatistics(Category category) {
        return statistics.get(category);
    }

    @Override
    public GameType getGameType() {
        return gameType;
    }

    @Override
    public String toString() {
        return "OWStatisticContainer{" +
                "heroType=" + heroType +
                ", statistics=" + statistics +
                ", gameType=" + gameType +
                '}';
    }
}

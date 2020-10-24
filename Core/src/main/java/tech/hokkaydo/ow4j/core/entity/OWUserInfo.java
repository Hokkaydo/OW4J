package tech.hokkaydo.ow4j.core.entity;

import tech.hokkaydo.ow4j.api.player.UserInfo;
import tech.hokkaydo.ow4j.api.player.achievement.Achievement;
import tech.hokkaydo.ow4j.api.player.hero.Hero;
import tech.hokkaydo.ow4j.api.player.hero.HeroType;
import tech.hokkaydo.ow4j.api.statistic.StatisticContainer;
import tech.hokkaydo.ow4j.api.util.GameType;

import java.util.*;

public class OWUserInfo implements UserInfo {

    private final String battleTag;
    private final Map<HeroType, Hero> heroes;
    private final List<Achievement> completedAchievements;
    private final List<Achievement> nonCompletedAchievements ;
    private final Map<GameType, Map<HeroType, StatisticContainer>> statisticContainers;

    public OWUserInfo(String battleTag, Map<HeroType, Hero> heroes, List<Achievement> completedAchievements, List<Achievement> nonCompletedAchievements, Map<GameType, Map<HeroType, StatisticContainer>> statisticContainers) {
        this.battleTag = battleTag;
        this.heroes = heroes;
        this.completedAchievements = completedAchievements;
        this.nonCompletedAchievements = nonCompletedAchievements;
        this.statisticContainers = statisticContainers;
    }

    @Override
    public String getName() {
        return battleTag.split("#")[0];
    }

    @Override
    public String getBattleTag() {
        return battleTag;
    }

    @Override
    public Hero getHero(HeroType heroType) throws IllegalArgumentException {
        if(heroType.equals(HeroType.ALL_HEROES))
            throw new IllegalArgumentException("HeroType.ALL_HEROES argument not permitted here");
        return heroes.get(heroType);
    }

    @Override
    public Map<HeroType, Hero> getHeroes(HeroType... heroTypes) {
        Map<HeroType, Hero> heroes = new HashMap<>();

        if (Arrays.asList(heroTypes).contains(HeroType.ALL_HEROES)) {
            heroTypes = HeroType.values();
        }

        for (HeroType heroType : heroTypes) {
            heroes.put(heroType, this.heroes.get(heroType));
        }
        return heroes;
    }

    @Override
    public List<Achievement> getAchievements() {
        List<Achievement> achievements = new ArrayList<>(completedAchievements);
        achievements.addAll(nonCompletedAchievements);
        return achievements;
    }

    @Override
    public List<Achievement> getCompletedAchievements() {
        return completedAchievements;
    }

    @Override
    public List<Achievement> getNonCompletedAchievements() {
        return nonCompletedAchievements;
    }

    @Override
    public Map<GameType, StatisticContainer> getStatisticContainer(HeroType heroType) throws IllegalArgumentException {
        if(heroType.equals(HeroType.ALL_HEROES))
            throw new IllegalArgumentException("HeroType.ALL_HEROES argument not permitted here");
        Map<GameType, StatisticContainer> map = new HashMap<>();
        getStatisticContainers(heroType).forEach((k, v) -> {
            if(v.containsKey(heroType)) map.put(k, v.get(heroType));
        });
        return map;
    }

    @Override
    public Map<HeroType, StatisticContainer> getStatisticContainer(GameType gameType) {
        return statisticContainers.get(gameType);
    }

    @Override
    public Map<GameType, Map<HeroType, StatisticContainer>> getStatisticContainers(HeroType... heroTypes) {
        Map<GameType, Map<HeroType, StatisticContainer>> statisticContainerMap = new HashMap<>();
        for (Map.Entry<GameType, Map<HeroType, StatisticContainer>> gameTypeMapEntry : statisticContainers.entrySet()) {
            Map<HeroType, StatisticContainer> map = new HashMap<>();
            for (HeroType heroType : heroTypes) {
                map.put(heroType, gameTypeMapEntry.getValue().get(heroType));
            }
            statisticContainerMap.put(gameTypeMapEntry.getKey(), map);
        }
        return statisticContainerMap;
    }

    @Override
    public StatisticContainer getStatisticContainer(GameType gameType, HeroType heroType) throws IllegalArgumentException {
        return getStatisticContainer(heroType).get(gameType);
    }

    @Override
    public Map<HeroType, StatisticContainer> getStatisticContainers(GameType gameType, HeroType... heroTypes) {
        return getStatisticContainers(heroTypes).get(gameType);
    }

    @Override
    public String toString() {
        return "OWUserInfo{" +
                "heroes=" + heroes +
                ", completedAchievements=" + completedAchievements +
                ", nonCompletedAchievements=" + nonCompletedAchievements +
                ", statisticContainers=" + statisticContainers +
                '}';
    }
}

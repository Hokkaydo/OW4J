package tech.hokkaydo.ow4j.core.retriever;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import tech.hokkaydo.ow4j.api.player.hero.Hero;
import tech.hokkaydo.ow4j.api.player.hero.HeroType;
import tech.hokkaydo.ow4j.api.statistic.StatisticContainer;
import tech.hokkaydo.ow4j.api.util.GameType;
import tech.hokkaydo.ow4j.core.entity.OWUserInfo;
import tech.hokkaydo.ow4j.core.exceptions.OWPlayerNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class DataRetriever {

    private final Document document;

    public DataRetriever(Document document) throws OWPlayerNotFoundException{
        this.document = document;
        for (Element h1 : document.select("h1")) {
            if(h1.html().equalsIgnoreCase("Profile Not Found")) throw new OWPlayerNotFoundException("Profile Not Found. Maybe you should verify the given battle tag ?", OWPlayerNotFoundException.Cause.PROFILE_NOT_FOUND);
        }
    }

    private Map<GameType, Map<HeroType, StatisticContainer>> getStatistics(){
        Map<GameType, Map<HeroType, StatisticContainer>> statisticContainers = new HashMap<>();
        StatisticRetriever retriever = new StatisticRetriever(document);
        statisticContainers.put(GameType.QUICKPLAY, retriever.retrieveStats(GameType.QUICKPLAY));
        statisticContainers.put(GameType.COMPETITIVE, retriever.retrieveStats(GameType.COMPETITIVE));

        return statisticContainers;
    }
    private Map<HeroType, Hero> getHeroes(){
        HeroStatisticRetriever retriever = new HeroStatisticRetriever(document);
        Map<HeroType, Hero> map = new HashMap<>();
        for (GameType gameType : GameType.values()) {
            for (HeroType heroType : HeroType.values()) {
                if(heroType == HeroType.ALL_HEROES) continue;
                map.put(heroType, retriever.getHeroStatistics(heroType, gameType));
            }
        }
        return map;
    }

    public OWUserInfo retrievePlayer(String battleTag){
        AchievementRetriever achievementRetriever = new AchievementRetriever(document);
        return new OWUserInfo(
                battleTag,
                getHeroes(),
                achievementRetriever.retrieveAchievements(true, true),
                achievementRetriever.retrieveAchievements(true, false),
                getStatistics()
        );
    }

}

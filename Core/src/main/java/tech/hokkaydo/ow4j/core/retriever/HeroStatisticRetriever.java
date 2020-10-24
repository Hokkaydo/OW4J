package tech.hokkaydo.ow4j.core.retriever;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tech.hokkaydo.ow4j.api.player.hero.HeroType;
import tech.hokkaydo.ow4j.api.statistic.Statistic;
import tech.hokkaydo.ow4j.api.util.GameType;
import tech.hokkaydo.ow4j.core.entity.OWHero;
import tech.hokkaydo.ow4j.core.entity.OWStatistic;
import tech.hokkaydo.ow4j.api.util.HeroStatistic;
import tech.hokkaydo.ow4j.core.utils.StatisticParser;

import java.util.HashMap;
import java.util.Map;

public class HeroStatisticRetriever {

    private final Document document;

    HeroStatisticRetriever(Document document){
        this.document = document;
    }

    public OWHero getHeroStatistics(HeroType heroType, GameType gameType){
        Elements section = document.select("div#" + gameType.getName().toLowerCase() + " section.career-section div.progress-category");
        Map<HeroStatistic, Statistic> statistics = new HashMap<>();
        for (HeroStatistic value : HeroStatistic.values()) {
            Elements stat = section.select("[data-category-id=" + value.getId() + "]").first().children();
            statistics.put(value, new OWStatistic(value.getName(), StatisticParser.parseStatistic(stat.select("div[data-hero=" + heroType.getName() + "] + div.ProgressBar-textWrapper div.ProgressBar-description").html())));
        }
        return new OWHero(heroType, Map.of(gameType, statistics));
    }
}

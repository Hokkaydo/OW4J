package tech.hokkaydo.ow4j.core.retriever;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import tech.hokkaydo.ow4j.api.player.hero.HeroType;
import tech.hokkaydo.ow4j.api.statistic.Statistic;
import tech.hokkaydo.ow4j.api.statistic.StatisticContainer;
import tech.hokkaydo.ow4j.api.util.GameType;
import tech.hokkaydo.ow4j.core.entity.OWStatistic;
import tech.hokkaydo.ow4j.core.entity.OWStatisticContainer;
import tech.hokkaydo.ow4j.core.utils.StatisticParser;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class StatisticRetriever {

    private final Document document;
    StatisticRetriever(Document document){
        this.document = document;
    }
    Map<HeroType, StatisticContainer> retrieveStats(GameType gameType) {
        Element statDivRanked = document.select("div#" + gameType.getName().toLowerCase() + " section.career-section div.row.column").get(1);
        List<HeroType> heroTypes = new ArrayList<>();

        for (Element heroName : statDivRanked.selectFirst("select").children()) {
            HeroType.getByName(heroName.html()).ifPresent(heroTypes::add);
        }

        Map<HeroType, StatisticContainer> heroStatsRanked = new HashMap<>();
        for(Element heroStatCategory : statDivRanked.select("div[data-group-id=stats]")){
            HeroType heroType = heroTypes.remove(0);
            Map<StatisticContainer.Category, List<Statistic>> statistics = new HashMap<>();
            for(Element statCategory : heroStatCategory.children()){
                StatisticContainer.Category.getByName(statCategory.selectFirst("thead tr th h5").html()).ifPresent(category -> {
                    for(Element statisticE : statCategory.selectFirst("tbody").children()){
                        String name = statisticE.select("td").get(0).html();
                        double value = parseStat(statisticE.select("td").get(1).html());
                        Statistic statistic = new OWStatistic(name, value);
                        statistics.compute(category, (category1, statistics1) -> {
                            List<Statistic> list = new ArrayList<>(Collections.singletonList(statistic));
                            if(statistics1 != null)
                                list.addAll(statistics1);
                            return list;
                        });
                    }
                });
            }
            heroStatsRanked.put(heroType, new OWStatisticContainer(heroType, statistics, GameType.QUICKPLAY));
        }
        return heroStatsRanked;
    }

    private static double parseStat(String value){
        return StatisticParser.parseStatistic(value);
    }
}

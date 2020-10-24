package tech.hokkaydo.ow4j.core.retriever;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import tech.hokkaydo.ow4j.api.player.achievement.Achievement;
import tech.hokkaydo.ow4j.api.player.achievement.AchievementType;
import tech.hokkaydo.ow4j.core.entity.OWAchievement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AchievementRetriever {

    private final Document document;

    AchievementRetriever(Document document){
        this.document = document;
    }

    public List<Achievement> retrieveAchievements(boolean filterCompleted, boolean completed){
        List<Achievement> achievements = new ArrayList<>();

        for (Element category : document.select("section#achievements-section div[data-group-id=achievements]")) {
            String categoryID = category.attr("data-category-id");
            AchievementType.getByName(categoryID).ifPresent(achievementType -> {
                for (Element card : category.selectFirst("ul").children()) {
                    URL imageURL = null;
                    try {
                        imageURL = new URL(card.selectFirst("img").attr("src"));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    Element toolTipElement = card.selectFirst("div.tooltip-tip");

                    String name = toolTipElement.selectFirst("h6.h5").html();
                    String description = toolTipElement.selectFirst("p.h6").html();

                    boolean isCompleted = !card.selectFirst("div.achievement-card").hasClass("m-disabled");

                    if(filterCompleted){
                        if(completed!= isCompleted) continue;
                    }
                    achievements.add(new OWAchievement(achievementType, name, description, imageURL, isCompleted));
                }
            });
        }
        return achievements;
    }
}

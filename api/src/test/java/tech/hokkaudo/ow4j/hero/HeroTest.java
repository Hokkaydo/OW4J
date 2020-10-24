package tech.hokkaudo.ow4j.hero;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tech.hokkaydo.ow4j.api.player.hero.Hero;
import tech.hokkaydo.ow4j.api.player.hero.HeroType;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class HeroTest {

    private Document document;

    @Before
    public void before() throws IOException {
        document = Jsoup.connect("https://playoverwatch.com/en-us/career/pc/Hokkaydo-21576/").get();
    }

    @Test
    public void getName_shouldReturnAna(){
        Hero hero = Mockito.mock(Hero.class);

        String name = document.select("div#quickplay section.career-section div.progress-category-item div.ProgressBar-textWrapper div.ProgressBar-title").get(2).html();
        when(hero.getHeroInfo()).thenReturn(HeroType.ANA);


        assertThat(hero.getHeroInfo().getName()).isEqualTo("Ana");
    }
}

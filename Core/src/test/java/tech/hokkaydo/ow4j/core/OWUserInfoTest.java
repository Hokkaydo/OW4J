package tech.hokkaydo.ow4j.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.hokkaydo.ow4j.api.player.UserInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OWUserInfoTest {

    private UserInfo userInfo;

    @BeforeEach
    public void before(){
        userInfo = OW4J.retrieveData("Hokkaydo#21576").getPlayerInfo();
    }

    @Test
    public void when_getPlayerName_shouldReturn_Hokkaydo(){
        String name = userInfo.getName();
        assertEquals(name, "Hokkaydo");
    }
    @Test
    public void when_getBattleTag_shouldReturn_Hokkaydo21576(){
        String battleTag = userInfo.getBattleTag();
        assertEquals(battleTag, "Hokkaydo#21576");
    }
}

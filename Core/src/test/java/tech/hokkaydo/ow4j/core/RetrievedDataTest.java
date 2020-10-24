package tech.hokkaydo.ow4j.core;

import org.junit.jupiter.api.Test;
import tech.hokkaydo.ow4j.core.exceptions.OWPlayerNotFoundException;
import tech.hokkaydo.ow4j.core.retriever.RetrievedData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RetrievedDataTest {

    @Test
    public void when_retrievePlayerData_with_invalidBattleTag_retrievedData_contains_exception(){
        RetrievedData data = OW4J.retrieveData("Hokkaydo#1221");
        assertNotNull(data);
        assertNotNull(data.getException());
    }
    @Test
    public void when_errorOccurred_isPresent_returns_false() {
        RetrievedData data = OW4J.retrieveData("Hokkaydo#1221");
        assertEquals(data.getException() != null, !data.isPresent());
    }
    @Test
    public void when_retrievePlayerData_errorCause_isEqualTo_PROFIL_NOT_FOUND() {
        RetrievedData data = OW4J.retrieveData("Hokkaydo#1221");
        assertEquals(data.getException().getErrorCause(), OWPlayerNotFoundException.Cause.PROFILE_NOT_FOUND);
    }
    @Test
    public void when_retrievePlayerData_errorCause_isEqualTo_INVALID_BATTLE_TAG() {
        RetrievedData data = OW4J.retrieveData("SomethingBadMustHappen");
        assertEquals(data.getException().getErrorCause(), OWPlayerNotFoundException.Cause.INVALID_BATTLE_TAG);
    }
}

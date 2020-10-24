package tech.hokkaydo.ow4j.core;

import org.jsoup.Jsoup;
import tech.hokkaydo.ow4j.core.entity.OWUserInfo;
import tech.hokkaydo.ow4j.core.exceptions.OWPlayerNotFoundException;
import tech.hokkaydo.ow4j.core.retriever.DataRetriever;
import tech.hokkaydo.ow4j.core.retriever.RetrievedData;

import java.io.IOException;
import java.util.regex.Pattern;

public class OW4J {

    private static final Pattern BTAG_SPLITTER = Pattern.compile("#");

    /**
     * Get player matching given battleTag data
     *
     * @param battleTag : player's battleTag
     * @return an {@link OWUserInfo} representing player data
     * */
    public static RetrievedData retrieveData(String battleTag) {
        if(!battleTag.matches("[A-z]+#[0-9]{4,5}")) {
            return new RetrievedData(null, new OWPlayerNotFoundException("Invalid battleTag", OWPlayerNotFoundException.Cause.INVALID_BATTLE_TAG));
        }
        try {
            return new RetrievedData(
                    new DataRetriever(
                            Jsoup.connect(
                                    String.format(
                                            "https://playoverwatch.com/en-us/career/pc/%s-%d/",
                                            BTAG_SPLITTER.split(battleTag)[0],
                                            Integer.valueOf(BTAG_SPLITTER.split(battleTag)[1])
                                    )
                            ).get()
                    ).retrievePlayer(battleTag),
                    null
            );
        } catch (IOException e) {
            return new RetrievedData(
                    null, new OWPlayerNotFoundException(
                    "An exception occurred. Please open an issue on the Github repository (https://github.com/Hokkaydo/OW4J)",
                    OWPlayerNotFoundException.Cause.INTERNAL_ERROR
            ));
        } catch (OWPlayerNotFoundException e){
            return new RetrievedData(null, e);
        }
    }
}

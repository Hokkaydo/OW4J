package tech.hokkaydo.ow4j.core;

import org.jsoup.Jsoup;
import tech.hokkaydo.ow4j.core.exceptions.OWPlayerNotFoundException;
import tech.hokkaydo.ow4j.core.retriever.DataRetriever;
import tech.hokkaydo.ow4j.core.retriever.RetrievedData;

import java.io.IOException;
import java.util.regex.Pattern;

public class OW4J {

    private static final Pattern BTAG_SPLITTER = Pattern.compile("#");

    /**
     * Here's the API entrypoint. <br><br>
     * To use it, you can call the following method to retrieve a {@link RetrievedData} representing the status of the request. <br><br>
     * If the request returned something, you can get it through {@link RetrievedData#getUserInfo()} method.  <br>
     * If the request stopped due to an error, you can check it through {@link RetrievedData#getException()} method. <br>
     *
     * <br>
     *
     * @param battleTag : player's battleTag
     * @return an {@link RetrievedData} representing request data
     * @see RetrievedData
     */

    public static RetrievedData retrieveData(String battleTag) {
        if (!battleTag.matches("[A-z]+#[0-9]{4,5}")) {
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

package tech.hokkaydo.ow4j.core.retriever;

import tech.hokkaydo.ow4j.api.player.UserInfo;
import tech.hokkaydo.ow4j.core.exceptions.OWPlayerNotFoundException;

import java.util.function.Consumer;

public class RetrievedData {

    private final UserInfo userInfoInfo;
    private final OWPlayerNotFoundException exception;

    public RetrievedData(UserInfo userInfoInfo, OWPlayerNotFoundException exception){
        this.userInfoInfo = userInfoInfo;
        this.exception = exception;
        if(exception != null){
            exception.printStackTrace();
        }
    }

    public void ifPresent(Consumer<UserInfo> playerConsumer){
        if(userInfoInfo == null) return;
        playerConsumer.accept(userInfoInfo);
    }
    public void ifEmpty(Consumer<OWPlayerNotFoundException> exceptionConsumer){
        if(userInfoInfo != null) return;
        exceptionConsumer.accept(exception);
    }
    public void ifPresentOrElse(Consumer<UserInfo> player, Consumer<OWPlayerNotFoundException> exceptionConsumer){
        ifPresent(player);
        ifEmpty(exceptionConsumer);
    }
    public UserInfo getPlayerInfo(){
        return userInfoInfo;
    }
    public OWPlayerNotFoundException getException(){
        return exception;
    }
    public boolean isPresent() {
        return userInfoInfo != null;
    }
}

package tech.hokkaydo.ow4j.core.retriever;

import tech.hokkaydo.ow4j.api.player.UserInfo;
import tech.hokkaydo.ow4j.core.exceptions.OWPlayerNotFoundException;

import java.util.function.Consumer;

public class RetrievedData {

    private UserInfo userInfo;
    private OWPlayerNotFoundException exception;

    public RetrievedData(UserInfo userInfo, OWPlayerNotFoundException exception) {
        this.userInfo = userInfo;
        this.exception = exception;
    }

    public boolean isPresent() {
        return userInfo != null;
    }

    public void ifPresent(Consumer<UserInfo> playerConsumer) {
        if (isPresent()) return;
        playerConsumer.accept(userInfo);
    }

    public void ifEmpty(Consumer<OWPlayerNotFoundException> exceptionConsumer) {
        if (!isPresent()) return;
        exceptionConsumer.accept(exception);
    }

    public UserInfo orElse(UserInfo userInfo) {
        if (userInfo != null && !isPresent()) {
            this.userInfo = userInfo;
            this.exception = null;
        }
        return userInfo;
    }

    public void ifPresentOrElse(Consumer<UserInfo> playerConsumer, Consumer<OWPlayerNotFoundException> exceptionConsumer) {
        ifPresent(playerConsumer);
        ifEmpty(exceptionConsumer);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public OWPlayerNotFoundException getException() {
        return exception;
    }
}

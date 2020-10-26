package tech.hokkaydo.ow4j.core.retriever;

import tech.hokkaydo.ow4j.api.player.UserInfo;
import tech.hokkaydo.ow4j.core.exceptions.OWPlayerNotFoundException;

import java.util.function.Consumer;

/**
 * This class represents the request's response <br><br>
 * <p>
 * If the request succeed, the value can be accessed through {@link #getUserInfo()} method or using {@link #ifPresent(Consumer)} method. <br>
 * If the request failed, the error can be accessed through {@link #getException()} method or using {@link #ifEmpty(Consumer)} method <br><br>
 * <p>
 * This class uses some same method types as the {@link java.util.Optional} class like <br>
 * {@link java.util.Optional#ifPresent(Consumer)}, <br>
 * {@link java.util.Optional#ifPresentOrElse(Consumer, Runnable)}) or <br>
 * {@link java.util.Optional#orElse(Object)} <br><br>
 *
 * @see UserInfo
 * @see OWPlayerNotFoundException
 */
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

    /**
     * As the {@link java.util.Optional#ifPresent(Consumer)} method, ifPresent can proceed a {@link Consumer<UserInfo>}
     * if {@link #userInfo} is present
     *
     * @param playerConsumer a {@link Consumer<UserInfo>} proceeded if {@link #userInfo} is not null
     */
    public void ifPresent(Consumer<UserInfo> playerConsumer) {
        if (isPresent()) return;
        playerConsumer.accept(userInfo);
    }

    /**
     * {@link #ifPresent(Consumer)} can proceed a {@link Consumer<OWPlayerNotFoundException>} if {@link #userInfo} is null.
     *
     * @param exceptionConsumer a {@link Consumer<OWPlayerNotFoundException>} proceeded if {@link #userInfo} is null
     */
    public void ifEmpty(Consumer<OWPlayerNotFoundException> exceptionConsumer) {
        if (!isPresent()) return;
        exceptionConsumer.accept(exception);
    }

    /**
     * Replace current {@link #userInfo} value by the given one if {@link #userInfo} is null, and the parameter is not null. <br>
     * If replacement has been successful, {@link #exception} is set to null. <br>
     * Return old or new value depend on the replacement status. <br><br>
     *
     * @param userInfo {@link UserInfo} to use instead of the current one if {@link #userInfo} is null.
     * @return given {@link UserInfo} if replacement has been successful and the old one if not. <br>
     * Be careful using this method, the return value could be null.
     */
    public UserInfo orElse(UserInfo userInfo) {
        if (userInfo != null && !isPresent()) {
            this.userInfo = userInfo;
            this.exception = null;
        }
        return userInfo;
    }

    /**
     * Almost as the {@link java.util.Optional#ifPresentOrElse(Consumer, Runnable)} method, <br>
     * {@link #ifPresent(Consumer)} can proceed a {@link Consumer<UserInfo>}
     * if {@link #userInfo} is present <br>
     * or a {@link Consumer<OWPlayerNotFoundException>} if it isn't. <br><br>
     *
     * @param playerConsumer    a {@link Consumer<UserInfo>} proceeded if {@link #userInfo} is not null
     * @param exceptionConsumer a {@link Consumer<OWPlayerNotFoundException>} proceeded if {@link #userInfo} is null
     */
    public void ifPresentOrElse(Consumer<UserInfo> playerConsumer, Consumer<OWPlayerNotFoundException> exceptionConsumer) {
        ifPresent(playerConsumer);
        ifEmpty(exceptionConsumer);
    }

    /**
     * Get {@link #userInfo}. <br>
     * Be careful using this method, {@link #userInfo} could be null.
     *
     * @return current {@link #userInfo}
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * Get {@link #exception}. <br>
     * Be careful using this method, {@link #exception} could be null.
     *
     * @return current {@link #exception}
     */
    public OWPlayerNotFoundException getException() {
        return exception;
    }
}
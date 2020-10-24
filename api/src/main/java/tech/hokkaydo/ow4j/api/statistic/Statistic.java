package tech.hokkaydo.ow4j.api.statistic;

public interface Statistic {

    /**
     * Get statistic value as {@link Double}. If the statistic value is a timer, it get returned in seconds.
     *
     * @return an {@link Integer} containing the statistic value
     * */
    double getValue();

    /**
     * Get statistic name
     * @return a {@link String} containing statistic name
     * */
    String getName();
}

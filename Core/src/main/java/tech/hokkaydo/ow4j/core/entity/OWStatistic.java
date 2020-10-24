package tech.hokkaydo.ow4j.core.entity;

import tech.hokkaydo.ow4j.api.statistic.Statistic;

public class OWStatistic implements Statistic {

    private final String name;
    private final double value;

    public OWStatistic(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "OWStatistic{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}

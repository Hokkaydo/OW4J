package tech.hokkaydo.ow4j.core;

public class Main {

    public static void main(String[] args) {
        OW4J.retrieveData("Hokkaydo#21576").ifPresent(player -> System.out.println(player.getNonCompletedAchievements()));
    }

}

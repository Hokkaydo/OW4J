package tech.hokkaydo.ow4j.api.player.hero;

import java.util.Arrays;
import java.util.Optional;

public enum HeroType {

    ALL_HEROES("All Heroes"),
    ANA("Ana"),
    ASHE("Ashe"),
    BAPTISTE("Baptiste"),
    BASTION("Bastion"),
    BRIGITTE("Brigitte"),
    D_VA("D.Va"),
    DOOMFIST("Doomfist"),
    ECHO("Echo"),
    GENJI("Genji"),
    HANZO("Hanzo"),
    JUNKRAT("Junkrat"),
    LUCIO("Lúcio"),
    MCCREE("McCree"),
    MEI("Mei"),
    MERCY("Mercy"),
    MOIRA("Moira"),
    ORISA("Orisa"),
    PHARAH("Pharah"),
    REAPER("Reaper"),
    REINHARDT("Reinhardt"),
    ROADHOG("Roadhog"),
    SIGMA("Sigma"),
    SOLDIER_76("Soldier: 76"),
    SOMBRA("Sombra"),
    SYMMETRA("Symmetra"),
    TORBJORN("Torbjörn"),
    TRACER("Tracer"),
    WIDOWMAKER("Widowmaker"),
    WINSTON("Winston"),
    WRECKING_BALL("Wrecking Ball"),
    ZARYA("Zarya"),
    ZENYATTA("Zenyatta");


    private final String name;
    HeroType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<HeroType> getByName(String name){
        return Arrays.stream(values()).filter(v -> v.name.equalsIgnoreCase(name)).findFirst();
    }

    @Override
    public String toString() {
        return "HeroType{" +
                "name='" + name + '\'' +
                '}';
    }
}

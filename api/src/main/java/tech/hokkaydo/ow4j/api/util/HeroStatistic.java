package tech.hokkaydo.ow4j.api.util;

public enum HeroStatistic {

    TIME_PLAYED("Time played", "0x0860000000000021"),
    GAMES_WON("Games won", "0x0860000000000039"),
    WEAPON_ACCURACY("Weapon accuracy", "0x086000000000002F"),
    ELIMINATIONS_PER_LIFE("Eliminations per life", "0x08600000000003D2"),
    CRITICAL_HIT_ACCURACY("Critical hit accuracy", "0x08600000000003E2"),
    MULTIKILL_BEST("Multikill best", "0x0860000000000346"),
    OBJECTIVE_KILLS("Objective kills", "0x086000000000031C");

    private String name;
    private String id;
    HeroStatistic(String name, String id){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }

    @Override
    public String toString() {
        return "HeroStatistic{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

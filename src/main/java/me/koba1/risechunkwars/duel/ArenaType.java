package me.koba1.risechunkwars.duel;

public enum ArenaType {
    SOLO,
    DOUBLES,
    SQUAD;

    public static ArenaType getByName(String name) {
        for (ArenaType value : values()) {
            if(value.name().equals(name)) {
                return value;
            }
        }
        return null;
    }
}

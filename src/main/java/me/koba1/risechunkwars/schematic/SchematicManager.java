package me.koba1.risechunkwars.schematic;

import lombok.Getter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Getter
public class SchematicManager {

    private final Map<String, ArenaSchematic> schematics;

    public SchematicManager() {
        this.schematics = new HashMap<>();
    }

    public void registerSchematic(File file) {
        this.schematics.put(file.getName(), new ArenaSchematic(file));
    }
}

package me.koba1.risechunkwars;

import lombok.Getter;
import me.koba1.risechunkwars.adventure.TextBuilder;
import me.koba1.risechunkwars.duel.Arena;
import me.koba1.risechunkwars.duel.ArenaManager;
import me.koba1.risechunkwars.duel.RunningArena;
import me.koba1.risechunkwars.files.ArenaFile;
import me.koba1.risechunkwars.placeholders.ChunkwarsPlaceholder;
import me.koba1.risechunkwars.schematic.SchematicManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Map;

@Getter
public final class Main extends JavaPlugin {
    @Getter private static Main instance;

    private ChunkwarsPlaceholder placeholder;
    private ArenaManager arenaManager;
    private SchematicManager schematicManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.schematicManager = new SchematicManager();
        this.arenaManager = new ArenaManager();

        File schematics = new File(getDataFolder(), "schematics");
        if(!schematics.exists())
            schematics.mkdirs();

        if(schematics.listFiles() != null) {
            for (File file : schematics.listFiles()) {
                this.schematicManager.registerSchematic(file);
                System.out.println("Loaded schematic " + file.getName());
            }
        }

        ArenaFile templateFile = new ArenaFile("arenas", "_template.yml");

        File arenas = new File(getDataFolder(), "arenas");

        if(arenas.listFiles() != null) {
            for (File file : arenas.listFiles()) {
                if(file.getName().startsWith("_")) continue;
                ArenaFile arenaFile = new ArenaFile("arenas", file.getName());

                Arena arena = new Arena(arenaFile);

                this.arenaManager.registerArena(arena);
               // this.arenaManager.buildArenas(arena.getWorld(),10);
            }
        }

        Player kbalu = Bukkit.getPlayer("kbalu");
        kbalu.sendMessage(new TextBuilder("&5%player% &7csatlakozott egy duelhez. &a[[Kattints ide]=/riseduels join %duel_id%] &7a csatlakozáshoz.").getComponent());
//        for (Map.Entry<Integer, RunningArena> integerRunningArenaEntry : this.arenaManager.getRunnableArenas().entrySet()) {
//            integerRunningArenaEntry.getValue().enableArena();
//        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

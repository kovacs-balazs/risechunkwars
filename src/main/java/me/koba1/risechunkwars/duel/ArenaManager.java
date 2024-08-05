package me.koba1.risechunkwars.duel;

import lombok.Getter;
import me.koba1.risechunkwars.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ArenaManager {
    private final Map<String, Arena> arenas;
    private Map<Integer, RunningArena> runnableArenas;


   public ArenaManager() {
        this.arenas = new HashMap<>();
        this.runnableArenas = new HashMap<>();
    }

    public void registerArena(Arena arena) {
       this.arenas.put(arena.getName(), arena);
        Bukkit.getLogger().info("Registered arena: " + arena.getName());
    }

    public void buildArenas(World world, int n) {
       for(int z = 0; z < n;z++) {
           for (int x = 0; x < n;x++) {
               try {
                   this.arenas.get("ruins").getSchematic().buildArenaTo(new Location(world,x * 100,85,z * 100));
                   RunningArena runningArena = new RunningArena(this.arenas.get("ruins"), Utils.hashCode(x,z));
                   runnableArenas.put(Utils.hashCode(x,z),runningArena);
               } catch (IOException e) {
                   Bukkit.getLogger().severe("Error while pasting new arena!");
                   e.printStackTrace();
               }
           }
       }
    }
    public void rebuildArena(int hash){
       int[] xz = Utils.fromHashCode(hash);
       Arena arena = runnableArenas.get(hash).getArena();
        try {
            arena.getSchematic().buildArenaTo(new Location(arena.getWorld(),xz[0] * 100, 85, xz[1] * 100));
        } catch (IOException e) {
            Bukkit.getLogger().severe("Error while pasting new arena!");
        }
    }
}

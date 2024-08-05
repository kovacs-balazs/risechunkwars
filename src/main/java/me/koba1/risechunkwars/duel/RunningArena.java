package me.koba1.risechunkwars.duel;

import lombok.Getter;
import me.koba1.risechunkwars.Main;
import me.koba1.risechunkwars.objects.Region;
import me.koba1.risechunkwars.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RunningArena {

    private Arena arena;
    private List<Player> players;
    //private Region region;
    private int id;
    //Real locations
    private Location lobbyLocation;
    private boolean joinable;
    private boolean arenaStarting;
    private long startTime;


    
    public RunningArena(Arena arena,int id) {
        this.arena = arena;
        this.players = new ArrayList<>();
        //this.region = region;
        this.id = id;
        this.joinable = false;

    }

    public void enableArena() {
        int[] xz = Utils.fromHashCode(this.id);
        this.lobbyLocation = new Location(arena.getWorld(), xz[0] * 100 + this.arena.getLobbyLocationOffset().getBlockX(), 85 + this.arena.getLobbyLocationOffset().getBlockY(), xz[1] * 100 + this.arena.getLobbyLocationOffset().getBlockZ());
        //Bukkit.getLogger().info("Arena " + this.id + " enabled! Lobby location: " + this.lobbyLocation);
        this.joinable = true;
        new GameLoop(this).runTaskTimer(Main.getPlugin(Main.class),0L,20L);
    }

    public void joinPlayer(Player player) {
        this.players.add(player);
        player.teleport(this.lobbyLocation);
        switch (this.arena.getType()){
            case SOLO -> {
                if(this.players.size() == 2){
                    this.arenaStarting = true;
                    this.startTime = System.currentTimeMillis() + this.arena.getStartTime() * 1000L;
                } else {
                    this.arenaStarting = false;
                    this.startTime = 0;
                }
            }
        }

    }


    public static class GameLoop extends BukkitRunnable {
        private RunningArena arena;
        public GameLoop(RunningArena arena){
            this.arena = arena;
        }
        @Override
        public void run() {
            if(arena.startTime - System.currentTimeMillis() <= 0){
                
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                if(arena.isArenaStarting()){
                    player.sendMessage((arena.startTime - System.currentTimeMillis()) / 1000 + "s start...");
                }
            }

        }
    }

}

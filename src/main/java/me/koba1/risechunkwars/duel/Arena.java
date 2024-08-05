package me.koba1.risechunkwars.duel;

import lombok.Getter;
import me.koba1.risechunkwars.Main;
import me.koba1.risechunkwars.files.ArenaFile;
import me.koba1.risechunkwars.schematic.ArenaSchematic;
import me.koba1.risechunkwars.utils.LocationUtils;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

@Getter
public class Arena {

    private final String name;
    private final ArenaFile file;
    private final ArenaSchematic schematic;
    private final World world;
    private String displayName;
    private String description;
    private ArenaType type;
    private int waitingTime;
    private int startTime;
   // private Map<>

    // Coord offsets UwU

    private Location lobbyLocationOffset;

    public Arena(ArenaFile file) {
        this.file = file;
        this.name = this.file.getFile().getString("name");
        this.schematic = Main.getInstance().getSchematicManager().getSchematics().get(this.file.getFile().getString("schematic"));
        Validate.notNull(this.schematic, name + " arénához tartozó schematic nem található.");

        this.world = Bukkit.getWorld(this.file.getFile().getString("world"));
        Validate.notNull(this.world, name + " arénához tartozó világ nem található.");

        this.displayName = this.file.getFile().getString("displayName");


//        this.team1SpawnLocations = this.file.getFile().getStringList("offsets.team1_spawns")
//                .stream()
//                .map(LocationUtils::parseLocation)
//                .toList();
//        this.team2SpawnLocations = this.file.getFile().getStringList("offsets.team2_spawns")
//                .stream()
//                .map(LocationUtils::parseLocation)
//                .toList();

        this.waitingTime = this.file.getFile().getInt("times.waiting");
        this.startTime = this.file.getFile().getInt("times.starting");
        this.type = ArenaType.getByName(this.file.getFile().getString("type"));
        this.lobbyLocationOffset = LocationUtils.parseLocation(this.file.getFile().getString("offsets.lobby_spawn"));
    }

    public void reload() {
        this.displayName = this.file.getFile().getString("displayName");
//        this.team1SpawnLocations = this.file.getFile().getStringList("offsets.team1_spawns")
//                .stream()
//                .map(LocationUtils::parseLocation)
//                .toList();
//        this.team2SpawnLocations = this.file.getFile().getStringList("offsets.team2_spawns")
//                .stream()
//                .map(LocationUtils::parseLocation)
//                .toList();

    }
}

package me.koba1.risechunkwars.utils;

import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationUtils {

    public static Location parseLocation(String input) {
        Validate.notNull(input, "A location nem lehet nulla xd");
        String[] args = input.split("; ");
        if(args.length != 2) return null;
        World world = Bukkit.getWorld(args[0]);
        if(world == null) return null;

        String[] coords = args[1].split(", ");
        switch (coords.length) {
            case 3: {
                List<Double> doubles = toDoubles(coords);
                return new Location(
                        world,
                        doubles.get(0),
                        doubles.get(1),
                        doubles.get(2)
                );
            }
            case 5: {
                List<Double> doubles = toDoubles(Arrays.copyOfRange(coords, 0, 3));
                List<Float> floats = toFloats(Arrays.copyOfRange(coords, 3, coords.length));
                return new Location(
                        world,
                        doubles.get(0),
                        doubles.get(1),
                        doubles.get(2),
                        floats.get(0),
                        floats.get(1)
                );
            }
        }

        return null;
    }

    public static String convertLocation(Location location, boolean blockOnly, boolean yawPitch) {
        StringBuilder builder = new StringBuilder();
        builder.append(location.getWorld().getName()).append("; ");

        if(blockOnly) {
            builder.append(location.getBlockX()).append(", ")
                    .append(location.getBlockY()).append(", ")
                    .append(location.getBlockZ());
        } else {
            builder.append(location.getX()).append(", ")
                    .append(location.getY()).append(", ")
                    .append(location.getZ());
        }

        if(yawPitch) {
            builder.append(", ")
                    .append(location.getYaw()).append(", ")
                    .append(location.getPitch());
        }

        return builder.toString();
    }

    public static List<Double> toDoubles(String[] args) {
        return Arrays.stream(args).map(Double::parseDouble).toList();
    }

    public static List<Float> toFloats(String[] args) {
        return Arrays.stream(args).map(Float::parseFloat).toList();
    }
}
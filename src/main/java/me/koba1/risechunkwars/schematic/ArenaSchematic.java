package me.koba1.risechunkwars.schematic;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.Location;
import org.bukkit.World;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ArenaSchematic {
    private File schematicFile;

    public ArenaSchematic(File schematicFile) {
        this.schematicFile = schematicFile;
    }

    public void buildArenaTo(Location buildPos) throws IOException {

        ClipboardFormat format = ClipboardFormats.findByFile(this.schematicFile);

        ClipboardReader reader = format.getReader(new FileInputStream(this.schematicFile));

        Clipboard clipboard = reader.read();

        try {
            com.sk89q.worldedit.world.World adaptedWorld = BukkitAdapter.adapt(buildPos.getWorld());

            EditSession editSession = WorldEdit.getInstance().newEditSession(adaptedWorld);

// Saves our operation and builds the paste - ready to be completed.
            Operation operation = new ClipboardHolder(clipboard).createPaste(editSession).to(BlockVector3.at(buildPos.getBlockX(), buildPos.getBlockY(), buildPos.getBlockZ())).ignoreAirBlocks(false).build();

            try { // This simply completes our paste and then cleans up.
                Operations.complete(operation);
                editSession.flushSession();

            } catch (WorldEditException e) { // If worldedit generated an exception it will go here
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

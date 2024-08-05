package me.koba1.risechunkwars.files;

import lombok.Getter;
import me.koba1.risechunkwars.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

@Getter
public class ArenaFile {

    private static Main m = Main.getPlugin(Main.class);
    private File cfg;
    private FileConfiguration file;
    private final File ymlFile;
    private String path;

    public ArenaFile(String ymlFile) {
        this.path = ymlFile;
        this.ymlFile = new File(m.getDataFolder(), this.path);
        this.cfg = this.ymlFile;
        setup();
    }

    public ArenaFile(String folder, String file) {
        this.path = file;
        if(folder != null) {
            this.path = folder + "/" + file;
        }
        this.ymlFile = new File(m.getDataFolder(), this.path);
        this.cfg = this.ymlFile;
        setup();
    }

    public void setup() {
        if(this.cfg.length() == 0) {
            this.cfg.delete();
        }

        if (!this.cfg.exists()) {
            try {
                this.ymlFile.getParentFile().mkdirs();
                this.ymlFile.createNewFile();
                InputStream in = m.getResource(this.path);
                FileOutputStream out = new FileOutputStream(this.cfg);

                if(in == null) {
                    return;
                }
                try {
                    int n;
                    while ((n = in.read()) != -1) {
                        out.write(n);
                    }
                }
                finally {
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.file = YamlConfiguration.loadConfiguration(this.cfg);
    }

    public void save() {
        try {
            this.file.save(this.cfg);
        } catch (IOException e) {
            System.out.println("Can't save language file");
        }
    }

    public void reload() {
        this.file = YamlConfiguration.loadConfiguration(this.cfg);
    }
}

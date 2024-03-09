package me.trouper.ultracore.data.config;

import io.github.itzispyder.pdk.utils.misc.JsonSerializable;

import java.io.File;

public class Config implements JsonSerializable<Config> {

    @Override
    public File getFile() {
        File file = new File("plugins/UltraCore/main-config.json");
        file.getParentFile().mkdirs();
        return file;
    }

    public String prefix = "&9UltraCore> &7";
    public boolean debugMode = false;
    public Plugin plugin = new Plugin();

    public class Plugin {

    }
}

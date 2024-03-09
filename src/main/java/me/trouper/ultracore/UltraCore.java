package me.trouper.ultracore;

import io.github.itzispyder.pdk.PDK;
import io.github.itzispyder.pdk.utils.misc.JsonSerializable;
import me.trouper.ultracore.cmds.UltraCoreCommand;
import me.trouper.ultracore.data.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class UltraCore extends JavaPlugin {

    private static UltraCore instance;
    private static final File configFile = new File("plugins/UltraCore/main-config.json");
    public static Config config = JsonSerializable.load(configFile, Config.class, new Config());
    public static final Logger log = Bukkit.getLogger();

    /**
     * Plugin startup logic
     */
    @Override
    public void onEnable() {

        log.info("\n]======------ Pre-load started! ------======[");
        PDK.init(this);
        instance = this;

        log.info("Loading Config...");

        loadConfig();

        startup();
    }

    public void startup() {
        log.info("\n]======----- Loading UltraCore! -----======[");

        // Plugin startup logic
        log.info("Starting Up! (%s)...".formatted(getDescription().getVersion()));


        new UltraCoreCommand().register();

        // Events

        log.info("""
                Finished!
                Make ASCII art for your plugin here!
                > http://patorjk.com/software/taag/ <
                """);
    }

    public void loadConfig() {

        // Init
        config = JsonSerializable.load(configFile, Config.class,new Config());

        // Save
        config.save();

    }


    /**
     * Plugin shutdown logic
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        log.info("UltraCore has disabled! (%s)".formatted(getDescription().getVersion()));
    }

    public static UltraCore getInstance() {
        return instance;
    }

}

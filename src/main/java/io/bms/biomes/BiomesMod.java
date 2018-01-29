package io.bms.biomes;

import java.util.Map;
import java.util.logging.Logger;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.gmail.sharpcastle33.managers.RegionsManager;
import io.bms.biomes.config.BiomesConfig;
import io.bms.biomes.event.BiomesListener;

/**
 * Created by benjaminsutter on 7/19/17.
 */
public class BiomesMod extends JavaPlugin {
    private static BiomesMod instance;
    public static RegionsManager manager;

    public static BiomesMod getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Logger logger = getLogger();

        FileConfiguration config = getConfig();
        BiomesConfig.initConfig(config);
        saveConfig();
        
        
        Map<String, String> regions = BiomesConfig.loadBiomes();
        manager = new RegionsManager(regions);

        new BiomesListener();
        getServer().getPluginManager().registerEvents(new BiomesListener(), this);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("loadbiomes")) {
            if (sender instanceof Player) {
                if (!sender.hasPermission("biomes.loadbiomes")) {
                    return true;
                }
            }
            reloadConfig();
            return true;
        }

        return false;
    }
}

package io.bms.biomes.config;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import io.bms.biomes.BiomesMod;

/**
 * Created by benjaminsutter on 7/20/17.
 */
public class BiomesConfig {

    public static void initConfig(FileConfiguration config)  {
        for (World world : BiomesMod.getInstance().getServer().getWorlds()) {
            for (Biome biome : Biome.values()) {
                if (!config.isSet(String.format("worlds.%s.biomes.%s", world.getName(), biome.name()))) {
                    config.set(String.format("worlds.%s.biomes.%s", world.getName(), biome.name()), biome.name());
                }
            }
        }
    }
    
    public static Map<String, String> loadBiomes(){
      Map<String, String> regions = new HashMap<String, String>();
      
      FileConfiguration config = BiomesMod.getInstance().getConfig();
      
      for(World world : Bukkit.getServer().getWorlds()){
        for(Biome b : Biome.values()){
          if(config.isSet(String.format("worlds.%s.biomes.%s", world.getName(), b.name()))){
            String regionName = config.getString(String.format("worlds.%s.biomes.%s", world.getName(), b.name()));
          
            if(regionName != b.name()){
              regions.put(world.getName() + b.name(), regionName);
            }
          }
        }
      }
      
      return regions;
    }
/*
    public static String getCustomBiomeName(Biome biome, String worldName) {
        FileConfiguration config = BiomesMod.getInstance().getConfig();
        if (config.isSet(String.format("worlds.%s.biomes.%s", worldName, biome.name())))
            return config.getString(String.format("worlds.%s.biomes.%s", worldName, biome.name()));
        return null;
    }*/
}

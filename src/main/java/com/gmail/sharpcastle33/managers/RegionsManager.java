package com.gmail.sharpcastle33.managers;

import java.util.Map;
import org.bukkit.World;
import org.bukkit.block.Biome;

public class RegionsManager {
  
  private Map<String, String> regions;
  
  public RegionsManager(Map<String,String> r){
    regions = r;
  }
  
  public Map<String, String> getRegions(){
    return regions;
  }
  
  public void setRegions(Map<String, String> r){
    regions = r;
  }
  
  public String getRegionName(Biome b, World w){
    if(regions.containsKey(b.name())){
      return regions.get(b.name());
    }else return b.name();
  }

}

package io.bms.events.parsing;

import io.bms.events.EventsMod;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by benjaminsutter on 7/20/17.
 */
public class JSParse {

    public static ScriptEngine scriptEngine;

    public JSParse(String dataFolderName, ClassLoader cl) throws FileNotFoundException, ScriptException {
        Thread.currentThread().setContextClassLoader(cl);
        this.scriptEngine = new ScriptEngineManager(cl).getEngineByName("nashorn");
        // expose blocks to JS
        for (Material material : Material.values()) {
            scriptEngine.put(material.name(), material);
        }
        // expose entities to JS
        for(EntityType type : EntityType.values()) {
            scriptEngine.put(type.name(), type);
        }
        scriptEngine.eval(new FileReader(String.format("plugins/%s/%s", dataFolderName, EventsMod.script)));
    }
}

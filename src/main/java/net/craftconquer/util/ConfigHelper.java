package net.craftconquer.util;

import com.google.gson.Gson;
import net.craftconquer.config.CraftConquerConfig;
import net.craftconquer.main.Main;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;

public class ConfigHelper
{
    public static final String ccDirectory = "./plugins/craftconquer";
    public static final String ccConfig = ccDirectory + "/config.json";

    public static <T> T LoadConfig(String path, Class<T> classOfT)
    {
        try
        {
            String json = Files.readString(Path.of(ConfigHelper.ccConfig));

            if(json.isEmpty())
            {
                try
                {
                    var newConfig = classOfT.getDeclaredConstructor().newInstance();
                } catch (InstantiationException e)
                {
                    Main.getInstance().getLogger().log(Level.SEVERE, "Failed to load new '"+path+"' config.");
                    e.printStackTrace();
                } catch (IllegalAccessException e)
                {
                    Main.getInstance().getLogger().log(Level.SEVERE, "Failed to load new '"+path+"' config.");
                    e.printStackTrace();
                } catch (InvocationTargetException e)
                {
                    Main.getInstance().getLogger().log(Level.SEVERE, "Failed to load new '"+path+"' config.");
                    e.printStackTrace();
                } catch (NoSuchMethodException e)
                {
                    Main.getInstance().getLogger().log(Level.SEVERE, "Failed to load new '"+path+"' config.");
                    e.printStackTrace();
                }
                Files.writeString(Path.of(ConfigHelper.ccConfig), new Gson().toJson(ccConfig));
            }
            else
            {
                return new Gson().fromJson(json, classOfT);
            }
        } catch (IOException e)
        {
            Main.getInstance().getLogger().log(Level.SEVERE, "Failed to load '"+path+"' config.");
            e.printStackTrace();
        }

        return null;
    }
}

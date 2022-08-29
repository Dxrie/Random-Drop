package randomdrop.randomdrop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import randomdrop.randomdrop.eventlistener.ServerEventListener;

import java.util.ArrayList;

public final class Randomdrop extends JavaPlugin {

    Randomdrop plugin;

    public static ArrayList<Material> itemList = new ArrayList<>();


    @Override
    public void onEnable() {
        plugin = Randomdrop.this;

        new ServerEventListener(this);

        for (Material forEach : Material.values()) {
            itemList.add(forEach);
        }

        getConfig().addDefault("randomDropRange", 128);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();
    }
}

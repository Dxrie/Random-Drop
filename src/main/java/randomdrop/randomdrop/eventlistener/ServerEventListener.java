package randomdrop.randomdrop.eventlistener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import randomdrop.randomdrop.Randomdrop;

import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Random;

public class ServerEventListener implements Listener {
    Randomdrop plugin;

    Random random = new Random();

    public ServerEventListener(Randomdrop plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        World world = e.getPlayer().getWorld();
        int length = Randomdrop.itemList.size();
        ItemStack randomItem = new ItemStack(Randomdrop.itemList.get(random.nextInt(length)), random.nextInt(plugin.getConfig().getInt("randomDropRange")));

        e.setDropItems(false);
        try {
            world.dropItemNaturally(e.getBlock().getLocation(), randomItem);
        } catch (IllegalArgumentException exception) {}
    }
}

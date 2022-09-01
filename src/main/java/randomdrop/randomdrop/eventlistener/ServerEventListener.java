package randomdrop.randomdrop.eventlistener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
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
        int range = random.nextInt(plugin.getConfig().getInt("randomDropRange")) + 10;
        ItemStack randomItem = new ItemStack(Randomdrop.itemList.get(random.nextInt(length)), range);

        e.setDropItems(false);
        try {
            world.dropItemNaturally(e.getBlock().getLocation(), randomItem);
        } catch (IllegalArgumentException exception) {
            ((TNTPrimed) world.spawnEntity(e.getPlayer().getLocation(), EntityType.PRIMED_TNT)).setFuseTicks(1 * 20);
            Bukkit.getLogger().info("trying to spawn tnt");
        }
    }
}

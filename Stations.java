package Stations;

import Stations.Lisener.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Stations extends JavaPlugin {

    private Economy eco;
    private static Stations pl;

    public static Stations getPlugin() {
        return pl;
    }

    public Economy getEco() {
        return this.eco;
    }

    private boolean setupEco() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        eco = rsp.getProvider();
        return eco != null;
    }

    public void onEnable() {
        setupEco();
        getServer().getPluginManager().registerEvents(new TouchInEvent(), this);
        getServer().getPluginManager().registerEvents(new TouchOutEvent(), this);
        getServer().getPluginManager().registerEvents(new AddFundsEvent(), this);
        getServer().getPluginManager().registerEvents(new GetCardEvent(), this);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new WaitTick(), 1L, 1L);
        pl = this;
        getPlugin().saveDefaultConfig();
        getPlugin().getConfig();
    }

    public void onDisable() {
        getPlugin().saveConfig();

    }
}

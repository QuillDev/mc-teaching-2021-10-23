package moe.quill.teachingminecraftstuff;

import moe.quill.teachingminecraftstuff.commands.GameModeCommand;
import moe.quill.teachingminecraftstuff.commands.LowGrav;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeachingMinecraftStuff extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic


        final var gmCommand = getCommand("gm");
        if (gmCommand != null) {
            final var executor = new GameModeCommand();
            gmCommand.setExecutor(executor);
            gmCommand.setTabCompleter(executor);
        }

//        final var gravityCommand = getCommand("lg");
//        if (gravityCommand != null) {
//            final var executor = new LowGrav(this);
//            gravityCommand.setExecutor(executor);
//        }


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

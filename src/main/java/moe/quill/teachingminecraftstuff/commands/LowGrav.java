package moe.quill.teachingminecraftstuff.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class LowGrav implements CommandExecutor {

    float gravity = -.007f;

    public LowGrav(Plugin plugin) {

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach((player) -> {
                    if (player.getVelocity().getY() > 0) return;

                    player.setVelocity(player.getVelocity().setY(gravity));

                });
            }
        }.runTaskTimer(plugin, 0, 1);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) return true;

        float gravity;
        try {
            gravity = Float.parseFloat(args[0]);
        } catch (IllegalArgumentException e) {
            return true;
        }

        this.gravity = gravity;

        return true;
    }
}

package moe.quill.teachingminecraftstuff.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameModeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (args.length < 1) {
            sender.sendMessage(Component.text("You must supply a game mode to change to!").color(NamedTextColor.RED));
            return true;
        }

        final var query = args[0];

        GameMode mode;
        try {
            mode = GameMode.valueOf(query.toUpperCase());
        } catch (IllegalArgumentException ignored) {
            sender.sendMessage(Component.text("The game mode you supplied '%s' does not exist".formatted(query)).color(NamedTextColor.RED));
            return true;
        }

        player.setGameMode(mode);
        sender.sendMessage(Component.text("Set game mode to %s!".formatted(mode.name())).color(NamedTextColor.GREEN));

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return Arrays.stream(GameMode.values())
                    .map(Enum::name)
                    .map(String::toLowerCase)
                    .filter((mode) -> mode.contains(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        return null;
    }
}

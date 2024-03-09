package me.trouper.ultracore.cmds;

import io.github.itzispyder.pdk.commands.Args;
import io.github.itzispyder.pdk.commands.CommandRegistry;
import io.github.itzispyder.pdk.commands.CustomCommand;
import io.github.itzispyder.pdk.commands.Permission;
import io.github.itzispyder.pdk.commands.completions.CompletionBuilder;
import me.trouper.ultracore.UltraCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandRegistry(value = "ultracore", permission = @Permission("ultracore.admin"), printStackTrace = true)
public class UltraCoreCommand implements CustomCommand {

    @Override
    public void dispatchCommand(CommandSender sender, Args args) {
        Player p = (Player) sender;

        switch (args.get(0).toString()) {
            case "toggle" -> {
                switch (args.get(1).toString()) {
                    case "debug" -> {
                        UltraCore.config.debugMode = !UltraCore.config.debugMode;
                        UltraCore.config.save();
                    }
                }
            }
        }
    }

    @Override
    public void dispatchCompletions(CompletionBuilder b) {
        b.then(b.arg("toggle")
                .then(b.arg("debug"))
        );
    }
}

package io.eodc.infinitybotjava.commands.util;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import io.eodc.infinitybotjava.InfinityBot;

import java.time.temporal.ChronoUnit;

public class PingCommand extends Command {
    public PingCommand() {
        this.name = "ping";
        this.help = "Checks the bot's ping to Discord's servers";
        this.category = InfinityBot.UTILITIES_CATEGORY;
        this.guildOnly = false;
    }
    @Override
    protected void execute(CommandEvent event) {
        event.reply("Pong!", msg -> {
            msg.editMessage("Pong! Took " + event.getMessage().getCreationTime().until(msg.getCreationTime(), ChronoUnit.MILLIS) +
                    "ms, Websocket took " + event.getJDA().getPing() + "ms").queue();
        });
    }
}

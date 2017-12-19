package io.eodc.infinitybotjava.commands.util;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import io.eodc.infinitybotjava.InfinityBot;
import sun.nio.ch.sctp.Shutdown;

public class ShutdownCommand extends Command {
    public ShutdownCommand () {
        this.name = "shutdown";
        this.ownerCommand = true;
        this.hidden = true;
        this.help = "Shuts down the bot";
        this.category = InfinityBot.UTILITIES_CATEGORY;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("Shutting down bot....");
        event.getJDA().shutdown();
    }
}

package io.eodc.infinitybotjava;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandClientBuilder;
import io.eodc.infinitybotjava.commands.about.UserLookupCommand;
import io.eodc.infinitybotjava.commands.util.PingCommand;
import io.eodc.infinitybotjava.commands.util.ShutdownCommand;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.EventListener;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;

public class InfinityBot implements EventListener {

    public static final Command.Category UTILITIES_CATEGORY = new Command.Category("Utilities");
    public static final Command.Category INFO_CATEGORY = new Command.Category("Info");

    public static void main(String[] args) throws LoginException, InterruptedException, RateLimitedException {

        System.out.println("Attempting to log in...");

        if (args.length > 0) {
            System.out.println("Using token " + args[0]);

            DBHandler.createDB("config.db");

            CommandClientBuilder cmd = new CommandClientBuilder()
                    .setOwnerId("150731311001239553")
                    .setPrefix("-")
                    .addCommands(
                            new PingCommand(),
                            new UserLookupCommand(),
                            new ShutdownCommand());
            new JDABuilder(AccountType.BOT)
                    .addEventListener(cmd.build())
                    .addEventListener(new InfinityBot())
                    .setToken(args[0])
                    .buildBlocking();
        } else {
            System.out.println("No token specified.... Closing");
        }
    }
    @Override
    public void onEvent(Event event) {
        if (event instanceof ReadyEvent) {
            System.out.println("Infinity Bot Dev (Java) v0.0.1 started");
        }
    }

}

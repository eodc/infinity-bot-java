package io.eodc.infinitybotjava.commands.about;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import io.eodc.infinitybotjava.InfinityBot;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class UserLookupCommand extends Command {
    public UserLookupCommand() {
        this.name = "whois";
        this.help = "Looks up information on a specified user.";
        this.guildOnly = true;
        this.category = InfinityBot.INFO_CATEGORY;
        this.arguments = "[@user|userid]";
    }

    @Override
    protected void execute(CommandEvent event) {
        if (event.getArgs() != null || !event.getArgs().equals("")) {
            String id;

            if (event.getArgs().contains("<")) {
                if (event.getArgs().contains("!"))
                    id = event.getArgs().substring(3, event.getArgs().length() - 1);
                else
                    id = event.getArgs().substring(2, event.getArgs().length() - 1);
            } else {
                id = event.getArgs();
            }
            Member target;
            try {
                target = event.getGuild().getMemberById(id);
            } catch (NumberFormatException e) {
                event.reply("Could not lookup user given; user does not exist.");
                return;
            }
            User targetUser = target.getUser();
            StringBuilder strBuilder = new StringBuilder();

            for (int i = 0; i < target.getRoles().size(); i++) {
                List<Role> rolesList = target.getRoles();
                strBuilder.append(", @");
                strBuilder.append(rolesList.get(i).getName());
                if (i != target.getRoles().size() - 1) {
                    strBuilder.append(", ");
                }
            }

            String dateCreated = targetUser.getCreationTime().getDayOfMonth() + " " +
                    targetUser.getCreationTime().getMonth().getDisplayName(TextStyle.SHORT, Locale.US) + ", " +
                    targetUser.getCreationTime().getYear();
            String dateJoined = target.getJoinDate().getDayOfMonth() + " " +
                    target.getJoinDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.US) + ", " +
                    target.getJoinDate().getYear();

            String roles = "@everyone" + strBuilder.toString();

            MessageEmbed response = new EmbedBuilder()
                    .setAuthor(target.getNickname() != null ? targetUser.getName() + "#" + targetUser.getDiscriminator() + " (" + target.getNickname() + ")" :
                        targetUser.getName() + "#" + targetUser.getDiscriminator())
                    .setThumbnail(targetUser.getAvatarUrl())
                    .setColor(target.getColor())
                    .addField("» User Details",
                              "‣ ID: `" + targetUser.getId() + "`\n" +
                                    "‣ Joined Discord: `" + dateCreated + "`\n" +
                                    "‣ Status: `" + target.getOnlineStatus().toString() + "`\n" +
                                    "‣ Currently Playing: `" + (target.getGame() != null ? target.getGame().getName() + "`\n" : "Nothing`\n"), false)
                    .addField("» Member Details",
                            "‣ Roles: `" + roles + "`\n" +
                                  "‣ Joined on: `" + dateJoined + "`",false)
                    .build();
            event.reply(response);
        } else {
            event.reply("Please enter a user to lookup.");
        }
    }
}

package dev.gigafyde.undelete;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class JoinListener extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        try {
            event.getMember().getUser().openPrivateChannel().complete().sendMessage("""
                    **Welcome to Platinum crown**
                                    
                    You can gain access to the chatting channels by reading the rules in <#813727163278688288> and then reacting to the message confirming that you agree to the rules
                    You can grab a role for your favorite series in <#794032017109024819> and you will be pinged every time we release a chapter
                                    
                    You can help the group release faster by donating at https://patreon.com/platinumcrown
                    """).queue();
        } catch (Exception ignored) {
            //Do nothing if it fails
        }
    }
}

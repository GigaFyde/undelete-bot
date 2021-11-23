package dev.gigafyde.undelete;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RoleTimer extends ListenerAdapter {
    Role role = Main.role;

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        addMemberRoleAfterDelay(event.getMember());
    }

    public void addMemberRoleAfterDelay(Member member) {
        TimerTask task = new TimerTask() {
            public void run() {
                try {
                    Guild guild = member.getGuild();
                    guild.addRoleToMember(member, role).queue();
                } catch (Exception ignored) {

                }
            }
        };
        Timer timer = new Timer("RoleTimer");
        long delay = TimeUnit.HOURS.toMillis(6);
        timer.schedule(task, delay);

    }
}

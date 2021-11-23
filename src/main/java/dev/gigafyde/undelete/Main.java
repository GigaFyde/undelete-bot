package dev.gigafyde.undelete;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static Role role;
    public static void main(String[] args) throws Exception {
        JDA jda = JDABuilder.createDefault(System.getenv("TOKEN"))
                .setEnabledIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_EMOJIS)
                .addEventListeners(new DeleteListener(), new JoinListener(), new RoleTimer()).build();
        jda.awaitReady();
        role = jda.getRoleById("794198246544900136");
    }
}

package dev.gigafyde.undelete;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DeleteListener extends ListenerAdapter {
    private final Cache<Long, User> userCache = CacheBuilder.newBuilder().concurrencyLevel(10).maximumSize(1_000_000).build();
    private final Cache<Long, Message> messageCache = CacheBuilder.newBuilder().concurrencyLevel(10).maximumSize(5_000_000).build();
    private final Cache<Long, Message> modlogCache = CacheBuilder.newBuilder().concurrencyLevel(10).maximumSize(2_500).build();

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        messageCache.put(event.getMessage().getIdLong(), event.getMessage());
        userCache.put(event.getMessage().getAuthor().getIdLong(), event.getAuthor());
    }

    @Override
    public void onGuildMessageDelete(GuildMessageDeleteEvent event) {
        Message message = modlogCache.getIfPresent(event.getMessageIdLong());
        if (message != null) {
            messageCache.put(message.getIdLong(), message);
            return;
        }

        message = messageCache.getIfPresent(event.getMessageIdLong());
        if (message == null) return;


        String content = message.getContentStripped();
        if (content.isEmpty()) {
            return;
        }
        User user = message.getAuthor();
        if (user.getIdLong() != 282914337428078593L) {
            return;
        }
        event.getChannel().sendMessage("Lurker deleted the message: `" + message.getContentDisplay() + "`").queue();
    }
}

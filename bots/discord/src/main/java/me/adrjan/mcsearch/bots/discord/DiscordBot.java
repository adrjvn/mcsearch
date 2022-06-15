package me.adrjan.mcsearch.bots.discord;

import lombok.SneakyThrows;
import me.adrjan.mcsearch.bots.Bot;
import me.adrjan.mcsearch.bots.BotsConstans;
import me.adrjan.mcsearch.bots.discord.listener.PrivateMessageReceiveListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class DiscordBot extends Bot {

    public static DiscordBot INSTANCE;
    public static JDA BOT_INSTANCE;

    @SneakyThrows
    @Override
    public void enable() {
        INSTANCE = this;
        BOT_INSTANCE = JDABuilder.createDefault(BotsConstans.TOKEN_DISCORD).build();
        BOT_INSTANCE.getPresence().setActivity(Activity.playing("( ͡° ͜ʖ ͡°)"));
        BOT_INSTANCE.getPresence().setStatus(OnlineStatus.ONLINE);
        BOT_INSTANCE.awaitReady();
        BOT_INSTANCE.addEventListener(
                new PrivateMessageReceiveListener(
                        super.getAccountFactory(),
                        super.getAccountCache(),
                        super.getBotRecordSearchHandler(),
                        super.getExecutorService()));
    }
}
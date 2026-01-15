package xyz.glarmer;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import xyz.glarmer.configuration.Configuration;

import java.util.EnumSet;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration("monke.properties");

        try {
            EnumSet<GatewayIntent> intents = EnumSet.of(
                    // Enables MessageReceivedEvent for guild (also known as servers)
                    GatewayIntent.GUILD_MESSAGES,
                    // Enables the event for private channels (also known as direct messages)
                    GatewayIntent.DIRECT_MESSAGES,
                    // Enables access to message.getContentRaw()
                    GatewayIntent.MESSAGE_CONTENT,
                    // Enables MessageReactionAddEvent for guild
                    GatewayIntent.GUILD_MESSAGE_REACTIONS,
                    // Enables MessageReactionAddEvent for private channels
                    GatewayIntent.DIRECT_MESSAGE_REACTIONS);

            JDA jda = JDABuilder.createDefault((String) config.getOption("BOT_TOKEN").getValue(), intents)
                    .addEventListeners(new MessageReceivedListener())
                    .setActivity(Activity.watching("for /help"))
                    .build();

            jda.getRestPing()
                    .queue(ping ->
                            // shows ping in milliseconds
                            System.out.println("Logged in with ping: " + ping));

            // If you want to access the cache, you can use awaitReady() to block the main thread until the jda instance
            // is fully loaded
            jda.awaitReady();
        } catch (InterruptedException e) {
            // Thrown if the awaitReady() call is interrupted
            e.printStackTrace();
        }


    }
}
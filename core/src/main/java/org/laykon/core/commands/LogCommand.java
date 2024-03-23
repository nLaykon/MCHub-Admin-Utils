package org.laykon.core.commands;

import com.sun.tools.javac.Main;
import net.labymod.api.client.chat.command.Command;
import org.laykon.core.MCHubAdminUtils;
import org.laykon.core.config.MainConfig;
import org.laykon.core.utils.DiscordWebhook;
import java.awt.*;
import java.io.IOException;

public class LogCommand extends Command {

  private final MainConfig cfg;

  public LogCommand(MainConfig cfg) {
    super("log", "l");
    this.cfg = cfg;
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    // Retrieve webhook URL from configuration

    // Check if webhook URL is not blank
    if (!cfg.webhookUrl().get().isEmpty()) {
      DiscordWebhook webhook = new DiscordWebhook(cfg.webhookUrl().get());
      if (cfg.webhookImageURL().get().isEmpty()) {
        return true;
      }
      // Check if there are 3 arguments
      if (!(arguments.length == 3)) {
        displayMessage("§cCommand Usage: /log Name Reason_For_Punish ImgurLink");
        displayMessage("§cExpected 3 arguments, got " + arguments.length);
        return true;
      }

      // Check if the third argument contains an Imgur link
      if (!arguments[2].contains("https://i.imgur.com/")) {
      //if (!arguments[(arguments.length - 1)].contains("https://i.imgur.com/")) {
        displayMessage("§cError: Arg3 Did not contain an imgur link");
        return true;
      }
      if (cfg.webhookImageURL().get() != null) {
        webhook.setAvatarUrl(cfg.webhookImageURL().get());
      } else {
        webhook.setAvatarUrl(
            "https://cdn.discordapp.com/avatars/256116115422314496/1a69f18e8e9405ed31f26b637df752b4.png");
      }

      webhook.setUsername("MCHub Logs");

      Thread thread = new Thread(() -> {
        displayMessage("§dLogging to Discord...");
        webhook.setContent(arguments[0]);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
            .setTitle("Punishment")
            //.setDescription(String.format("arg1 %s arg2 %s arg3 %s arg4 %s", arguments[0], arguments[1], arguments[2], arguments[3])) // Not Working
            //.setDescription("Punishment for " + arguments[0] + " Has been logged") // Working
            //.setDescription("Reason " + args + " ") // Not Working
            //.setDescription("Reason " + arguments[1] + arguments[2] + arguments[3] + arguments[4] + " ") // also not working
            .addField("Player", arguments[0], false)
            .addField("Reason", arguments[1].replace("_", " "), false)
            .setImage(arguments[2])
            .setColor(Color.CYAN));

        long timeTaken;
        try {
          long startTime = System.currentTimeMillis();
          webhook.execute();
          long endTime = System.currentTimeMillis();
          timeTaken = endTime - startTime;
        } catch (IOException e) {
          e.printStackTrace();
          displayMessage("§cError: " + e.getMessage());
          throw new RuntimeException(e);
        }
        displayMessage("§aLogged to Discord (" + timeTaken + "ms)");
      });
      thread.start();
    } else {
      displayMessage("§cError: Webhook URL is blank");
    }
    return true;
  }

  //this might be useless lol

  protected Class<MainConfig> configurationClass() {
    return MainConfig.class;
  }
}


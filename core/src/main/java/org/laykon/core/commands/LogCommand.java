package org.laykon.core.commands;

import com.sun.tools.javac.Main;
import net.labymod.api.LabyAPI;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.entity.player.Player;
import org.laykon.core.MCHubAdminUtils;
import org.laykon.core.config.MainConfig;
import org.laykon.core.utils.DiscordWebhook;
import java.awt.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogCommand extends Command {

  private final MainConfig cfg;

  public LogCommand(MainConfig cfg) {
    super("log", "l");
    this.cfg = cfg;
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {

    if (!cfg.webhookUrl().get().isEmpty()) {
      DiscordWebhook webhook = new DiscordWebhook(cfg.webhookUrl().get());
      if (cfg.webhookImageURL().get().isEmpty()) {
        return true;
      }
      if (!arguments[(arguments.length - 1)].contains("https://i.imgur.com/")) {
        displayMessage("§cError: Arg3 Did not contain an imgur link");
        return true;
      }
      if (cfg.webhookImageURL().get() != null) {
        webhook.setAvatarUrl(cfg.webhookImageURL().get());
      } else {
        webhook.setAvatarUrl(
            "https://cdn.discordapp.com/avatars/256116115422314496/1a69f18e8e9405ed31f26b637df752b4.png");
      }

      String regex = "\\d+[smhdwy]";
      Pattern pattern = Pattern.compile(regex);

      String punLength = "NaN";

      StringBuilder reason = new StringBuilder();

      for (int i = 1; i < arguments.length - 1; i++){
        if (containsShortLength(arguments[i], pattern)){
          punLength = arguments[i];
          continue;
        }
        reason.append(arguments[i]).append(" ");
      }
      String trimmedReason = reason.toString().trim();

      webhook.setUsername("MCHub Logs");

      String finalPunLength = punLength;
      Thread thread = new Thread(() -> {
        displayMessage("§dLogging to Discord...");
        webhook.setContent(arguments[0]);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
            .setTitle("Punishment")
            .addField("Player", arguments[0], false)
            .addField("Length", finalPunLength, false)
            .addField("Reason", trimmedReason, false)
            .setImage(arguments[arguments.length-1])
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

  public static boolean containsShortLength(String input, Pattern pattern) {
    Matcher matcher = pattern.matcher(input);
    return matcher.matches();
  }

  protected Class<MainConfig> configurationClass() {
    return MainConfig.class;
  }
}


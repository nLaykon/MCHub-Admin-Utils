package org.laykon.core.commands;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.configuration.loader.Config;
import org.laykon.core.config.ElementConfig;
import org.laykon.core.config.MainConfig;

public class HideCommand extends Command {
  ElementConfig cfg = new ElementConfig();

  public HideCommand() {
    super("hide", "h");
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    if (!(arguments.length == 1)) {
      this.displayMessage(Component.text("Command usage: /hide <chat(c): bossbar(b): scoreboard(s)>", NamedTextColor.RED));
      return true;
    }

    if (arguments[0].equalsIgnoreCase("c") || arguments[0].equalsIgnoreCase("chat")){
      if (this.cfg.getChatToggle().get()){
        this.cfg.getChatToggle().set(false);
      }else {
        this.cfg.getChatToggle().set(true);
      }
      return true;
    }



    if (arguments[0].equalsIgnoreCase("b") || arguments[0].equalsIgnoreCase("bossbar")){
      if (this.cfg.getBossBarToggle().get()){
        this.cfg.getBossBarToggle().set(false);
      }else {
        this.cfg.getBossBarToggle().set(true);
      }
      return true;
    }



    if (arguments[0].equalsIgnoreCase("s") || arguments[0].equalsIgnoreCase("scoreboard")){
      if (this.cfg.getScoreboardToggle().get()){
        this.cfg.getScoreboardToggle().set(false);
      }else {
        this.cfg.getScoreboardToggle().set(true);
      }
      return true;
    }

    this.displayMessage(Component.text("Command usage: /hide <chat(c): bossbar(b): scoreboard(s)>", NamedTextColor.RED));
    return true;
  }
}
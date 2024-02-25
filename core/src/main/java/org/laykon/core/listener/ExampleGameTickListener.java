package org.laykon.core.listener;

import net.labymod.api.Laby;
import net.labymod.api.LabyAPI;
import net.labymod.api.client.Minecraft;
import net.labymod.api.client.entity.player.ClientPlayer;
import net.labymod.api.configuration.labymod.main.LabyConfig;
import net.labymod.api.event.Phase;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatMessageSendEvent;
import net.labymod.api.event.client.lifecycle.GameTickEvent;
import org.laykon.core.MCHubContentUtils;

public class ExampleGameTickListener {
  private final MCHubContentUtils addon;

  public ExampleGameTickListener(MCHubContentUtils addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onGameTick(GameTickEvent event) {
    if (event.phase() != Phase.PRE) {
      return;
    }
  }
}

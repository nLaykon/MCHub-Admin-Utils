package org.laykon.core.listener;

import net.labymod.api.event.Phase;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.lifecycle.GameTickEvent;
import org.laykon.core.MCHubAdminUtils;

public class ExampleGameTickListener {
  private final MCHubAdminUtils addon;

  public ExampleGameTickListener(MCHubAdminUtils addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onGameTick(GameTickEvent event) {
    if (event.phase() != Phase.PRE) {
      return;
    }
  }
}

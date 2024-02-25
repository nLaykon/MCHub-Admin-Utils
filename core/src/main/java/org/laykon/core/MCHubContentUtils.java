package org.laykon.core;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import org.laykon.core.commands.HideContentCommand;
import org.laykon.core.config.MainConfig;
import org.laykon.core.listener.ExampleGameTickListener;

@AddonMain
public class MCHubContentUtils extends LabyAddon<MainConfig> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerListener(new ExampleGameTickListener(this));
    this.registerCommand(new HideContentCommand());

    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<MainConfig> configurationClass() {
    return MainConfig.class;
  }
}

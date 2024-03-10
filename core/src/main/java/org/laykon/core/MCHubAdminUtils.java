package org.laykon.core;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import org.laykon.core.commands.LogCommand;
import org.laykon.core.config.MainConfig;

@AddonMain
public class MCHubAdminUtils extends LabyAddon<MainConfig> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerCommand(new LogCommand(this.configuration()));

  }

  @Override
  protected Class<MainConfig> configurationClass() {
    return MainConfig.class;
  }
}

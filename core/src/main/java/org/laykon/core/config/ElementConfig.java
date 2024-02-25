package org.laykon.core.config;

import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class ElementConfig extends Config {
  @SwitchSetting
  private final ConfigProperty<Boolean> scoreboardToggle = new ConfigProperty<>(true);

  public ConfigProperty<Boolean> getScoreboardToggle() {
    return this.scoreboardToggle;
  }


  @SwitchSetting
  private final ConfigProperty<Boolean> chatToggle = new ConfigProperty<>(true);

  public ConfigProperty<Boolean> getChatToggle() {
    return this.chatToggle;
  }


  @SwitchSetting
  private final ConfigProperty<Boolean> bossBarToggle = new ConfigProperty<>(true);

  public ConfigProperty<Boolean> getBossBarToggle() {
    return this.bossBarToggle;
  }

}

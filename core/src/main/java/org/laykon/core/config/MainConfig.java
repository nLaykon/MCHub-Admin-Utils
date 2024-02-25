package org.laykon.core.config;


import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.client.gui.screen.widget.widgets.input.KeybindWidget.KeyBindSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;

@ConfigName("settings")
public class MainConfig extends AddonConfig {
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }
  public ElementConfig elementConfig = new ElementConfig();

  public ElementConfig getElementConfig() {
    return this.elementConfig;
  }


}

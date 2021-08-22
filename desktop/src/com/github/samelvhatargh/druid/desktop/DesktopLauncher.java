package com.github.samelvhatargh.druid.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.github.samelvhatargh.druid.Config;
import com.github.samelvhatargh.druid.DruidGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Druid and His Circle of Animals");
        config.setWindowedMode(Config.windowWidth, Config.windowHeight);
        config.setWindowIcon("icons/icon16.png", "icons/icon32.png", "icons/icon48.png", "icons/icon64.png", "icons/icon128.png");
        new Lwjgl3Application(new DruidGame(), config);
    }
}
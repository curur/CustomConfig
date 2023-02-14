package com.github.curur.customconfig.plugin

import com.github.curur.customconfig.config.configBuilder
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {

    companion object {
        lateinit var plugin: Main
    }

    val testConfig = configBuilder.createCustomConfig("data.yml", dataFolder)

    override fun onEnable() {
        plugin = this

        testConfig.saveConfig()

        logger.info("enable")
    }

}
package com.github.curur.customconfig.config

import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class ConfigBuilder(private val plugin: JavaPlugin) {

    fun createCustomConfig(name: String, path: File): CustomConfig {
        return CustomConfig(plugin, name, path)
    }

}

val JavaPlugin.configBuilder: ConfigBuilder
    get() = ConfigBuilder(this)
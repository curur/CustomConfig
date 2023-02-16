package com.github.curur.customconfig.config

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.util.logging.Level

class CustomConfig(val plugin: JavaPlugin, val configName: String, val configPath: File) {

    init {
        saveDefaultConfig()
    }

    private var configFile: File? = File(configPath, configName)
    var config: FileConfiguration = YamlConfiguration.loadConfiguration(configFile!!)
        private set


    fun reloadConfig() {

        this.config = YamlConfiguration.loadConfiguration(configFile!!)

        plugin.getResource(configName)?.let {

            val defaultConfig = YamlConfiguration.loadConfiguration(InputStreamReader(it))
            this.config.setDefaults(defaultConfig)

        }
    }

    fun saveConfig() {
        try {
            config.save(this.configFile!!)
        } catch (e: IOException) {
            plugin.logger.log(Level.SEVERE, "Could not save config to ${this.configFile}", e)
        }
    }

    fun saveDefaultConfig() {
        if(this.configFile == null) this.configFile = File(configPath, configName)

        println(configFile!!.path)

        if(!this.configFile!!.exists()) {
            try {
                configFile!!.createNewFile()
            } catch (e: IOException) {
                this.plugin.logger.log(Level.SEVERE, "can't create new config file(error)")
            }
        }

    }

    fun set(path: String, value: Any?) {
        config.set(path, value)
    }

    fun get(path: String) {
        config.get(path, )
    }

}
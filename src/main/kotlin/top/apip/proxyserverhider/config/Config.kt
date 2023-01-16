package top.apip.proxyserverhider.config

import org.bukkit.configuration.file.FileConfiguration

class Config(config: FileConfiguration) {

    val whitelistedIPs: List<String>
    val debug : Boolean

    init {
        whitelistedIPs = config.getStringList("whitelistedIPs")
        debug = config.getBoolean("debug")
    }

}
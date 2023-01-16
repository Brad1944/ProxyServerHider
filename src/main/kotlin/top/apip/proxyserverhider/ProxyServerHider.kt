package top.apip.proxyserverhider

import org.bukkit.plugin.java.JavaPlugin
import top.apip.proxyserverhider.commands.Command
import top.apip.proxyserverhider.config.Config
import top.apip.proxyserverhider.manager.manager.PacketManager

class ProxyServerHider: JavaPlugin() {

    lateinit var configfile : Config

    override fun onEnable() {
        PacketManager(this)
        saveDefaultConfig()
        configfile = Config(config)
        getCommand("psh")?.setExecutor(Command(this))
    }

}
package top.apip.proxyserverhider.commands

import net.md_5.bungee.api.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import top.apip.proxyserverhider.ProxyServerHider
import top.apip.proxyserverhider.config.Config

class Command(private val main: ProxyServerHider): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("${ChatColor.RED}Usage: /psh reload")
            return true
        }
        if (args[0] == "reload") {
            if (!sender.hasPermission("psh.reload")) {
                sender.sendMessage("${ChatColor.RED}You don't have permission to use this commands!")
                return true
            }
            main.reloadConfig()
            main.configfile = Config(main.config)
            sender.sendMessage("${ChatColor.GREEN}Config Successfully Reloaded!")
        }

        return true
    }
}
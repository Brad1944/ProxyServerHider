package top.apip.proxyserverhider.manager.manager

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.ProtocolManager
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import top.apip.proxyserverhider.ProxyServerHider

class PacketManager(private val main: ProxyServerHider) {

    private var manager: ProtocolManager = ProtocolLibrary.getProtocolManager()

    init {
        manager.addPacketListener(object: PacketAdapter(main, ListenerPriority.NORMAL, PacketType.Handshake.Client.SET_PROTOCOL) {
            override fun onPacketReceiving(event: PacketEvent) {
                val ip = event.player.address?.hostString
                if (main.configfile.debug)
                    println(ip)
                if (!main.configfile.whitelistedIPs.contains(ip))
                    event.isCancelled = true
            }
        })
    }

}
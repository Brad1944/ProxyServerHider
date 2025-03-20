package top.apip.proxyserverhider.filter

import io.netty.channel.ChannelHandlerContext
import net.bytebuddy.implementation.bind.annotation.Origin
import net.bytebuddy.implementation.bind.annotation.SuperCall
import java.lang.reflect.Method
import java.net.InetAddress
import java.net.InetSocketAddress
import java.util.concurrent.Callable


object WhitelistInterceptor {
    @Throws(Exception::class)
    @JvmStatic
    fun intercept(ctx: ChannelHandlerContext) {
        val remoteAddress = ctx.channel().remoteAddress() as InetSocketAddress
        val remoteIp = remoteAddress.address

        if (listOf<InetAddress>().contains(remoteIp)) {
            //callable.call() // Allow the connection if the IP is whitelisted
        } else {
            println("Rejected connection from: $remoteIp")
            ctx.close() // Close the connection if not whitelisted
        }
    }
}
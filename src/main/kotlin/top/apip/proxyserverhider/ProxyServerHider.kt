package top.apip.proxyserverhider

import net.bytebuddy.ByteBuddy
import net.bytebuddy.dynamic.DynamicType
import net.bytebuddy.implementation.MethodDelegation
import net.bytebuddy.matcher.ElementMatchers
import org.bukkit.plugin.java.JavaPlugin
import top.apip.proxyserverhider.commands.Command
import top.apip.proxyserverhider.config.Config
import top.apip.proxyserverhider.filter.WhitelistInterceptor
import java.lang.reflect.Field
import java.lang.reflect.Modifier

class ProxyServerHider: JavaPlugin() {

    lateinit var configfile : Config

    override fun onEnable() {
        saveDefaultConfig()
        configfile = Config(config)
        getCommand("psh")?.setExecutor(Command(this))
        inject()
    }

    fun inject() {
        val connectionClass = Class.forName("net.minecraft.network.NetworkManager")
        val instrumentation = ByteBuddyAgent.install()

        val subclass: DynamicType.Unloaded<*> = ByteBuddy()
            .subclass(connectionClass)
            .method(ElementMatchers.named("channelActive"))
            .intercept(MethodDelegation.to(WhitelistInterceptor::class.java))
            .make()

        val modifiedClass: Class<*> = subclass.load(connectionClass.classLoader).getLoaded()

        try {
            val modifiersField = Field::class.java.getDeclaredField("modifiers")
            modifiersField.isAccessible = true
            modifiersField.setInt(modifiedClass, modifiedClass.modifiers and Modifier.FINAL.inv())

            // Get the ClassLoader for the Connection class
            val classLoader = connectionClass.classLoader

            // Find the "defineClass" method
            val defineClassMethod = ClassLoader::class.java.getDeclaredMethod(
                "defineClass", String::class.java, ByteArray::class.java, Int::class.java, Int::class.java
            )
            defineClassMethod.isAccessible = true

            // Get the bytecode of the modified class (you might need a way to get this)
            val modifiedClassBytes: ByteArray = subclass.bytes

            // Define the modified class in the ClassLoader
            defineClassMethod.invoke(
                classLoader, "net.minecraft.network.NetworkManager", // The original class name
                modifiedClassBytes, 0, modifiedClassBytes.size
            )

            println("Class redefinition attempt complete.")
        } catch (e: Exception) {
            e.printStackTrace()
        }



    }

}
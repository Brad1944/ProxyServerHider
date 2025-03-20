# DISCLAIMER
The plugin is currently very much bypassable because it's using protocollib to decline MOTD packet from players, which does work but comes with a lot of bypasses since the connection itself is accepted through Minecraft's Netty. There should be a rework on this in order to be 100% safe with it (e.g Injecting minecraft's network stuff using reflections for a complete control). Right now this repo only acts as a POC and an idea proposal. Everyone is free to open up a pull request regarding this! :)  

A Spigot Plugin Used for hiding servers from port scanner, without using any firewall

## Dependency
- ProtocolLib

## Usage
1. Install the latest version of ProtocolLib and This Plugin to your server
2. Add your Bungeecord Server IP to the Whitelisted IPs list in the config
3. (Optional) Change your server port to an [Unused Popular TCP Ports](https://en.wikipedia.org/wiki/List_of_TCP_and_UDP_port_numbers) to hide it even more
4. Restart your server

## Building
Clone this repository then either run `./gradlew build`, or import it to your IDE

## FAQ
>Q: Why should i use this plugin?\
A: This plugin is sort of an alternative to Firewalls for server that have no access to them (Like a hosting)

>Q: Is this any different with BungeeGuard?\
A: Yes! This plugin cancels Minecraft Client Handshake packet (for unWhitelisted IPs) in order to mimics an ordinary TCP application

Found any problem or have any question? Message me on Discord: [Meong#2093](https://discord.com/users/418362484433485824')

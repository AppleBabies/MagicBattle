package com.themadmc.magicbattle;

import net.minecraft.server.v1_7_R4.ChatComponentText;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.spigotmc.ProtocolInjector;
 
/**
* Created by Andrew on 11/2/2014.
*/

public class Title {
String message = "";
ProtocolInjector.PacketTitle.Action action = null;
int in = 0;
int out = 0;
int stay = 0;
public Title(String m, ProtocolInjector.PacketTitle.Action a, int i, int o, int s)
{
message = m;
action = a;
in = i;
out = o;
stay = s;
}
public static final int TITLE_PROTOCOL_VERSION = 18;
ProtocolInjector.PacketTitle packet = null;
public void build()
{
if(action == ProtocolInjector.PacketTitle.Action.TIMES)
{
 
ProtocolInjector.PacketTitle title = new ProtocolInjector.PacketTitle(action,in,stay,out);
 
packet = title;
}
else if(action == ProtocolInjector.PacketTitle.Action.CLEAR || action == ProtocolInjector.PacketTitle.Action.RESET)
{
ProtocolInjector.PacketTitle title = new ProtocolInjector.PacketTitle(action);
 
packet = title;
}
else if(action == ProtocolInjector.PacketTitle.Action.TITLE || action == ProtocolInjector.PacketTitle.Action.SUBTITLE)
{
ProtocolInjector.PacketTitle title = new ProtocolInjector.PacketTitle(action, new ChatComponentText(ChatColor.translateAlternateColorCodes('&',message)));
packet = title;
}
}
public void send(Player player) {
if (packet == null) return;
if (player == null) {
for (Player p : Bukkit.getOnlinePlayers())
{
send(p);
}
 
} else if (hasTitleSupport(player)) {
((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
}
}
public static boolean hasTitleSupport(CommandSender sender) {
return sender instanceof CraftPlayer &&
((CraftPlayer) sender).getHandle().playerConnection.networkManager.getVersion()
>= TITLE_PROTOCOL_VERSION;
}
}

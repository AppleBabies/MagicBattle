package me.pogostick29dev.magicbattle;

import java.util.Arrays;

import me.pogostick29dev.magicbattle.MessageManager.MessageType;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum Spell {

/**

These are spells. The spells are cast when a player right clicks a book. Depending on their temporarily stored kit, they will have
access to different spells. Spell power is gained from kiling other players, to keep the game going. By default, players have 0 spell
power. The required amount of spell power to cast the most basic spell is 1.

Spells cannot be chosen. When picked up, a random spell will be assigned to the book. When the spell is used, the spell power required
is revoked from the player and the book disappears. The spell is then cast.

Players in the game are notified when the spell is cast, to warn them so they can hide or move to a safe place. Players in game will
also be notified when a player picks up a spell book. Players do not know the name of the spell when picked up, only when cast. 
If the player is killed with a spell book, the spell book will disappear.

The casting player will not be able to move, which is a great disadvantage on their part as then players can move out of the way.
HOWEVER, the player is invulnerable to all damage and projectiles while the spell is being cast.

All spells heal the player.

Spells cannot be cast in areas of holy regime.

Spell rankings are as follow:
      Kit: FIRE
      Spells:
            FIRE_STORM:
            SPELL_POWER: 1
            DESCRIPTION: A shockwave of fire particles are sent from the player. Any player (Besides the casting player) who is in the
            radius of the fire storm will be set ablaze. (Thus being why only one spell point is required. It is a very weak spell)
            
      Kit: POISON
      Spells:
            ANTI-ANTIDOTE:
            SPELL_POWER: 1
            DESCRIPTION: Any item (besides holy water) that would be used to heal a player now poisons them. This spell is effective 
            for the rest of the game. (Level one because a player will still be able to heal themselves by means of spell or holy 
            water)
            
      Kit: Lightning
      Spells:
            GODS_STORM:
            SPELL_POWER: 1
            DESCRIPTION: Spell causes casting player to be able to levitate for 30 seconds. The player CANNOT change their height in
            any way while levitating. Cloud (smoke) particles will spawn around the player. (Level one because it just makes the 
            player levitate. That's it.)

/**

	FIRE_STORM("Fire Storm", ChatColor.RED + "" + ChatColor.BOLD, new WandRunnable() {
		public void run(PlayerInteractEvent e) {
	
		}
	}),
	
	ANTI-ANTIDOTE("Anti-antidote", ChatColor.DARK_PURPLE + "" + ChatColor.BOLD, new WandRunnable() {
		public void run(PlayerInteractEvent e) {
	
		}
	}),
	
	GODS_STORM("God's Storm", ChatColor.YELLOW + "" + ChatColor.BOLD, new WandRunnable() {
		public void run(PlayerInteractEvent e) {
	
		}
	});
	
	private String name;
	private ChatColor color;
	private WandRunnable run;
	
	Wand(String name, ChatColor color, WandRunnable run) {
		this.name = name;
		this.color = color;
		this.run = run;
	}
	
	public String getName() {
		return name;
	}
	
	public ChatColor getColor() {
		return color;
	}
	
	public String getFullName() {
		return color + name;
	}
	
	public void run(PlayerInteractEvent e) {
		run.run(e);
	}
	
	public ItemStack createItemStack() {
		ItemStack i = new ItemStack(Material.STICK, 1);
		
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(getFullName());
		im.setLore(Arrays.asList("A magic wand!"));
		
		i.setItemMeta(im);
		
		return i;
	}
	
	public static Wand forName(String name) {
		for (Wand w : Wand.values()) {
			if (w.getName().equalsIgnoreCase(name)) return w;
		}
		
		return null;
	}
}

abstract class WandRunnable { public abstract void run(PlayerInteractEvent e); }

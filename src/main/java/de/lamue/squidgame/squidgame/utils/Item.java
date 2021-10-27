package de.lamue.squidgame.squidgame.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Item {

    public static void addLore(ItemStack i, String s) {
        if (i.getItemMeta().getLore() == null) {
            List lore = new ArrayList();
            lore.add(s);
            ItemMeta imeta = i.getItemMeta();
            imeta.setLore(lore);
            i.setItemMeta(imeta);
        } else {
            List lore = i.getItemMeta().getLore();
            lore.add(s);
            ItemMeta imeta = i.getItemMeta();
            imeta.setLore(lore);
            i.setItemMeta(imeta);
        }
    }

    public static ItemStack createItem(Material mat, int amount, int shortid, String DisplayName) {
        ItemStack i = new ItemStack(mat, amount, (short) shortid);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(DisplayName);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack createItemLore(Material material, int anzahl, int metadata, String displayname,
                                           String lore) {
        ItemStack item = new ItemStack(material, anzahl, (short) metadata);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setLore(Arrays.asList(new String[] { lore }));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItemLore2Enchant(Material material, int anzahl, int metadata, String displayname,
                                                   String lore, String lore2, Enchantment enchant, int stärke) {
        ItemStack item = new ItemStack(material, anzahl, (short) metadata);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setLore(Arrays.asList(new String[] { lore, lore2 }));
        meta.addEnchant(enchant, stärke, true);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItemLore2Enchant2(Material material, int anzahl, int metadata, String displayname,
                                                    String lore, String lore2, Enchantment enchant, int stärke, Enchantment enchant2, int stärke2) {
        ItemStack item = new ItemStack(material, anzahl, (short) metadata);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setLore(Arrays.asList(new String[] { lore, lore2 }));
        meta.addEnchant(enchant, stärke, true);
        meta.addEnchant(enchant2, stärke2, true);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItemEnchant2(Material material, int anzahl, int metadata, String displayname,
                                               Enchantment enchant, int stärke, Enchantment enchant2, int stärke2) {
        ItemStack item = new ItemStack(material, anzahl, (short) metadata);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.addEnchant(enchant, stärke, true);
        meta.addEnchant(enchant2, stärke2, true);
        item.setItemMeta(meta);
        return item;
    }

    public static int getCardinalDirection(Player player) throws NullPointerException {
        double rotation = (player.getLocation().getYaw() - 90) % 360;
        if (rotation < 0)
            rotation += 360.0;
        if (0 <= rotation && rotation < 67.5)
            return 10; // NorthWest
        else if (67.5 <= rotation && rotation < 112.5)
            return 2; // North
        else if (112.5 <= rotation && rotation < 157.5)
            return 10; // NorthEeast
        else if (157.5 <= rotation && rotation < 202.5)
            return 3; // East
        else if (202.5 <= rotation && rotation < 247.5)
            return 10; // SouthEast
        else if (247.5 <= rotation && rotation < 292.5)
            return 0; // South
        else if (292.5 <= rotation && rotation < 337.5)
            return 10; // SouthWest
        else if (337.5 <= rotation && rotation < 360.0)
            return 1; // West
        else
            return 10;
    }

    public static ItemStack createItemEnchant(Material material, int anzahl, int metadata, String displayname,
                                              Enchantment enchant, int stärke) {
        ItemStack item = new ItemStack(material, anzahl, (short) metadata);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.addEnchant(enchant, stärke, true);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItemEnchantNotVisible(Material material, int anzahl, int metadata, String displayname, Enchantment enchant, int stärke) {
        ItemStack item = new ItemStack(material, anzahl, (short) metadata);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.addEnchant(enchant, stärke, false);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItemLore2(Material material, int anzahl, int metadata, String displayname,
                                            String lore, String lore2) {
        ItemStack item = new ItemStack(material, anzahl, (short) metadata);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setLore(Arrays.asList(new String[] { lore, lore2 }));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItemLore3(Material material, int anzahl, int metadata, String displayname,
                                            String lore, String lore2, String lore3) {
        ItemStack item = new ItemStack(material, anzahl, (short) metadata);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setLore(Arrays.asList(new String[] { lore, lore2, lore3 }));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItemLore4(Material material, int anzahl, int metadata, String displayname,
                                            String lore, String lore2, String lore3, String lore4) {
        ItemStack item = new ItemStack(material, anzahl, (short) metadata);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setLore(Arrays.asList(new String[] { lore, lore2, lore3, lore4 }));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createLeather(Material mat, int amount, int shortid, Color color, String DisplayName,
                                          String lore, String lore2) {
        ItemStack i = new ItemStack(mat, amount, (short) shortid);
        LeatherArmorMeta meta = (LeatherArmorMeta) i.getItemMeta();
        meta.setDisplayName(DisplayName);
        meta.setColor(color);
        meta.setLore(Arrays.asList(new String[] { lore, lore2 }));
        i.setItemMeta(meta);
        return i;
    }
    public static ItemStack createItemBlock(Material mat, int amount, int shortid, String DisplayName) {
        ItemStack i = new ItemStack(mat, amount, (short) shortid);
        return i;
    }
    public static ItemStack createPotionLore2(PotionType pt, int amount, boolean werfbar, String DisplayName, String lore1 , String lore2) {
        ItemStack stack = new ItemStack(Material.POTION, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(DisplayName);
        meta.setLore(Arrays.asList(new String[] { lore1, lore2 }));
        stack.setItemMeta(meta);
        Potion pot0 = new Potion(pt, 2);
        pot0.setSplash(werfbar);
        pot0.apply(stack);
        return stack;
    }
    public static ItemStack createPotionLore1(PotionType pt, int amount, boolean werfbar, String DisplayName, String lore1) {
        ItemStack stack = new ItemStack(Material.POTION, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(DisplayName);
        meta.setLore(Arrays.asList(new String[] { lore1 }));
        stack.setItemMeta(meta);
        Potion pot0 = new Potion(pt, 2);
        pot0.setSplash(werfbar);
        pot0.apply(stack);
        return stack;
    }

    public static void clearInv(Player p) {
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        for (PotionEffect pe : p.getActivePotionEffects())
            p.removePotionEffect(pe.getType());
    }

    public static ItemStack createLeather2(Material mat, int amount, int shortid, Color color, String DisplayName) {
        ItemStack i = new ItemStack(mat, amount, (short) shortid);
        LeatherArmorMeta meta = (LeatherArmorMeta) i.getItemMeta();
        meta.setDisplayName(DisplayName);
        meta.setColor(color);
        i.setItemMeta(meta);
        return i;
    }

    public static void fillInventory(Inventory inv, ItemStack filling) {
        for (int i = 0; i < inv.getSize(); i++)
            inv.setItem(i, filling);
    }

    public static void fillinvwithid(Inventory inv, ItemStack item, int startzahl, int endzahl)
    {
        for(int start = startzahl; start < endzahl; startzahl++)
        {
            inv.setItem(start, item);
        }
    }

}

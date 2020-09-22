package net.aksyo.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class HunterItem {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public HunterItem(Material type) {
        this.itemStack = new ItemStack(type);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public HunterItem(Material type, int amount) {
        this.itemStack = new ItemStack(type, amount);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public HunterItem(Material type, int amount, short damage) {
        this.itemStack = new ItemStack(type, amount, damage);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public HunterItem(ItemStack itemStack) {
        this.itemStack = new ItemStack(itemStack);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public HunterItem name(String name) {
        itemMeta.setDisplayName(name);
        return this;
    }

    public HunterItem lore(String... lore) {
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public HunterItem addEnchantment(Enchantment enchantment, int level, boolean bool) {
        itemMeta.addEnchant(enchantment, level, bool);
        return this;
    }

    public HunterItem removeEnchantment(Enchantment enchantment) {
        itemMeta.removeEnchant(enchantment);
        return this;
    }

    public HunterItem itemFlags(ItemFlag... itemFlag) {
        itemMeta.removeItemFlags(itemMeta.getItemFlags().toArray(new ItemFlag[] {}));
        itemMeta.addItemFlags(itemFlag);
        return this;
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }

    public ItemStack create() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}

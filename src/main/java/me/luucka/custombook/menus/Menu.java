package me.luucka.custombook.menus;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class Menu implements InventoryHolder {

    protected MenuUtility menuUtility;
    protected Inventory inventory;
    protected String name;
    protected int slots;

    public Menu(MenuUtility menuUtility, String name, int slots) {
        this.menuUtility = menuUtility;
        this.name = name;
        this.slots = slots;
    }

    public abstract void handleMenu(InventoryClickEvent e);

    public abstract void setItems();

    public void open() {
        inventory = Bukkit.createInventory(this, slots, name);
        this.setItems();
        menuUtility.getPlayer().openInventory(inventory);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public int getSlots() {
        return slots;
    }
}

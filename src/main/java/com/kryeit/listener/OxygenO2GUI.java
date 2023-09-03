package com.kryeit.listener;

import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class OxygenO2GUI implements Listener {

    List<String> pins = List.of("1770 12 -4374");

    @EventHandler
    public void onOxygenTask(PlayerInteractEvent event) {
        if(event.getClickedBlock()==null) return;
        if(event.getClickedBlock().getType().equals(Material.AIR)) return;
        if (AmongKryeitors.is_otwo_sabotaged) {
            if (pins.contains(Utils.parseLocation(Objects.requireNonNull(event.getClickedBlock()).getLocation()))) {
                if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    Random random = new Random();
                    int pin_code = random.nextInt(999999);
                    Inventory gui = Bukkit.createInventory(event.getPlayer(), 36, "Pin Code : " + pin_code);

                    ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                    ItemStack zero = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                    ItemStack one = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                    ItemStack two = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                    ItemStack three = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                    ItemStack four = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                    ItemStack five = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                    ItemStack six = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                    ItemStack seven = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                    ItemStack eight = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                    ItemStack nine = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);

                    ItemMeta zero_meta = zero.getItemMeta();
                    zero_meta.setDisplayName("0");
                    zero.setItemMeta(zero_meta);
                    ItemMeta one_meta = zero.getItemMeta();
                    one_meta.setDisplayName("1");
                    one.setItemMeta(one_meta);
                    ItemMeta two_meta = zero.getItemMeta();
                    two_meta.setDisplayName("2");
                    two.setItemMeta(two_meta);
                    ItemMeta three_meta = zero.getItemMeta();
                    three_meta.setDisplayName("3");
                    three.setItemMeta(three_meta);
                    ItemMeta four_meta = zero.getItemMeta();
                    four_meta.setDisplayName("4");
                    four.setItemMeta(four_meta);
                    ItemMeta five_meta = zero.getItemMeta();
                    five_meta.setDisplayName("5");
                    five.setItemMeta(five_meta);
                    ItemMeta six_meta = zero.getItemMeta();
                    six_meta.setDisplayName("6");
                    six.setItemMeta(six_meta);
                    ItemMeta seven_meta = zero.getItemMeta();
                    seven_meta.setDisplayName("7");
                    seven.setItemMeta(seven_meta);
                    ItemMeta eight_meta = zero.getItemMeta();
                    eight_meta.setDisplayName("8");
                    eight.setItemMeta(eight_meta);
                    ItemMeta nine_meta = zero.getItemMeta();
                    nine_meta.setDisplayName("9");
                    nine.setItemMeta(nine_meta);

                    ItemStack indicator = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);


                    ItemStack[] menu_items = {filler, filler, filler, one, two, three, filler, filler, filler,
                            filler, filler, filler, four, five, six, filler, filler, filler,
                            filler, filler, filler, seven, eight, nine, filler, filler, filler,
                            filler, filler, filler, filler, zero, filler, filler, filler, indicator};

                    gui.setContents(menu_items);
                    event.getPlayer().openInventory(gui);

                    AmongKryeitors.current_pin2 = "";
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClickOxygen(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().contains("Pin Code")) {
            int p_to_admin = (int) Utils.parseString("1770 12 -4374").distance(event.getWhoClicked().getLocation());
            int p_to_o2 = (int) Utils.parseString("1767 12 -4378").distance(event.getWhoClicked().getLocation());

            if (p_to_admin > p_to_o2) {
                if (event.getClickedInventory() == event.getView().getTopInventory()) {
                    event.getView().getTopInventory().setItem(35, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                    switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                        case "0":
                            AmongKryeitors.current_pin2 += "0";
                            break;
                        case "1":
                            AmongKryeitors.current_pin2 += "1";
                            break;
                        case "2":
                            AmongKryeitors.current_pin2 += "2";
                            break;
                        case "3":
                            AmongKryeitors.current_pin2 += "3";
                            break;
                        case "4":
                            AmongKryeitors.current_pin2 += "4";
                            break;
                        case "5":
                            AmongKryeitors.current_pin2 += "5";
                            break;
                        case "6":
                            AmongKryeitors.current_pin2 += "6";
                            break;
                        case "7":
                            AmongKryeitors.current_pin2 += "7";
                            break;
                        case "8":
                            AmongKryeitors.current_pin2 += "8";
                            break;
                        case "9":
                            AmongKryeitors.current_pin2 += "9";
                            break;
                    }
                    String title = event.getView().getTitle();
                    String[] titles = title.split(" ", 4);
                    int finaltitle = titles[3].length();
                    if (AmongKryeitors.current_pin2.equals(titles[3])) {
                        onOxygenFixed onOxygenFixed = new onOxygenFixed();
                        onOxygenFixed.OnOxygenFixed(1);
                        player.closeInventory();
                        AmongKryeitors.current_pin2 = "";
                    } else if (AmongKryeitors.current_pin2.length() == finaltitle) {
                        event.getView().getTopInventory().setItem(35, new ItemStack(Material.RED_WOOL));
                        AmongKryeitors.current_pin2 = "";
                    }
                    event.setCancelled(true);
                }

            }
        }
    }
}

package com.kryeit.miscellanous;

import com.kryeit.AmongKryeitors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InventoryGUI {

    public void GameStartUp() {

        List<UUID> PlayersInGame = new ArrayList<>();

        PlayersInGame.addAll(AmongKryeitors.crewmates);
        PlayersInGame.addAll(AmongKryeitors.impostors);

        for(UUID element : PlayersInGame) {
            if (AmongKryeitors.crewmates.contains(element)) {
                Bukkit.getPlayer(element).getInventory().setItem(0, new ItemStack(Material.MAP));

                ItemStack report_body = new ItemStack(Material.RED_DYE);
                ItemMeta report_body_meta = report_body.getItemMeta();
                report_body_meta.setDisplayName("Report Body");
                report_body.setItemMeta(report_body_meta);

                Bukkit.getPlayer(element).getInventory().setItem(1, report_body);
            } else if (AmongKryeitors.impostors.contains(element)) {

                Bukkit.getPlayer(element).getInventory().setItem(1, new ItemStack(Material.MAP));

                ItemStack imp_actions = new ItemStack(Material.COMPASS);
                ItemMeta imp_actions_meta = imp_actions.getItemMeta();
                imp_actions_meta.setDisplayName("Sabotage/Report Body");
                imp_actions.setItemMeta(imp_actions_meta);

                Bukkit.getPlayer(element).getInventory().setItem(2, imp_actions);

                ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                Bukkit.getPlayer(element).getInventory().setItem(0, filler);
            } else if (AmongKryeitors.shapeshifter.equals(Bukkit.getPlayer(element))) {
                Bukkit.getPlayer(element).getInventory().setItem(1, new ItemStack(Material.MAP));

                ItemStack imp_actions = new ItemStack(Material.COMPASS);
                ItemMeta imp_actions_meta = imp_actions.getItemMeta();
                imp_actions_meta.setDisplayName("Sabotage/Report Body");
                imp_actions.setItemMeta(imp_actions_meta);

                Bukkit.getPlayer(element).getInventory().setItem(2, imp_actions);

                ItemStack shapeshift = new ItemStack(Material.PLAYER_HEAD);
                ItemMeta shapeshift_meta = shapeshift.getItemMeta();
                shapeshift_meta.setDisplayName("Shapeshift");
                shapeshift.setItemMeta(shapeshift_meta);

                Bukkit.getPlayer(element).getInventory().setItem(3, shapeshift);

                ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                Bukkit.getPlayer(element).getInventory().setItem(0, filler);
            }
        }

    }

}

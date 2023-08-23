package com.kryeit.claiming;

import com.griefdefender.api.Core;
import com.griefdefender.api.GriefDefender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ClaimUtils {

    private static final Core core = GriefDefender.getCore();
    private static final UUID claimUUID = UUID.fromString("example");

    public static boolean isInside(Player player) {
        //return core.getClaimAt(player.getLocation()).getUniqueId().equals(claimUUID);
        return true;
    }
}

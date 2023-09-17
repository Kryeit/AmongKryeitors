package com.kryeit.miscellanous;

import com.kryeit.events.onEmergencyMeeting;

public class EmergencyCooldown {

    Long RestartTime;
    public void StartCooldown() {

        RestartTime = System.currentTimeMillis();
    }

    public void CheckCooldown() {
        long updatedTime = System.currentTimeMillis() - RestartTime;
        if(updatedTime >= 30000) {
            onEmergencyMeeting.OnEmergencyMeeting(false);
        }
    }

}

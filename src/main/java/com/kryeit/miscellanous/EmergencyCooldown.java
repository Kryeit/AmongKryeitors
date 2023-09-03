package com.kryeit.miscellanous;

import com.kryeit.events.onEmergencyMeeting;

public class EmergencyCooldown {

    Long RestartTime;
    public void StartCooldown() {

        RestartTime = System.currentTimeMillis();
    }

    public void CheckCooldown() {
        Long updatedTime = System.currentTimeMillis() - RestartTime;
        if(updatedTime >= 30000) {
            onEmergencyMeeting onEmergencyMeeting = new onEmergencyMeeting();
            onEmergencyMeeting.OnEmergencyMeeting(false);
        }
    }

}

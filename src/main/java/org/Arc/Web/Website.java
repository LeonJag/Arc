package org.Arc.Web;

import java.util.Map;
import java.util.HashMap;

public enum Website {
    CONEXED, RADIUS, ACUITY, NONE;

    public String toLink() {
        switch (this) {
            case CONEXED:
                return "https://mathnasium.craniumcafe.com/onlineabrams/classroom";
            case RADIUS:
                return "https://radius.mathnasium.com/Account/Login";
            case ACUITY:
                return "https://secure.acuityscheduling.com/appointments.php";
            default:
                return "https://www.google.com";
        }
    }
    
    public String getUsername() {
        switch (this) {
            case CONEXED:
                return "onlineabrams@mathnasium.com";
            case RADIUS:
                return "Leon.Jaggon";
            case ACUITY:
                return "leon@mathnhp.com";
            default:
                return null;
        }
    }
    
    public String getPassword() {
        switch (this) {
            case CONEXED:
                return "Math1234$$";
            case RADIUS:
                return "Kaylyn2!";
            case ACUITY:
                return "Kaylyn2!";
            default:
                return null;
        }
    }
}

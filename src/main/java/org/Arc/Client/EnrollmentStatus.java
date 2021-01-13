package org.Arc.Client;

public enum EnrollmentStatus {
    ENROLLED, INACTIVE, ON_HOLD, NONE;

    public String toString(){
        switch (this) {
            case ENROLLED:
                return "Enrolled";
            case INACTIVE:
                return "Inactive";
            case ON_HOLD:
                return "On Hold";
            default:
                return null;
        }
    }
}

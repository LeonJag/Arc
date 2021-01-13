package org.Arc.Client;

public enum Center {
    GARDEN_CITY, NEW_HYDE_PARK, NONE;

    public static Center toCenter(String center) {
        center = center.trim();
        switch (center) {
            case "Garden City NY":
                return GARDEN_CITY;
            case "New Hyde Park":
                return NEW_HYDE_PARK;
        }
        return NONE;
    }
    public String toString() {
        switch (this) {
            case GARDEN_CITY:
                return "Garden City/Mineola";
            case NEW_HYDE_PARK:
                return "New Hyde Park";
            default:
                return null;
        }
    }
}

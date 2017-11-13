package enterprise.entity_bean_entity;

import java.io.Serializable;

public class GPSPosition implements Serializable {
    private double longitude;
    private double latitude;   
   
    public GPSPosition(double latitude, double longitude) {
        this.latitude  = latitude;
        this.longitude = longitude;
    }

    public String toString() {
        return " (" + latitude + ", " + longitude + ")";
    }

}   

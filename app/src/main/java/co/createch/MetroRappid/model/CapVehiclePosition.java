package co.createch.MetroRappid.model;

import com.google.android.gms.maps.model.LatLng;

import org.simpleframework.xml.Element;

public class CapVehiclePosition {

    private double latitude;
    private double longitude;

    public CapVehiclePosition(Double lat, Double lon) {
        this.latitude = lat;
        this.longitude = lon;
    }

    public CapVehiclePosition(@Element(name = "Position") String positions) {
            String parts[] = positions.split(",");
            if (parts.length == 2) {
                try {
                    Double lat = Double.parseDouble(parts[0]);
                    Double lon = Double.parseDouble(parts[1]);
                    this.latitude = lat;
                    this.longitude = lon;
                } catch (NumberFormatException ex) {

                }
            }
    }

    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }
}

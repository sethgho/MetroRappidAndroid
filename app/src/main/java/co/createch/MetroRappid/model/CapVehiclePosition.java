package co.createch.MetroRappid.model;

import com.google.android.gms.maps.model.LatLng;

import org.simpleframework.xml.Text;

public class CapVehiclePosition {

    @Text
    private String rawString;

    public LatLng getLatLng() {
        if (rawString == null) {
            return null;
        }

        String parts[] = rawString.split(",");
        if (parts.length == 2) {
            try {
                Double lat = Double.parseDouble(parts[0]);
                Double lon = Double.parseDouble(parts[1]);
                return new LatLng(lat, lon);
            } catch (NumberFormatException ex) {

            }
        }
        return null;
    }
}

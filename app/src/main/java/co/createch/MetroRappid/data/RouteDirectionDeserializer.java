package co.createch.MetroRappid.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import co.createch.MetroRappid.model.RouteDirection;

/**
 * Created by Seth Gholson on 5/4/14.
 */
public class RouteDirectionDeserializer implements JsonDeserializer<RouteDirection> {

    @Override
    public RouteDirection deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int typeInt = json.getAsInt();
        return RouteDirection.findByKey(typeInt);
    }
}

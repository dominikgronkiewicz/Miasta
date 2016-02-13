package pl.dominikgronkiewicz.miasta;

import android.database.CursorJoiner;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dominik on 05.02.16.
 */
public class CityClass {
    private List<Result> results;
    private String status;

    public List<Result> getResults() {
        return results;
    }
}

class Result {
    @SerializedName("address_components")
    private List<AddressComponent> addressComponents;

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

}

class AddressComponent {
    @SerializedName("long_name")
    private String longName;
    @SerializedName("short_name")
    String shortName;
    List<String> types;

    public String getLongName() {
        return longName;
    }
}

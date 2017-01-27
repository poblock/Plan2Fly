package pl.poblock.plan2fly.data.model;

/**
 * Created by krzysztof.poblocki on 2017-01-24.
 */

public class Miasto {
    private String name;
    private String code;
    private String country;
    private String latitude;
    private String longitude;

    public Miasto(String name, String code, String country, String latitude, String longitude) {
        this.name = name;
        this.code = code;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAutoCompleteLabel() { return getName()+" "+getCode(); }
}

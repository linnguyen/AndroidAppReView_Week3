package model;

/**
 * Created by lin on 26/12/2016.
 */

public class Place {
    private float lon;
    private float lat;
    private long sunset;
    private long sunrise;
    private String country;
    private String city;
    private long lastupdate;

    public Place() {
    }

    public Place(float lon, float lat, long sunset, long sunrise, String country, long lastupdate, String city) {
        this.lon = lon;
        this.lat = lat;
        this.sunset = sunset;
        this.sunrise = sunrise;
        this.country = country;
        this.lastupdate = lastupdate;
        this.city = city;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(long lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

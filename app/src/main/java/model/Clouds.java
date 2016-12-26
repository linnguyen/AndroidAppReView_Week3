package model;

/**
 * Created by lin on 26/12/2016.
 */

public class Clouds {
    private int precipitation;

    public Clouds() {
    }

    public Clouds(int precipitation) {
        this.precipitation = precipitation;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(int precipitation) {
        this.precipitation = precipitation;
    }
}

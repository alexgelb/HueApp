package app.hue.hueapp.app.hue.hueapp.enums;

public enum HueEnum {

    ON("on"),
    SATURATION("sat"),
    BRIGHTNESS("bri"),
    HUE("hue");

    private final String value;
    private HueEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

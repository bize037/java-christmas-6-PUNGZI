package christmas.common.constants;

public enum Unit {
    KOREA_MONEY("원");

    private final String unit;

    Unit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}

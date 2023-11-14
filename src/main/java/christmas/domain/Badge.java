package christmas.domain;

import java.util.function.Predicate;

public enum Badge {
    STAR("별", (price) -> price >= 5_000 && price < 10_000),
    TREE("트리", (price) -> price >= 10_000 && price < 20_000),
    SANTA("산타", (price) -> price >= 20_000);

    private final String name;
    private final Predicate<Integer> totalPrice;

    Badge(String name, Predicate<Integer> totalPrice) {
        this.name = name;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public static String getBadge(int price) {
        for (Badge badge : values()) {
            if (badge.totalPrice.test(price)) {
                return badge.getName();
            }
        }
        return "없음";
    }
}

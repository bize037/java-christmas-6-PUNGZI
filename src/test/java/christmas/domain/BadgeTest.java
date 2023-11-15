package christmas.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BadgeTest {
    @DisplayName("금액에 따라 배지 이름을 다르게 출력하는가")
    @CsvSource(value = {"0, '없음'", "4900, '없음'", "5000, '별'", "9900, '별'", "10000, '트리'", "19900, '트리'", "20000, '산타'", "39900, '산타'"}, delimiter = ',')
    @ParameterizedTest
    void starBadgeTest(int price, String badge) {
        String badgeName = Badge.getBadge(price);
        assertThat(badgeName).contains(badge);
    }
}

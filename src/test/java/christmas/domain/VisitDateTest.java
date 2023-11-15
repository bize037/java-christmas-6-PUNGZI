package christmas.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VisitDateTest {
    @DisplayName("입력한 날짜가 정상적으로 저장되는가")
    @ValueSource(strings = {"1", "15", "31"})
    @ParameterizedTest
    void saveVisitDateTest(String date) {
        VisitDate visitDate = new VisitDate(date);
        assertThat(visitDate.getDate()).isEqualTo(Integer.parseInt(date));
    }

    @DisplayName("공백을 입력하면 예외처리 하는가")
    @Test
    void blankValidateVisitDateTest() {
        String inBlank = "";
        assertThatThrownBy(() -> new VisitDate(inBlank))
                .isInstanceOf(IllegalArgumentException.class);
    }

}

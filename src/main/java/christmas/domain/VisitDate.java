package christmas.domain;

import christmas.common.validate.DateValidate;

public class VisitDate {
    private final int date;

    private static final String MONTH = "12월 ";
    private static final String DATE = "일";

    public VisitDate(String date) {
        validateVisitDate(date);
        this.date = Integer.parseInt(date);
    }

    private void validateVisitDate(String visitDate) {
        DateValidate.inBlank(visitDate);
        DateValidate.notNumber(visitDate);
        DateValidate.notRangeNumber(Integer.parseInt(visitDate));
    }

    public String monthAndDate() {
        return MONTH + date + DATE;
    }
}

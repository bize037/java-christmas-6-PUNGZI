package christmas.domain;

import christmas.common.validate.VisitDateValidate;

public class VisitDate {
    private final int date;

    public VisitDate(String date) {
        validateVisitDate(date);
        this.date = Integer.parseInt(date);
    }

    private void validateVisitDate(String visitDate) {
        VisitDateValidate.inBlank(visitDate);
        VisitDateValidate.notNumber(visitDate);
        VisitDateValidate.notRangeNumber(Integer.parseInt(visitDate));
    }

    public int getDate() {
        return date;
    }
}

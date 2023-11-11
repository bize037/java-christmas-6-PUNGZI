package christmas.domain;

import christmas.common.validate.DateValidate;

public class VisitDate {
    private final int date;

    public VisitDate(String date) {
        validateVisitDate(date);
        this.date = Integer.parseInt(date);
    }

    private void validateVisitDate(String visitDate) {
        DateValidate.inBlank(visitDate);
        DateValidate.notNumber(visitDate);
        DateValidate.notRangeNumber(Integer.parseInt(visitDate));
    }
}

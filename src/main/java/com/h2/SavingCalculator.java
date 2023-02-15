package com.h2;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SavingCalculator {

    private Float[] credits;

    private Float[] debits;

    public static void main (String[] args) {
        String[] creditsAsString = args[0].split(",");
        String[] debitsAsString = args[1].split(",");
        Float[] credits = new Float[creditsAsString.length];
        Float[] debits = new Float[debitsAsString.length];

        for(int i = 0; i < creditsAsString.length; i++) {
            credits[i] = Float.parseFloat(creditsAsString[i]);
        }
        for(int i = 0; i < debitsAsString.length; i++) {
            debits[i] = Float.parseFloat(debitsAsString[i]);
        }

        SavingCalculator calculator = new SavingCalculator(credits, debits);
        float netSavings = calculator.calculate();

        System.out.println("Net Savings = " + netSavings + ", remaining days in month = "
                + remainingDaysInMonth(LocalDate.now()));
    }

    public SavingCalculator(Float[] credits, Float[] debits) {
        this.credits = credits;
        this.debits = debits;
    }

    public float calculate() {
        return sumOfCredits() - sumOfDebits();
    }

    private float sumOfCredits() {
        return calculateSum(Arrays.asList(credits));
    }

    private float sumOfDebits() {
        return calculateSum(Arrays.asList(debits));
    }

    private float calculateSum(List<Float> values) {
        return values.stream().reduce(0.0f, (a, b) -> a + b);
    }

    private static int remainingDaysInMonth(LocalDate date) {
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());
        int totalDaysInMonth = yearMonth.lengthOfMonth();
        int remainingDays = totalDaysInMonth - date.getDayOfMonth();
        return remainingDays;
    }
}

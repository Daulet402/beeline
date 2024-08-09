package com.beeline.util;

import com.beeline.config.BalanceConfig;

public class BalanceAmountConverter {

    public String getBalanceMessage(double balance, BalanceConfig config) {
        double threshold = config.getThreshold();
        String comparisonOperator = config.getCompareOperation().get(0).getValue();

        boolean conditionMet = false;

        switch (comparisonOperator) {
            case "<":
                conditionMet = balance < threshold;
                break;
            case ">":
                conditionMet = balance > threshold;
                break;
            case "=":
                conditionMet = balance == threshold;
                break;
            case "<=":
                conditionMet = balance <= threshold;
                break;
            case ">=":
                conditionMet = balance >= threshold;
                break;
        }

        if (conditionMet) {
            return "У Вас на счету меньше " + threshold + " тенге. Рекомендуем пополнить баланс.";
        } else {
            return "Ваш баланс: " + balance + " тенге.";
        }
    }
}

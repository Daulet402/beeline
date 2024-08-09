package com.beeline.config;

import java.util.List;

public class BalanceConfig {
    private int threshold;
    private List<CompareOperation> compareOperation;

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public List<CompareOperation> getCompareOperation() {
        return compareOperation;
    }

    public void setCompareOperation(List<CompareOperation> compareOperation) {
        this.compareOperation = compareOperation;
    }
}

package com.misys.teamextrarice.mibudget.com.misys.teamextrarice.finances;

import java.util.ArrayList;

/**
 * Created by Edgar on 7/23/2015.
 */
public class SavingsAggregate {

    private ArrayList<Savings> savingsAggregate;

    public ArrayList<Savings> getSavingsAggregate() {
        return savingsAggregate;
    }

    public void setSavingsAggregate(ArrayList<Savings> savingsAggregate) {
        this.savingsAggregate = savingsAggregate;
    }

    public void addSavings(Savings savings) {
        savingsAggregate.add(savings);
    }
}

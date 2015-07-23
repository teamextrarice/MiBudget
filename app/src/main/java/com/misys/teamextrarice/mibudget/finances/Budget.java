package com.misys.teamextrarice.mibudget.finances;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Edgar on 7/23/2015.
 */
public class Budget {

    private ArrayList<BudgetDaily> budget;

    public ArrayList<BudgetDaily> getBudget() {
        return budget;
    }

    public void setBudget(ArrayList<BudgetDaily> budget) {
        this.budget = budget;
    }

    public void addBudgetDaily(BudgetDaily budgetDaily) {
        budget.add(budgetDaily);
    }

    public BigDecimal totalIncome() {
        BigDecimal income = BigDecimal.ZERO;
        for (BudgetDaily budgetDaily: budget) {
            if (budgetDaily.getType() .equals("INCOME")) {
                income = income.add(new BigDecimal(budgetDaily.getAmount()));
            }
        }
        return income;
    }
    public BigDecimal totalExpense() {
        BigDecimal expense = BigDecimal.ZERO;
        for (BudgetDaily budgetDaily: budget) {
            if (budgetDaily.getType() .equals("EXPENSE")) {
                expense = expense.add(new BigDecimal(budgetDaily.getAmount()));
            }
        }
        return expense;
    }
    public BigDecimal totalDaily() {
        BigDecimal daily = BigDecimal.ZERO;
        for (BudgetDaily budgetDaily: budget) {
            if (budgetDaily.getType() .equals("DAILY")) {
                daily = daily.add(new BigDecimal(budgetDaily.getAmount()));
            }
        }
        return daily;
    }
}

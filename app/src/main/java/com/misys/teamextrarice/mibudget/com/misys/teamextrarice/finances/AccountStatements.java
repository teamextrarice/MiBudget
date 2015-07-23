package com.misys.teamextrarice.mibudget.com.misys.teamextrarice.finances;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Edgar on 7/23/2015.
 */
public class AccountStatements {

    private ArrayList<AccountStatement> accountStatements;

    public ArrayList<AccountStatement> getAccountStatement() {
        return accountStatements;
    }
    public void setAccountStatement(ArrayList<AccountStatement> accountStatements) {
        this.accountStatements = accountStatements;
    }

    public void addAccountStatement(AccountStatement accountStatement) {
        accountStatements.add(accountStatement);
    }

    public BigDecimal totalExpenses() {
        BigDecimal expenses = BigDecimal.ZERO;
        for (AccountStatement accountStatement: accountStatements) {
                if (accountStatement.getStatementType().equals("EXPENSE")) {
                        expenses = expenses.add(new BigDecimal(accountStatement.getAmount()));
                }
        }
        return expenses;
    }

    public BigDecimal totalIncome() {
        BigDecimal income = BigDecimal.ZERO;
        for (AccountStatement accountStatement: accountStatements) {
            if (accountStatement.getStatementType().equals("INCOME")) {
                income = income.add(new BigDecimal(accountStatement.getAmount()));
            }
        }
        return income;
    }
}



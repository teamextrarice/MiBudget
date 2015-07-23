package com.misys.teamextrarice.mibudget.finances;

/**
 * Created by Edgar on 7/23/2015.
 */
public class BudgetDaily {
    private String budgetId; // budget id
    private String partyId;  // name of user
    private String type;  // CREDIT / DEBIT
    private String transactionDate;  // pass of user
    private  String amount; // account number of user
    private  String commnets;  // cutoff date of user

    public String getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(String budgetId) {
        this.budgetId = budgetId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCommnets() {
        return commnets;
    }

    public void setCommnets(String commnets) {
        this.commnets = commnets;
    }
}

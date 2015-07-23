package com.misys.teamextrarice.mibudget.finances;

/**
 * Created by Edgar on 7/23/2015.
 */
public class Savings {

    private String savingsId; // id value for savings
    private String partyId;  // id of user
    private String transactionDate;  // date of transaction
    private String amount; // savings amount
    private String comments;  // comments for the entry

    public String getSavingsId() {
        return savingsId;
    }

    public void setSavingsId(String savingsId) {
        this.savingsId = savingsId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}

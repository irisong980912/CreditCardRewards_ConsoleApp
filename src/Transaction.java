public class Transaction {
    private String transactionName;
    private String date;
    private String merchantCode;
    private int amountCents;
    private TransactionLevelRule rule;

    public Transaction(String transactionName, String date, String merchantCode, int amountCents) {
        this.transactionName = transactionName;
        this.date = date;
        this.merchantCode = merchantCode;
        this.amountCents = amountCents;

        setRule();
    }

    private void setRule() {
        // set the rule for this transaction
        if (this.merchantCode.equals(MerchantCode.SPORT_CHECK)) {
            this.rule = SportcheckRule.getInstance();
        } else {
            this.rule = OtherRule.getInstance();
        }
    }

    public TransactionLevelPoint getTransactionLevelPoint() {
        // calculate the point
        int levelPoint = this.rule.calculatePoint(this);
        TransactionLevelPoint transactionLevelPoint = new TransactionLevelPoint(
                                                        this.transactionName,
                                                        levelPoint);

        return transactionLevelPoint;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionName='" + transactionName + '\'' +
                ", date='" + date + '\'' +
                ", merchantCode='" + merchantCode + '\'' +
                ", amountCents=" + amountCents +
                '}';
    }

    /**
     * Getter for transaction name
     * @return transaction name
     */
    public String getTransactionName() {
        return transactionName;
    }

    /**
     * Getter for date
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter for merchant code
     * @return merchant code
     */
    public String getMerchantCode() {
        return merchantCode;
    }

    /**
     * Getter for amount cents
     * @return amount cents
     */
    public int getAmountCents() {
        return amountCents;
    }

    public TransactionLevelRule getRule() {
        return rule;
    }
}

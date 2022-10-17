public class Transaction {
    String transactionName;
    String date;
    String merchantCode;
    int amountCents;

    public Transaction(String transactionName, String date, String merchantCode, int amountCents) {
        this.transactionName = transactionName;
        this.date = date;
        this.merchantCode = merchantCode;
        this.amountCents = amountCents;
    }

    /**
     * A recursive helper function that helps calculates transaction-level points based on Rule 6 and 7.
     * @param amountDollar amount in dollar
     * @param isSportCheck if the merchant_code of the transaction is sportcheck
     * @return transaction level point
     */
    private int transLevelPointsHelper(int amountDollar, boolean isSportCheck) {
        int points;

        if ((amountDollar >= 20) && isSportCheck) { // rule 6: 75 points for $20 sportcheck
            points = 75 + transLevelPointsHelper(amountDollar - 20, true);

        } else{
            points = amountDollar;
        }

        return points;
    }

    /**
     * A function that calculates transaction-level points based on Rule 6 and 7.
     * @return transaction level point
     */
    public int calculateTransLevelPoints() {

        return transLevelPointsHelper(this.amountCents / 100,
                this.merchantCode.equals(MerchantCode.SPORT_CHECK));

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
}

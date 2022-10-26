import java.util.ArrayList;
import java.util.List;

public class MonthlyReport extends RewardsReport {

    /** total maximum rewards point for the requested month */
    private int maximum_monthly_rewards_point;

    /** maximum points for each individual transaction in the month */
    private List<TransactionLevelPoint> transaction_level_points_list;

    private RewardsCalculator calculator;

    public MonthlyReport(List<Transaction> transactionList) {
        this.makeReport(transactionList);
    }

    /**
     * Makes monthly report based on a list of transactions
     * @param transactionList
     * @return monthly report which has the total rewards point as well as the transaction-level points
     */
    @Override
    protected void makeReport(List<Transaction> transactionList) {

        RewardsCalculator calculator = MonthlyRewardsCalculator.getInstance();
        this.calculator = calculator;
        int maxMonthlyPoint = calculator.calculateRewards(transactionList);

        List<TransactionLevelPoint> levelPointList = new ArrayList<>();
        for (Transaction transaction : transactionList) { // iterate and calculate points for each transaction
            int levelPoint = transaction.calculateTransLevelPoints();
            TransactionLevelPoint transactionLevelPoint = new TransactionLevelPoint(
                    transaction.getTransactionName(),
                    levelPoint);
            levelPointList.add(transactionLevelPoint);
        }

        this.maximum_monthly_rewards_point = maxMonthlyPoint;
        this.transaction_level_points_list = levelPointList;

    }

    @Override
    public String toString() {
        return "Total Points: " + maximum_monthly_rewards_point + "\n" +
                "Transaction Level Points: \n" + transaction_level_points_list;
    }
}

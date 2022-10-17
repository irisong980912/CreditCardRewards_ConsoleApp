import java.util.List;

public class MonthlyReport {

    /** total maximum rewards point for the requested month */
    private int maximum_monthly_rewards_point;

    /** maximum points for each individual transaction in the month */
    private List<TransactionLevelPoint> transaction_level_points_list;

    public MonthlyReport(int maximum_monthly_rewards_point, List<TransactionLevelPoint> transaction_level_point_list) {
        this.maximum_monthly_rewards_point = maximum_monthly_rewards_point;
        this.transaction_level_points_list = transaction_level_point_list;
    }

    @Override
    public String toString() {
        return "Total Points: " + maximum_monthly_rewards_point + "\n" +
                "Transaction Level Points: \n" + transaction_level_points_list;
    }
}

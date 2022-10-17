import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MonthlyRewardsCalculator {

    /**
     * Makes monthly report based on a list of transactions
     * @param transactionList
     * @return monthly report which has the total rewards point as well as the transaction-level points
     */
    public static MonthlyReport makeReport(List<Transaction> transactionList) {

        int maxMonthlyPoint = MonthlyRewardsCalculator.calculateMaxMonthlyPoint(transactionList);

        List<TransactionLevelPoint> levelPointList = new ArrayList<>();
        for (Transaction transaction : transactionList) { // iterate and calculate points for each transaction
            int levelPoint = transaction.calculateTransLevelPoints();
            TransactionLevelPoint transactionLevelPoint = new TransactionLevelPoint(
                                                                transaction.getTransactionName(),
                                                                levelPoint);
            levelPointList.add(transactionLevelPoint);
        }

        return new MonthlyReport(maxMonthlyPoint, levelPointList);

    }

    /** ================================== start of helper ================================== */

    /**
     * A helper function that aggregates the monthly expenditure for each merchant.
     * @param transactions
     * @return a hashmap
     */
    private static HashMap<String, Integer> aggregateMonthlyTrans(List<Transaction> transactions) {

        List<String> merchantsForReward = MerchantCode.makeRewardMerchantList(); // gets a list of all the merchants mentioned in the rules

        HashMap<String, Integer> result = new HashMap<>();

        for (Transaction t : transactions) {

            String merchantCode = t.getMerchantCode();

            if (!merchantsForReward.contains(merchantCode)) { // if not in the list, then count towards "other" to apply Rule 7
                merchantCode = MerchantCode.OTHER;
            }

            int newAmountCents = result.getOrDefault(merchantCode, 0) + t.getAmountCents();
            result.put(merchantCode, newAmountCents);
        }

        return result;
    }

    /**
     * A greedy helper function for calculating the maximum points earned for the month
     * Rule 3 and Rule 5 are ignored because they are not as cost-efficient as other rules combined.
     * For reasons of elimination, please refer to README.md
     *
     * @param spAmt dollar amount left for sportcheck
     * @param thAmt dollar amount left for tim-hortons
     * @param subwayAmt dollar amount left for subway
     * @return maximum monthly reward point
     */
    private static int maximizeMonthlyRecursive(int spAmt, int thAmt, int subwayAmt) {
        // base case: all of them are 0
        if ((spAmt == 0) && (thAmt == 0) && (subwayAmt == 0)) {
            return 0;
        }

        int points;

        if (spAmt >= 75 && thAmt >= 25 && subwayAmt >= 25) { // rule 1: 500 points for sportcheck $75,  timHortons $25, subway $25

            points = 500 + maximizeMonthlyRecursive(spAmt - 75, thAmt - 25, subwayAmt - 25);

        } else if (spAmt >= 75 && thAmt >= 25 && subwayAmt < 25) { // rule 2: 300 points for sportcheck $75,  timHortons $25

            points = 300 + maximizeMonthlyRecursive(spAmt - 75, thAmt - 25, subwayAmt);

        } else if (spAmt >= 25 && thAmt >= 10 && subwayAmt >= 10) { // rule 4: 150 points for sportcheck $25,  timHortons $10, subway $10

            points = 150 + maximizeMonthlyRecursive(spAmt - 25, thAmt - 10, subwayAmt - 10);

        } else if (spAmt >= 20) { // rule 6: 75 points for sportcheck $20

            points = 75 + maximizeMonthlyRecursive(spAmt - 20, thAmt, subwayAmt);

        } else { // rule 7: 1 point for the remaining.

            points = spAmt + thAmt + subwayAmt;

        }

        return points;
    }

    /**
     * A helper function for calculating the maximum points earned for the month
     *
     * @param transactionList
     * @return maximum monthly reward point
     */
    private static int calculateMaxMonthlyPoint(List<Transaction> transactionList) {

        HashMap<String, Integer> map = aggregateMonthlyTrans(transactionList); // aggregates all amounts for distinct merchants together

        int spAmt = map.getOrDefault(MerchantCode.SPORT_CHECK, 0); // sports check
        int thAmt = map.getOrDefault(MerchantCode.TIM_HORTONS, 0);; // timHortons
        int subwayAmt = map.getOrDefault(MerchantCode.SUBWAY, 0); // subway
        int otherAmt = map.getOrDefault(MerchantCode.OTHER, 0);

        // get the cents
        int remainingCents = spAmt % 100 + thAmt % 100 + subwayAmt % 100 + otherAmt % 100;

        // get the points earned based on rules
        int rulePoints = maximizeMonthlyRecursive(spAmt / 100, thAmt / 100, subwayAmt / 100);

        // add rule points, other points, and remaining cents together
        return rulePoints + otherAmt / 100 + remainingCents / 100;
    }

    /** ================================== end of helper ================================== */
}

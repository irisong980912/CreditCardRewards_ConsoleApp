import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CreditCardRewardsApp {

    /**
     * Reads transaction list info from file `transactionList.txt` and then print the monthly rewards info
     * @param args
     */
    public static void main(String[] args) throws IOException {

        ArrayList<Transaction> transactionList = new ArrayList<>();

        File file = new File("./src/transactionList.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) { // reads line by line and then construct Transaction objects.
            String[] transArgs = line.replaceAll(",", "").split(" ");
            Transaction transaction = new Transaction(transArgs[0],
                    transArgs[1],
                    transArgs[2],
                    Integer.valueOf(transArgs[3]));
            transactionList.add(transaction);
        }

        br.close();

        RewardsReport report = new MonthlyReport(transactionList);
        System.out.println(report);
    }

}

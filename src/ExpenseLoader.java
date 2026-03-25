import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class ExpenseLoader {
    public static ExpenseTracker loadExpenses() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/expenses.txt"));
        String line;
        ExpenseTracker tracker = null;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("BUDGET:")) {
                String[] parts = line.split(":");
                tracker = new ExpenseTracker(Double.parseDouble(parts[1]));
            } else if (line.startsWith("EXPENSE:")) {
                String[] parts = line.split(":");
                Expense currentExpense = new Expense(Double.parseDouble(parts[1]), parts[2], Category.valueOf(parts[4]), LocalDate.parse(parts[3]));
                tracker.addExpense(currentExpense);
            }
        }
        return tracker;
    }
}

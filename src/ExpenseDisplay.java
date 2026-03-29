import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ExpenseDisplay {
    public static void expenseDisplay(ExpenseTracker expenses) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/expenses.txt"));
        writer.write("BUDGET:" + expenses.getBudget());
        writer.newLine();
        for (Expense expense : expenses.getExpenses()) {
            writer.write("EXPENSE:" + expense.getAmount() + ":" + expense.getRecipient() + ":" + expense.getDate() + ":" + expense.getCategory());
            writer.newLine();
        }
        writer.newLine();
        writer.close();
    }
}

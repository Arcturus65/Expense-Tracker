import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        ExpenseTracker expenses = null;
        double userBudget = 0;
        try {
            ExpenseTracker loadedExpenses = ExpenseLoader.loadExpenses();
            expenses.expenseLoader();
        } catch (Exception e) {
            System.out.println("No expenses found.");
        }
        while(true) {
            System.out.println("Expenses Tracker Menu:");
            System.out.println("1. Set Budget.");
            System.out.println("2. Add Expense");
            System.out.println("3. Display Expenses");
            System.out.println("4. Filter expenses by category");
            System.out.println("5. Filter expenses by amount");
            System.out.println("6. Budget over expenses");
            System.out.println("7. Exit");
            int userInput = input.nextInt(); input.nextLine();
            if (userInput == 1) {
                System.out.println("Please enter the overall budget allocated:");
                System.out.println();
                userBudget = input.nextDouble();
                System.out.println("User's Budget: " + userBudget);
                expenses = new ExpenseTracker(userBudget);

            } else if (userInput == 2) {
                if (expenses != null) {
                    System.out.println("Please enter the expense amount:");
                    double expenseAmount = input.nextDouble();
                    input.nextLine();
                    System.out.println("Enter recipient of the expense:");
                    String recipient = input.nextLine();
                    System.out.println("Here's the list of expense categories:");
                    for (Category category : Category.values()) {
                        System.out.println(category);
                    }
                    System.out.println("Select category of the expense:");
                    Category category = Category.valueOf(input.nextLine().toUpperCase());
                    Expense expense = new Expense(expenseAmount, recipient, category);
                    expenses.addExpense(expense);
                } else {
                    userBudget = budgetForce(input);
                    expenses = new ExpenseTracker(userBudget);
                }
            } else if (userInput == 3) {
                    if (expenses != null) {
                        expenses.viewExpenses();
                        System.out.println();
                    } else {
                        userBudget = budgetForce(input);
                        expenses = new ExpenseTracker(userBudget);
                    }
            } else if (userInput == 4) {
                if (expenses != null) {
                    System.out.println("Please select expense category:");
                    Category category = Category.valueOf(input.nextLine().toUpperCase());
                    expenses.filterByCategory(category);
                } else {
                    userBudget = budgetForce(input);
                    expenses = new ExpenseTracker(userBudget);
                }
            } else if (userInput == 5) {
                if (expenses != null) {
                    System.out.println("Please select expense amount:");
                    double expenseAmount = input.nextDouble();
                    expenses.filterByAmount(expenseAmount);
                } else {
                    userBudget = budgetForce(input);
                    expenses = new ExpenseTracker(userBudget);
                }
            } else if (userInput == 6) {
                if (expenses != null) {
                    expenses.budgetCompare();
                } else {
                    userBudget = budgetForce(input);
                    expenses = new ExpenseTracker(userBudget);
                }
            } else {
                ExpenseDisplay.expenseDisplay(expenses);
                break;
            }
        }
    }

    public static double budgetForce(Scanner input) {
        System.out.println("Please set a budget first:");
        double userBudget = input.nextDouble();
        System.out.println("Budget set to: " + userBudget);
        return userBudget;
    }
}

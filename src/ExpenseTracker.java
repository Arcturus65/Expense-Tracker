import java.util.ArrayList;

public class ExpenseTracker {
    private ArrayList<Expense> expenses;
    private double budget;

    public ExpenseTracker(double budget) {
        this.budget = budget;
        this.expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void viewExpenses() {
        if (expenses.size() == 0) {
            System.out.println("No expenses found.");
        } else {
            System.out.println("You expenses are the following:");
            for (Expense expense : expenses) {
                System.out.println("Amount expended is: " +expense.getAmount() + " lei.");
                System.out.println("Category of: " + expense.getCategory());
                System.out.println("Date of payment: " + expense.getDate());
                System.out.println("Paid towards: " + expense.getRecipient());
                System.out.println();
            }
        }
    }

    public void filterByCategory (Category category) {
        double amountSpent = 0;
        if (expenses.size() == 0) {
            System.out.println("No expenses found.");
        } else {
            boolean expenseFound = false;
            System.out.println();
            for (Expense expense : expenses) {
                if (expense.getCategory() == category) {
                    if (!expenseFound) {
                        System.out.println("The expenses of the category " + category + " are:");
                        expenseFound = true;
                    }
                    System.out.println("Amount expended is: " + expense.getAmount() + " lei.");
                    System.out.println("Date of payment: " + expense.getDate());
                    System.out.println("Paid towards: " + expense.getRecipient());
                    amountSpent += expense.getAmount();
                    System.out.println();
                }
            }
            if (expenseFound) {
                System.out.println("Total amount spent in this category is: " + amountSpent);
                System.out.println();
            } else {
                System.out.println("No expenses found.");
            }
        }
    }

    public void filterByAmount (double amount) {
        if (expenses.size() == 0) {
            System.out.println("No expenses found.");
        } else {
            boolean expenseFound = false;
            double amountSpent = 0;
            int count = 0;
            for (Expense expense : expenses) {
                if (expense.getAmount() >= amount) {
                    if (!expenseFound) {
                        System.out.println("The expenses of the amount " + amount + " are:");
                        expenseFound = true;
                    }
                    System.out.println("Amount expended is: " + expense.getAmount() + " lei.");
                    System.out.println("Date of payment: " + expense.getDate());
                    System.out.println("Paid towards: " + expense.getRecipient());
                    System.out.println();
                    amountSpent += expense.getAmount();
                    count++;
                }
            }
            if (expenseFound) {
                System.out.println("Total amount spent in this category is: " + amountSpent);
                System.out.println("You've made " + count + " payments over " + amount + " lei.");

            } else {
                System.out.println("No expenses found.");
            }
        }
    }
}

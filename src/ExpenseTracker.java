import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public double getBudget() {
        return budget;
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            System.out.println("You expenses are the following:");
            for (Expense expense : expenses) {
                System.out.println("Amount expended is: " + expense.getAmount() + " lei.");
                System.out.println("Category of: " + expense.getCategory());
                System.out.println("Date of payment: " + expense.getDate());
                System.out.println("Paid towards: " + expense.getRecipient());
                System.out.println();
            }
        }
    }

    public void filterByCategory(Category category) {
        double amountSpent = 0;
        if (expenses.isEmpty()) {
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

    public void filterByAmount(double amount) {
        if (expenses.isEmpty()) {
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

    public void budgetCompare() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            double amountSpent = 0;
            double delta;
            for (Expense expense : expenses) {
                amountSpent += expense.getAmount();
            }
            System.out.println("Amount expended is: " + amountSpent + " lei.");
            if (amountSpent > budget) {
                delta = amountSpent - budget;
                System.out.println("You've exceeded your budget by " + delta + " lei.");
            } else {
                delta = budget - amountSpent;
                System.out.println("You've spent " + amountSpent + " lei with " + delta + " to spare.");
            }
        }
    }

    public void expenseLoader() {
        this.expenses.addAll(expenses);
    }

    public void sortByAmount(boolean isReversed) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            if (!isReversed) {
                Collections.sort(expenses, Comparator.comparingDouble(Expense::getAmount));
            } else {
                Collections.sort(expenses, Comparator.comparingDouble(Expense::getAmount).reversed());
            }
            for (Expense expense : expenses) {
                System.out.println("Amount expended is: " + expense.getAmount() + " lei.");
                System.out.println("Category of: " + expense.getCategory());
                System.out.println("Date of payment: " + expense.getDate());
                System.out.println("Paid towards: " + expense.getRecipient());
                System.out.println();
            }
        }
    }

    public void sortByDate (boolean isReversed) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            if (!isReversed) {
                Collections.sort(expenses, Comparator.comparing(Expense::getDate));
            } else {
                Collections.sort(expenses, Comparator.comparing(Expense::getDate).reversed());
            }
            for (Expense expense : expenses) {
                System.out.println("Amount expended is: " + expense.getAmount() + " lei.");
                System.out.println("Category of: " + expense.getCategory());
                System.out.println("Date of payment: " + expense.getDate());
                System.out.println("Paid towards: " + expense.getRecipient());
                System.out.println();
            }
        }
    }

    public void monthlyReport(int month, int year) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            double total = 0;
            for (Expense expense : expenses) {
                if (expense.getDate().getMonthValue() == month && expense.getDate().getYear() == year) {
                    total += expense.getAmount();
                }
            }

            System.out.println("You've spent " + total + " lei in month " + month + ".");
            if (total > budget) {
                System.out.println("You've exceeded your budget by " + (total - budget) + " lei.");
            } else {
                System.out.println("You managed to stay within budget range with " + (budget - total) + " lei remaining of your budget.");
            }
        }
    }
}

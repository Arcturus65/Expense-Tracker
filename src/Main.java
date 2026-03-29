import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        ExpenseTracker expenses = null;
        double userBudget = 0;
        try {
            expenses = ExpenseLoader.loadExpenses();
        } catch (Exception e) {
            System.out.println("No previous expenses found. Starting fresh.");
        }
        while(true) {
            System.out.println("Expenses Tracker Menu:");
            System.out.println("1. Set Budget.");
            System.out.println("2. Add Expense");
            System.out.println("3. Display Expenses");
            System.out.println("4. Filter expenses by category");
            System.out.println("5. Filter expenses by amount");
            System.out.println("6. Sort expenses by amount");
            System.out.println("7. Sort expenses by date");
            System.out.println("8. Budget over expenses");
            System.out.println("9. Monthly/Yearly report");
            System.out.println("10. Exit");
            int userInput = safeIntInput(input);
            if (userInput < 1 || userInput > 10 ) {
                System.out.println("Please select from the available options.");
                continue;
            }
            if (userInput == 1) {
                System.out.println("Please enter the overall budget allocated:");
                System.out.println();
                userBudget = safeDoubleInput(input);
                System.out.println("User's Budget: " + userBudget);
                expenses = new ExpenseTracker(userBudget);

            } else if (userInput == 2) {
                if (expenses != null) {
                    System.out.println("Please enter the expense amount:");
                    double expenseAmount = safeDoubleInput(input);
                    System.out.println("Enter recipient of the expense:");
                    String recipient = input.nextLine();
                    System.out.println("Here's the list of expense categories:");
                    for (Category category : Category.values()) {
                        System.out.println(category);
                    }
                        while (true) {
                            try {
                                System.out.println("Please select expense category:");
                                Category category = Category.valueOf(input.nextLine().toUpperCase());
                                Expense expense = new Expense(expenseAmount, recipient, category);
                                expenses.addExpense(expense);
                                break;
                            } catch (Exception e) {
                                System.out.println("Invalid category.");
                            }
                        }
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
                    while (true) {
                        try {
                            System.out.println("Please select expense category:");
                            Category category = Category.valueOf(input.nextLine().toUpperCase());
                            expenses.filterByCategory(category);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid category.");
                        }
                    }
                } else {
                    userBudget = budgetForce(input);
                    expenses = new ExpenseTracker(userBudget);
                }
            } else if (userInput == 5) {
                if (expenses != null) {
                    System.out.println("Please select expense amount:");
                    double expenseAmount = safeDoubleInput(input);
                    expenses.filterByAmount(expenseAmount);
                } else {
                    userBudget = budgetForce(input);
                    expenses = new ExpenseTracker(userBudget);
                }
            } else if (userInput == 6) {
                if (expenses != null) {
                    System.out.println("Do you wish the list to be reversed?");
                    String userReversed = input.nextLine();
                    if (userReversed.equalsIgnoreCase("yes")) {
                        expenses.sortByAmount(true);
                    }
                    else {
                        expenses.sortByAmount(false);
                    }
                } else {
                    userBudget = budgetForce(input);
                    expenses = new ExpenseTracker(userBudget);
                }
            } else if  (userInput == 7) {
                if (expenses != null) {
                    System.out.println("Do you wish the list to be reversed?");
                    String userReversed = input.nextLine();
                    if (userReversed.equalsIgnoreCase("yes")) {
                        expenses.sortByDate(true);
                    } else {
                        expenses.sortByDate(false);
                    }
                } else {
                    userBudget = budgetForce(input);
                    expenses = new ExpenseTracker(userBudget);
                }

            } else if (userInput == 8) {
                if (expenses != null) {
                    expenses.budgetCompare();
                } else {
                    userBudget = budgetForce(input);
                    expenses = new ExpenseTracker(userBudget);
                }
            } else if(userInput == 9) {
                if (expenses != null) {
                    System.out.println("Please input the month:");
                    int month = safeIntInput(input);
                    System.out.println("Please input the year:");
                    int year = safeIntInput(input);
                    expenses.monthlyReport(month, year);
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
        double userBudget = safeDoubleInput(input);
        System.out.println("Budget set to: " + userBudget);
        return userBudget;
    }

    public static int safeIntInput(Scanner input) {
        while (true) {
            int userInput;
            try {
                userInput = input.nextInt(); input.nextLine();
                return userInput;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                input.nextLine();
            }
        }
    }

    public static double safeDoubleInput(Scanner input) {
        while (true) {
            double userInput;
            try {
                userInput = input.nextDouble(); input.nextLine();
                return userInput;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                input.nextLine();
            }
        }
    }
}

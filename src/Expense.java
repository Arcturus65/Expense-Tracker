import java.time.LocalDate;

public class Expense {
    private double amount;
    private String recipient;
    private Category category;
    private LocalDate date;

    public Expense(double amount, String recipient, Category category) {
        this.amount = amount;
        this.recipient = recipient;
        this.category = category;
        this.date = LocalDate.now();
    }

    public Expense(double amount, String recipient, Category category, LocalDate date) {
        this.amount = amount;
        this.recipient = recipient;
        this.date = date;
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public String getRecipient() {
        return recipient;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }
}


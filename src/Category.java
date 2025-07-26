import java.util.*;

class Category {

    private final String name;
    private double budget;
    private final List<Expense> expenses = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void editExpense(int index, String newDesc, double newAmount) {

        if (index >= 0 && index < expenses.size()) {
            Expense e = expenses.get(index);
            e.setDescription(newDesc);
            e.setAmount(newAmount);
            System.out.println("\nExpense updated.");

        } else {

            System.out.println("Invalid index.");

        }
    }

    public void removeExpense(int index) {

       if (index >= 0 && index < expenses.size()) {

            expenses.remove(index);
            System.out.println("\nExpense removed.");

        } else {

            System.out.println("Invalid index.");

        }
    }

    public void listExpenses() {

        for (int i = 0; i < expenses.size(); i++) {

            Expense e = expenses.get(i);
            System.out.printf("[%d] %s - %.2f%n", i, e.getDescription(), e.getAmount());

        }
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public double totalExpenses() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public double getBudget() {
        return budget;
    }

    public String getName() {
        return name;
    }
}

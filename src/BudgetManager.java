import java.util.*;

class BudgetManager {
    Map<String, Category> categories = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public void addCategory() {
        System.out.print("Enter category name: ");
        String name = scanner.nextLine().trim();
        if (categories.containsKey(name)) {
            System.out.println("Category already exists.");
        } else {
            categories.put(name, new Category(name));
            System.out.println("Category added.");
        }
    }

    private Category selectCategory() {
        if (categories.isEmpty()) {
            System.out.println("No categories available.");
            return null;
        }

        List<String> names = new ArrayList<>(categories.keySet());
        for (int i = 0; i < names.size(); i++) {
            System.out.printf("[%d] %s%n", i, names.get(i));
        }

        System.out.print("Select a category index: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            if (index >= 0 && index < names.size()) {
                return categories.get(names.get(index));
            } else {
                System.out.println("Invalid index.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return null;
        }
    }

    public void setBudget() {
        Category cat = selectCategory();
        if (cat == null) return;

        System.out.print("Enter budget amount: ");
        try {
            double budget = Double.parseDouble(scanner.nextLine());
            cat.setBudget(budget);
            System.out.println("Budget set.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
        }
    }

    public void addExpense() {
        Category cat = selectCategory();
        if (cat == null) return;

        System.out.print("Enter expense description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter amount: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            cat.addExpense(new Expense(desc, amount));
            System.out.println("Expense added.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
        }
    }

    public void editExpense() {
        Category cat = selectCategory();
        if (cat == null || cat.getExpenses().isEmpty()) {
            System.out.println("No expenses to edit.");
            return;
        }

        cat.listExpenses();
        System.out.print("Enter expense index to edit: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine());
            System.out.print("New description: ");
            String newDesc = scanner.nextLine();
            System.out.print("New amount: ");
            double newAmount = Double.parseDouble(scanner.nextLine());
            cat.editExpense(idx, newDesc, newAmount);
        } catch (Exception e) {
            System.out.println("Error editing expense.");
        }
    }

    public void removeExpense() {
        Category cat = selectCategory();
        if (cat == null || cat.getExpenses().isEmpty()) {
            System.out.println("No expenses to remove.");
            return;
        }

        cat.listExpenses();
        System.out.print("Enter expense index to remove: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine());
            cat.removeExpense(idx);
        } catch (Exception e) {
            System.out.println("Error removing expense.");
        }
    }

    public void viewExpenses() {
        double total = 0;
        for (Category c : categories.values()) {
            System.out.printf("Category: %s | Budget: %.2f | Spent: %.2f%n", c.getName(), c.getBudget(), c.totalExpenses());
            c.listExpenses();
            total += c.totalExpenses();
        }
        System.out.printf("Total Expenses: %.2f%n", total);
    }
}

import java.util.*;

class BudgetManager {

    Map<String, Category> categories = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public void addCategory() {

        System.out.print("Enter category name: ");
        String name = scanner.nextLine().trim();

        if (categories.containsKey(name)) {

            System.out.println("\nCategory already exists.");

        } else {

            categories.put(name, new Category(name));
            System.out.println("\nCategory added.");

        }
    }

    private Category selectCategory() {

        if (categories.isEmpty()) {

            System.out.println("\nNo categories available.");
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

            if (budget < 0) {
                System.out.println("Budget cannot be negative.");
                return;

            }
            cat.setBudget(budget);
            System.out.println("\nBudget set.");

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

            if (amount < 0) {

                System.out.println("Expense amount cannot be negative.");
                return;

            }
            cat.addExpense(new Expense(desc, amount));
            System.out.println("\nExpense added.");

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

        System.out.println("View Options:\n1. View All Categories\n2. View Specific Category");
        System.out.print("Choose an option: ");

        String input = scanner.nextLine();

        if (input.equals("1")) {

            double total = 0;
            for (Category c : categories.values()) {
                System.out.printf("\nCategory: %s | Budget: %.2f | Spent: %.2f%n", c.getName(), c.getBudget(), c.totalExpenses());
                c.listExpenses();
                total += c.totalExpenses();

            }
            System.out.printf("Total Expenses: %.2f%n", total);

        } else if (input.equals("2")) {

            Category cat = selectCategory();

            if (cat == null) return;

            System.out.printf("\nCategory: %s | Budget: %.2f | Spent: %.2f%n", cat.getName(), cat.getBudget(), cat.totalExpenses());
            cat.listExpenses();

        } else {

            System.out.println("Invalid option.");

        }
    }

}

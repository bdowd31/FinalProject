import java.util.*;

public class ExpenseTracker {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BudgetManager manager = new BudgetManager();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n1. Add Category");
            System.out.println("2. Set Budget");
            System.out.println("3. Add Expense");
            System.out.println("4. Edit Expense");
            System.out.println("5. Remove Expense");
            System.out.println("6. View Totals");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            try {

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {

                    case 1 -> manager.addCategory();
                    case 2 -> manager.setBudget();
                    case 3 -> manager.addExpense();
                    case 4 -> manager.editExpense();
                    case 5 -> manager.removeExpense();
                    case 6 -> manager.viewExpenses();
                    case 7 -> System.exit(0);

                    default -> System.out.println("Invalid choice.");

                }
            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");

            }
        }
    }
}

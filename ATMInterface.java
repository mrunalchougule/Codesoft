import java.util.Scanner;

// Bank Account class to store balance and perform transactions
class BankAccount {
    private double balance;

    // Constructor to initialize balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Successfully deposited ₹" + amount);
        } else {
            System.out.println("❌ Invalid deposit amount! Please enter a positive value.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ Successfully withdrew ₹" + amount);
        } else if (amount > balance) {
            System.out.println("❌ Insufficient balance! Transaction failed.");
        } else {
            System.out.println("❌ Invalid withdrawal amount! Please enter a positive value.");
        }
    }

    // Check balance method
    public void checkBalance() {
        System.out.println("💰 Current balance: ₹" + balance);
    }
}

// ATM class to handle user interactions
class ATM {
    private BankAccount account;
    private Scanner scanner;

    // Constructor to associate an account with the ATM
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Display menu and handle user choices
    public void start() {
        while (true) {
            System.out.println("\n===== 🏧 ATM Menu =====");
            System.out.println("1️⃣ Deposit");
            System.out.println("2️⃣ Withdraw");
            System.out.println("3️⃣ Check Balance");
            System.out.println("4️⃣ Exit");
            System.out.print("👉 Choose an option: ");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("❌ Invalid input! Please enter a number.");
                scanner.next(); // Consume invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("💵 Enter deposit amount: ₹");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("💵 Enter withdrawal amount: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    System.out.println("📢 Thank you for using the ATM. Goodbye!");
                    scanner.close(); // Close scanner before exiting
                    return; // Exit program
                default:
                    System.out.println("❌ Invalid choice! Please select a valid option.");
            }
        }
    }
}

// Main class to run the ATM program
public class ATMInterface {
    public static void main(String[] args) {
        // Initializing account with a starting balance of ₹5000
        BankAccount userAccount = new BankAccount(5000);
        ATM atm = new ATM(userAccount);
        atm.start(); // Start the ATM interface
    }
}

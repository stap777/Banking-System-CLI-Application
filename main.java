import java.util.ArrayList;
import java.util.Scanner;


class account {
    int accountNo;
    String name;
    double balance;

    public account (int a, String b, double c) {
        accountNo = a;
        name = b;
        balance = c;
    }

    public String toString() {
        return name;
    }

}

class Bank {

    ArrayList<account> ac = new ArrayList<>();
    account currentAccount;
    account receiverAccount;

    void createAccount(Scanner sc) {

        System.out.println("input name");
        String name = sc.nextLine();

        System.out.println("input account no");
        int accNo = sc.nextInt();
        sc.nextLine();

        account acc = new account(accNo, name, 0);

        ac.add(acc);
    }

    void listAccounts() {

        if (ac.isEmpty()) {
            System.out.println("no accounts to list");
        }
        else {
            for (int i = 0; i < ac.size(); i++) {
            System.out.println(ac.get(i));
            }
        }
    }

    void login(Scanner sc) {
        System.out.println("Input name");
        String Name = sc.nextLine();
        boolean found = false;

        for (int i = 0; i < ac.size(); i++) {
            if (ac.get(i).name.equals(Name)) {
                System.out.println("Verify input acc NO");
                int accNo = sc.nextInt();
                sc.nextLine();
                
                int aNo = ac.get(i).accountNo;
                
                if (accNo == aNo) {
                    currentAccount = ac.get(i);
                    System.out.println("Login succesfull");
                }
                found = true;
                break;
            }
            
        }if (!found) {
            System.out.println("Account not found");
        } 
    }

    boolean isLogin() {
        if (currentAccount == null) {
            return false;
        }
        else {
            return true;
        }
    }

    void balanceCheck() {
        System.out.println(currentAccount.balance);
    }

    void depositMoney(Scanner sc) {

        System.out.println("input amount to be deposited");

        int amount = sc.nextInt();
        sc.nextLine();
        if (amount > 100000 && amount < 0) {
            System.out.println("limit excided");
        }
        else {
            currentAccount.balance += amount;
            System.out.println("Success");
        }
    }

    void withdrawMoney(Scanner sc) {
        System.out.println("input amount to be withdrawed");

        int amount = sc.nextInt();
        sc.nextLine();
        if (amount > currentAccount.balance && amount < 0) {
            System.out.println("insufficiant balance");
        }
        else {
            currentAccount.balance -= amount;
            System.out.println("Success");
        }
    }

    void transferMoney(Scanner sc) {
        System.out.println("input recipients account no");
        int accNo = sc.nextInt();
        sc.nextLine();

        System.out.println("input amount to be transfered");
        int amount = sc.nextInt();
        sc.nextLine();
        boolean found = false;

        for (int i = 0; i < ac.size(); i++) {
            if (accNo == ac.get(i).accountNo) {
                receiverAccount = ac.get(i);
                found = true;
                
                if (amount > currentAccount.balance && amount < 0) {
                    System.out.println("insufficiant funds");
                }
                else {
                    receiverAccount.balance += amount;
                    currentAccount.balance -= amount;
                    System.out.println("Success");
                }
                break;
            }
            
        }
        if (!found) {
            System.out.println("Invalid Account No");
        }
    }

    void LogOut() {
        currentAccount = null;
    }
}

public class main {

    static void printlist(ArrayList<String> menu) {
        for (int i=0; i < menu.size(); i++) {
            System.out.println(menu.get(i));
        } 
    }

    public static void main(String [] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<String> menu = new ArrayList<>();
        ArrayList<String> AccountMenu = new ArrayList<>();

        Bank BU = new Bank();

        AccountMenu.add("(1) Check Balance");
        AccountMenu.add("(2) Diposite Money");
        AccountMenu.add("(3) Withdraw money");
        AccountMenu.add("(4) Transfer Money");
        AccountMenu.add("(5) LogOut");

        menu.add("(1) Create Account");
        menu.add("(2) List all Accounts");
        menu.add("(3) Login");
        menu.add("(4) Exit");
        
        
        while (true) {
            if (!BU.isLogin()) {
                printlist(menu);
                int choice= sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    BU.createAccount(sc);
                }

                else if (choice == 2) {
                    BU.listAccounts();
                }

                else if (choice == 3) {
                    BU.login(sc);
                }

                else if (choice == 4) {
                    break;
                }
                
                else {
                    System.out.println("Invalid Choice");
                }
            }
            else {
                printlist(AccountMenu);
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    BU.balanceCheck();
                }

                else if (choice == 2) {
                    BU.depositMoney(sc);
                }

                else if (choice == 3) {
                    BU.withdrawMoney(sc);
                }

                else if (choice == 4) {
                    BU.transferMoney(sc);
                }

                else if (choice == 5) {
                    BU.LogOut();
                }

                else {
                    System.out.println("Invalid Choice");
                }
            }

        }
        
    }
}
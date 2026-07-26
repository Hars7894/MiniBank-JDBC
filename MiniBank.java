package com.jdbcMiniBank;

import java.sql.*;
import java.util.*;

public class MiniBank {
    static Scanner scanner = new Scanner(System.in);

    static public void Chek_m(Connection con) {
        System.out.print("Enter the Account Number: ");
        String a_no = scanner.nextLine();
        ShowBalance showBalance = new ShowBalance(con);
        Exist_Account existAccount = new Exist_Account(con);
        if (existAccount.Exist(a_no)) {
            showBalance.ChekBalance(a_no);
        }

    }

    static public void Deposit_m(Connection con) {
        Deposit deposit = new Deposit(con);
        Exist_Account existAccount = new Exist_Account(con);
        System.out.print("Enter the A/C: ");
        String account = scanner.nextLine().trim();

        if (existAccount.Exist(account)) {
            System.out.print("Enter the deposit Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            if (amount > 0) {
                deposit.DepositMoney(account, amount);
            } else {
                System.out.println("Amount can't be -ve❌");
            }


        } else {
            System.out.println("something want wrong❌❌ Account not exist");
        }
    }

    static public void Withdraw_m(Connection con) {
        Exist_Account existAccount = new Exist_Account(con);
        Withdraw withdraw = new Withdraw(con);
        System.out.print("Enter the A/c: ");
        String ac_no = scanner.nextLine().trim();


        if (existAccount.Exist(ac_no)) {
            System.out.print("Enter the withdrawal amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            if (amount > 0) {
                withdraw.withdrawMoney(ac_no, amount);
            } else {
                System.out.println("something want worng❌");
            }


        }
    }

    static public void Transfer_m(Connection con) {
        Transfer transfer = new Transfer(con);
        Exist_Account existAccount = new Exist_Account(con);
        System.out.print("Enter your primary A/c: ");
        String a1 = scanner.nextLine();
        System.out.print("Enter Secondary A/c: ");
        String a2 = scanner.nextLine();
        System.out.print("Enter the Transfer Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        if (existAccount.Exist(a1) && existAccount.Exist(a2)) {
            if (!Objects.equals(a1, a2)) {
                transfer.TransferMoney(a1, a2, amount);
            } else {
                System.out.println("Not transferable same Account❌");
            }

        } else {
            System.out.println("A/c not exist❌");
        }
    }

    static public void Close(Connection con) {
        Exist_Account existAccount = new Exist_Account(con);
        ShowBalance showBalance = new ShowBalance(con);
        Close_Ac close = new Close_Ac(con);
        System.out.print("Enter the A/c: ");
        String ac_no = scanner.nextLine().trim();
        if (existAccount.Exist(ac_no)) {
            if (showBalance.balance(ac_no) == 0) {
                close.close_A(ac_no);
            } else {
                System.out.println("This A/c have Balance ! First need to Transfer or withdraw the Fund💸 ");
            }
        }

    }

    static public void Open(Connection con) {
        String set = ("1234567890");
        String set_ac = "";
        Exist_Account existAccount = new Exist_Account(con);
        Open_Ac openAc = new Open_Ac(con);

        Random random = new Random();

        while (true) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 11; i++) {
                int index = random.nextInt(set.length());
                sb.append(set.charAt(index));
            }

            set_ac = sb.toString();

            if (!(existAccount.Exist(set_ac))) {
                set_ac = sb.toString();
                System.out.printf("Your A/c: %s", set_ac);
                break;
            }
        }
        System.out.println("-------  Fill details  ----------");
        System.out.print("Enter the Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter the mail: ");
        String mail = scanner.nextLine().trim();
        System.out.print("Enter the ph: ");
        String ph = scanner.nextLine().trim();
        System.out.print("Enter the imf: ");
        String imf = scanner.nextLine().trim();
        System.out.print("Enter the First opening Deposit: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();
        openAc.open(set_ac, name, mail, ph, imf, balance);


    }


    public static void main(String[] args) {
        /* Rewrite the  connection accounting ,use two table 1 employee ,2 user*/
        String url = "?";
        String user = "?";
        String password = "?";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.print("Enter the Id: ");
            String input_id = scanner.nextLine().trim();
            System.out.print("Enter the Password: ");
            String input_password = scanner.nextLine().trim();
            Emp emp = new Emp(con);

            boolean I_passwordExist = emp.exist(input_id, input_password);

            if (I_passwordExist) {
                System.out.println("Login ✅");
                boolean login = true;
                while (login) {

                    System.out.println("-----------Menu-----------");
                    System.out.println("1. Chek Money💰");
                    System.out.println("2. Deposit Money💰");
                    System.out.println("3. Withdraw Money💰");
                    System.out.println("4. Transfer Money💰");
                    System.out.println("5. close A/c");
                    System.out.println("6. Open A/c");
                    System.out.println("7. Exist Account Here");
                    System.out.println("8.Log Out ");
                    System.out.print("Enter the option: ");
                    int option = scanner.nextInt();
                    scanner.nextLine();
                    switch (option) {
                        case 1:
                            Chek_m(con);
                            break;
                        case 2:
                            Deposit_m(con);
                            break;
                        case 3:
                            Withdraw_m(con);
                            break;
                        case 4:
                            Transfer_m(con);
                            break;
                        case 5:
                            Close(con);
                            break;
                        case 6:
                            Open(con);
                            break;
                        case 7:

                            System.out.print("Enter the A/c: ");
                            String ac_no = scanner.nextLine().trim();
                            Exist_Account existAccount = new Exist_Account(con);
                            System.out.println(existAccount.Exist(ac_no));
                            break;
                        case 8:
                            System.out.println("Logout successful ✅✅");
                            login = false;
                            break;
                        default:
                            System.out.println("Enter the Valid Option✔️");
                    }
                }
            } else {
                System.out.println("invalid Id and password❌");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }

    }
}

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static List<ZEmployee> employeeList = new ArrayList<>();
    static List<User> userList = new ArrayList<>();
    static List<Account> zAccountList = new ArrayList<>();
    static Map<Integer, Account> map = new HashMap<>();
    static double zcoinExchangeRate;
    static double earn = 0;
    static int zid = 2;

    public static void main(String[] args) {
        addZemloyee();
        System.out.println("-----------Welcome to our Z coin Exchange Platform -------------");
        boolean loop = true;
        while (loop) {

            System.out.println("Please choose Your Operation : \n 1 --> User Panel  \n 2 --> ZEmployee login  \n 3 --> to exit");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    userPanel();
                    break;
                case 2:
                    employeeLogin();
                    break;
                case 3:
                    loop = false;
                    break;
                default:
                    System.out.println(" you Entered wrong option!   try again  ");
                    break;
            }

            System.out.println("-----------Thank you for using our Z coin Exchange Platform -------------");
        }
    }

    public static void addZemloyee() {
        ZEmployee ab = new ZEmployee("A", "aa@gmail.com", "1234qwert");
        ZEmployee ab1 = new ZEmployee("B", "bb@gmail.com", "1234qwert");
        ZEmployee ab2 = new ZEmployee("C", "cc@gmail.com", "1234qwert");
        employeeList.add(ab);
        employeeList.add(ab1);
        employeeList.add(ab2);
    }

    public static void userPanel() {
        boolean loop = true;
        while (loop) {

            System.out.println("Please choose Your Operation : \n 1 --> Login  \n 2 --> Sign up  \n 3 --> to exit");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    userLogin();
                    break;
                case 2:
                    userSignUp();
                    break;
                case 3:
                    loop = false;
                    break;
                default:
                    System.out.println(" You Entered wrong option!   try again  ");
                    break;
            }

        }
    }

    public static void userLogin() {
        System.out.println("Enter your Email Id");
        String email = sc.next();
        System.out.println("Enter your Password ");
        String password = sc.next();

        User currentUser = null;

        for (User user : userList) {

            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                currentUser = user;
            }

        }

        if (currentUser != null) {
            if (currentUser.isApproved()) {
                userHomePage(currentUser);
            } else {
                System.out.println(" Your Account not yet approved ");
            }
        } else {
            System.out.println(" Your Details does not match our record ");
        }
    }

    public static void userHomePage(User user) {
        boolean loop = true;
        while (loop) {

            System.out.println("Please choose Your Operation : \n 1 --> LoginYour Wallet \n 2 --> changeYourPassword  \n 3 --> to exit");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    loginAccount(user);
                    break;
                case 2:
                    userSignUp();
                    break;
                case 3:
                    loop = false;
                    break;
                default:
                    System.out.println(" You Entered wrong option!   try again  ");
                    break;
            }

        }

    }

    private static void userSignUp() {
        System.out.println("Enter Your Name");
        String name = sc.next();
        System.out.println("Enter your email");
        String email = sc.next();
        System.out.println("Enter Your Password");
        String password = sc.next();
        System.out.println("re Enter Your Password");
        String cpasswod = sc.next();
        System.out.println("Enter Your phone no");
        String phone = sc.next();
        System.out.println("enter your deposit amount");
        double amount = sc.nextDouble();
        if (isPasswordValid(password, name, phone) && password.equals(cpasswod) && isEmailValid(email)) {
            User newUser = new User(name, email, password, phone, amount);
            System.out.println("Your account is created successfully but you have wait sometime to use your Z coin wallet after our team member verify your account it activated ");
            userList.add(newUser);
        } else {
            System.out.println("Password is invalid.");
        }

    }
    public static boolean isPasswordValid(String password, String name, String mobileNumber) {

        if (password.length() < 8) {
            return false;
        }

        if (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!#%><?&*]).+$", password)) {
            return false;
        }

        if (Pattern.compile(Pattern.quote(name), Pattern.CASE_INSENSITIVE).matcher(password).find() ||
                Pattern.compile(Pattern.quote(mobileNumber)).matcher(password).find()) {
            return false;
        }

        return true;
    }

    public static boolean isEmailValid(String email) {

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);

        Matcher matcher = pattern.matcher(email);
            if(!matcher.matches()) {
            System.out.println("Your email id is not valid");
            }
        return matcher.matches();
    }
    public static void loginAccount(User user) {

        Account currentAccount = map.get(user.getId());

        boolean loop = true;
        while (loop) {

            System.out.println("Please choose Your Operation : \n 1 --> checkBalance  \n 2 --> makeTransaction  \n 3 --> to view History \n 4 --> To exit");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    checkBalance(currentAccount);
                    break;
                case 2:
                    makeTransaction(currentAccount);
                    break;
                case 3:
                    viewHistory(currentAccount);
                    break;
                case 4:
                    loop = false;
                    break;
                default:
                    System.out.println(" You Entered wrong option!   try again  ");
                    break;
            }

        }

    }

    public static void viewHistory(Account currentAccount) {
        currentAccount.getStatement();
    }

    public static void checkBalance(Account currentAccount) {
        System.out.println(" Your balance in Rc Account " + currentAccount.getRCBalance());
        System.out.println(" Your balance in Zc Account " + currentAccount.getZCBalance());
    }

    public static void makeTransaction(Account currentAccount) {
        checkBalance(currentAccount);
        boolean loop = true;
        while (loop) {

            System.out.println("Please choose Your Operation : \n 1 --> to Deposit Rc Account  \n 2 --> to Withdraw Rc Account  \n"
                    + " 3 --> to Deposit Zc Account  \n 4 --> to Withdraw Zc Account \n 5 --> to exchange RC to Zc Account  \n" +
                    " 6 --> exchange RC to Zc Account  \n 7  --> To exit");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    depositRc(currentAccount);
                    break;
                case 2:
                    withdrawRc(currentAccount);
                    break;
                case 3:
                    depositZc(currentAccount);
                    break;
                case 4:
                    withdrawZc(currentAccount);
                    break;
                case 5:
                    exchangeRctoZc(currentAccount);
                    break;
                case 6:
                    exchangeZctoRc(currentAccount);
                    break;
                case 7:
                    loop = false;
                    break;
                default:
                    System.out.println(" You Entered wrong option!   try again  ");
                    break;
            }

        }


    }

    public static void exchangeZctoRc(Account currentAccount) {
        System.out.println(" Welcome Your Zcoin to Regular Coin exchange Section ");
        System.out.println(" 1 Zcoin  =  " + 2 + " Rcoin");
        System.out.println(" Your exchange charges is 0.15% of your Rc Amount");
        System.out.println(" Enter your Rc amount to convert Zcoin Amount");
        double amount = sc.nextDouble();
        if (currentAccount.getRCBalance() > amount) {
            currentAccount.addRCBalance(-amount);
            double newearn = 0.15 * amount;
            double zcoin = amount - newearn;
            earn += newearn;
            currentAccount.addZCBalance(zcoin);
            System.out.println("To  the rc amount " + amount + " to  convert in to your ZC Account " + zcoin);
            System.out.println("To  Commision amount is " + newearn);
            System.out.println("your new RC balance  --> " + currentAccount.getRCBalance());
            System.out.println("your new ZC balance  --> " + currentAccount.getZCBalance());
            String transaction = "old Balance Rc" + (currentAccount.getRCBalance() + amount) + "\n Old Balance Zc " + (currentAccount.getZCBalance() - amount) + "\n Transaction details : " + amount + " is converted into Zcoin Amount " + zcoin + " Commision is " + newearn +
                    "New  Balance Rc" + (currentAccount.getRCBalance()) + "\n New Balance Zc " + (currentAccount.getZCBalance());
            currentAccount.addStatement(transaction);

            try {
                File file = new File("zcoin.txt");
                FileWriter fw = new FileWriter(file, true);
                fw.write(transaction + " \n ");
                fw.flush();
                fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }

        } else {
            System.out.println(" Your rc account have  insufficient balance ");
        }


    }

    public static void exchangeRctoZc(Account currentAccount) {
        System.out.println(" Welcome Your  Regular Coin to Zcoin exchange Section ");
        System.out.println(" 1 Rcoin  =  " + (0.5) + " Zcoin");
        System.out.println(" Your exchange charges is " + zcoinExchangeRate + " of your Zc Amount");
        System.out.println(" Enter your regular  amount to convert Zcoin Amount");
        double amount = sc.nextDouble();
        if (currentAccount.getZCBalance() > amount) {
            currentAccount.addZCBalance(-amount);
            double newearn = zcoinExchangeRate * amount;
            double Zcoin = amount - newearn;
            earn += newearn * 2;
            currentAccount.addZCBalance(Zcoin);
            System.out.println("To  the RC amount " + amount + " to  convert in to your ZC Account " + Zcoin);
            System.out.println("To  Commision amount is " + newearn * 2);
            System.out.println("your new RC balance  --> " + currentAccount.getRCBalance());
            System.out.println("your new ZC balance  --> " + currentAccount.getZCBalance());
            String transaction = "old Balance Rc" + (currentAccount.getRCBalance() + Zcoin) + "\n Old Balance Zc " + (currentAccount.getZCBalance() + amount) + "\n Transaction details : Rcoin " + amount + " is converted into Zcoin Amount " + Zcoin + " Commision is " + (newearn * 2) +
                    "New  Balance Rc" + (currentAccount.getRCBalance()) + "\n New Balance Zc " + (currentAccount.getZCBalance());
            currentAccount.addStatement(transaction);

            try {
                File file = new File("zcoin.txt");
                FileWriter fw = new FileWriter(file, true);
                fw.write(transaction + " \n ");
                fw.flush();
                fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }

        } else {
            System.out.println(" Your rc account have  insufficient balance ");
        }

    }

    public static void depositRc(Account currentAccount) {
        System.out.println("To enter the Amount to deposit your Rc Account");
        double amount = sc.nextDouble();
        currentAccount.addRCBalance(amount);
        System.out.println("To enter the " + amount + " to deposit your Rc Account");
        System.out.println("your new RC balance  --> " + currentAccount.getRCBalance());
        String transaction = "old Balance Rc" + (currentAccount.getRCBalance() - amount) + "\n Old Balance Zc " + (currentAccount.getZCBalance()) + "\n Transaction details : " + amount + " is deposited into Rcoin Amount " +
                "New  Balance Rc" + (currentAccount.getRCBalance()) + "\n New Balance Zc " + (currentAccount.getZCBalance());
        currentAccount.addStatement(transaction);

        try {
            File file = new File("zcoin.txt");
            FileWriter fw = new FileWriter(file, true);
            fw.write(transaction + " \n ");
            fw.flush();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void depositZc(Account currentAccount) {
        System.out.println("To enter the Z coin Amount to deposit your Zc Account");
        double amount = sc.nextDouble();
        currentAccount.addZCBalance(amount);
        System.out.println("To enter the " + amount + " to deposit your Zc Account");
        System.out.println("your new ZC balance  --> " + currentAccount.getZCBalance());

        String transaction = "old Balance Rc" + (currentAccount.getRCBalance()) + "\n Old Balance Zc " + (currentAccount.getZCBalance() - amount) + "\n Transaction details : " + amount + " is deposited into Zcoin Amount " +
                "New  Balance Rc" + (currentAccount.getRCBalance()) + "\n New Balance Zc " + (currentAccount.getZCBalance());
        currentAccount.addStatement(transaction);

        try {
            File file = new File("zcoin.txt");
            FileWriter fw = new FileWriter(file, true);
            fw.write(transaction + " \n ");
            fw.flush();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void withdrawRc(Account currentAccount) {
        System.out.println("To enter the Amount to withdraw your Rc Account");
        double amount = sc.nextDouble();
        if (currentAccount.getRCBalance() > amount) {
            currentAccount.addRCBalance(-amount);
            System.out.println("To enter the " + amount + " to withdraw your Rc Account");
            System.out.println("your new RC balance  --> " + currentAccount.getRCBalance());

            String transaction = "old Balance Rc" + (currentAccount.getRCBalance() + amount) + "\n Old Balance Zc " + (currentAccount.getZCBalance()) + "\n Transaction details : " + amount + " is Withdraw into RC account  " +
                    "New  Balance Rc" + (currentAccount.getRCBalance()) + "\n New Balance Zc " + (currentAccount.getZCBalance());
            currentAccount.addStatement(transaction);

            try {
                File file = new File("zcoin.txt");
                FileWriter fw = new FileWriter(file, true);
                fw.write(transaction + " \n ");
                fw.flush();
                fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println(" Your rc account have  insufficient balance ");
        }
    }

    public static void withdrawZc(Account currentAccount) {
        System.out.println("To enter the Z coin amount to withdraw your Zc Account");
        double amount = sc.nextDouble();
        if (currentAccount.getZCBalance() > amount) {
            currentAccount.addZCBalance(-amount);
            System.out.println("To enter the " + amount + " to withdraw your Zc Account");
            System.out.println("your new ZC balance  --> " + currentAccount.getZCBalance());
            String transaction = "old Balance Rc" + (currentAccount.getRCBalance()) + "\n Old Balance Zc " + (currentAccount.getZCBalance() + amount) + "\n Transaction details : " + amount + " is Withdraw into ZC account  " +
                    "New  Balance Rc" + (currentAccount.getRCBalance()) + "\n New Balance Zc " + (currentAccount.getZCBalance());
            currentAccount.addStatement(transaction);

            try {
                File file = new File("zcoin.txt");
                FileWriter fw = new FileWriter(file, true);
                fw.write(transaction + " \n ");
                fw.flush();
                fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println(" Your Zc account have  insufficient balance ");
        }
    }

    public static void employeeLogin() {

        System.out.println("Enter your Email Id");
        String email = sc.next();
        System.out.println("Enter your Password ");
        String password = sc.next();

        ZEmployee currentEmployee = null;

        for (ZEmployee e : employeeList) {

            if (e.getEmail().equals(email) && e.getPassword().equals(password)) {
                currentEmployee = e;
            }

        }

        if (currentEmployee != null) {
            agentHome(currentEmployee);
        } else {
            System.out.println(" Your Details does not match our record ");
        }

    }

    public static void agentHome(ZEmployee currentEmployee) {

        boolean loop = true;
        while (loop) {

            System.out.println("Please choose Your Operation : \n 1 --> Approve Zcoin Users \n 2 --> Set Z coinExchange Rate  \n 3 --> view total History \n 4 --> to exit");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    approveUsers();
                    break;
                case 2:
                    setExchange();
                    break;
                case 3:
                    viewTotaltransactionHistory();
                    break;
                case 4:
                    loop = false;
                    break;
                default:
                    System.out.println(" You Entered wrong option!   try again  ");
                    break;
            }

        }

    }

    public static void approveUsers() {
        System.out.println("Unapproved User list");

        System.out.println("Id						Name");

        for (User unApprovedUsers : userList) {

            if (!unApprovedUsers.isApproved()) {
                System.out.println(unApprovedUsers.getId() + "			" + unApprovedUsers.getName());
            }
        }

        System.out.println("Enter User id to approve and set Zid for Account ");
        int id = sc.nextInt();
        User selectUser = null;
        for (User unApprovedUsers : userList) {
            if (!unApprovedUsers.isApproved() && unApprovedUsers.getId() == id) {
                selectUser = unApprovedUsers;
            }
        }
        if (selectUser != null) {
            selectUser.setApproved(true);
            selectUser.setZaccountId(zid++);
            Account zcoinAccount = new Account(zid, selectUser, selectUser.getDepositAmount(), 0);
            zAccountList.add(zcoinAccount);
            map.put(selectUser.getId(), zcoinAccount);
            System.out.println(selectUser.getName() + "  account is activated");
        } else {
            System.out.println("you chose wrong id");
        }

    }

    public static void setExchange() {
        System.out.println("The exchange rate " + zcoinExchangeRate);
        System.out.println("If you want to modify enter 1 otherwise you enter 2");
        int option = sc.nextInt();
        if (option == 1) {
            System.out.println("Enter   exchange rate ");
            zcoinExchangeRate = sc.nextDouble();
            System.out.println("Success fully Exchange rate is updated \n New Exchange rate is " + zcoinExchangeRate);
        }
    }

    public static void viewTotaltransactionHistory() {
        try {
            File file = new File("zcoin.txt");
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                reader.close();
            } else {
                System.out.println("File does not exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
package Managers;

import Utilities.Account;
import Utilities.Security;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountsManager
{
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static Account[] Accounts;
    public static Account currentAccount;

    public static void Initialize()
    {
        String[] accountData = DataManager.ProcessAccountsData();
        Accounts = new Account[accountData.length];

        int i = 0;
        for (String data : accountData)
            Accounts[i++] = new Account(data.trim().split("\\r?\\n"));
    }

    public static boolean IsAccountRegistered(String email)
    {
        for (Account account : Accounts)
        {
            if (account.holderEmail.trim().equals(email.trim()))
                return true;
        }
        return false;
    }

    public static boolean Login(String email, String password)
    {
        for (Account account : Accounts)
        {
            if (account.holderEmail.trim().equals(email.trim()) && account.holderPassword.trim().equals(password.trim()))
            {
                currentAccount = account;
                return true;
            }
        }
        return false;
    }

    public static void Register(String name, String email, String password)
    {
        Path path = Path.of(DataManager.ACCOUNTS_FILE_PATH);
        try
        {
            Files.writeString(
                    path,
                    name.trim() + "\n" + email.trim() + "\n" + Security.encrypt(password.trim()) + "\n\n",
                    StandardOpenOption.APPEND);
            Initialize();
            Login(email, password);
            DataManager.InitializeAccountsData();
            GuiAppManager.StartBooking();
        }
        catch (Exception e)
        {
            System.out.println(e + "\nError Writing Account Details In " + path);
            System.exit(0);
        }
    }

    public static void AddBooking(String movie, String date, String time, String hall, ArrayList<Integer> seats)
    {
        Path path = Path.of( DataManager.ACCOUNTS_DATA_PATH + currentAccount.holderEmail + "/" + movie + "#" + hall + "#" + date + "#" + time.replace(":", "_") + ".txt");
        try
        {
            if (!new File(path.toUri()).exists()) new File(path.toUri()).createNewFile();
            for (Integer seat : seats)
                Files.writeString(path, seat.toString() + "\n", StandardOpenOption.APPEND);

            currentAccount.GetPreviousBookings();
        }
        catch (Exception e)
        {
            System.out.println(e + "\nError Writing Booked Seats In " + path);
            System.exit(0);
        }
    }

    public static boolean IsInvalidEmail(String emailStr)
    {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return !matcher.matches();
    }
}

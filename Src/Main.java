import Managers.*;
import Utilities.Security;
import Utilities.Visuals;

public class Main {
    public static void main(String[] args) {
        Initialize();
        Play();
    }

    private static void Initialize() {
        BookingManager.Initialize();
        DataManager.InitializeMovieData();
        Security.Initialize();
        AccountsManager.Initialize();
        DataManager.InitializeAccountsData();
        Visuals.Initialize();
        GuiAppManager.Initialize();
    }

    private static void Play() {
        GuiAppManager.StartGUI();
    }
}
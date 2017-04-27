/*
 * The "glue" class for the other classes demonstrating the MVC pattern;
 * this class contains the main method.
 */
package bankingsystem;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Marian Lam
 */
public class BankApplication {

    /**
     * Enhance GUI visual effects
     */
    public static void enhanceVisualEffects() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /* run the application */
    public static void main(String[] args) {
        BankApplication.enhanceVisualEffects();
        BankAppController controller = new BankAppController();
    }
}


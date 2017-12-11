package user_interface;

import javax.swing.*;
import java.util.Stack;

public class ScreenManager {
    private static ScreenManager ourInstance = new ScreenManager();

    public static ScreenManager getInstance() {
        return ourInstance;
    }

    JFrame frame;
    Stack<JPanel> panelStack;
    private ScreenManager() {
        panelStack = new Stack<>();
        frame = new JFrame("Civilizational Wars");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);

        frame.add(new MainMenuPanel());

        frame.setVisible(true);
        start();

    }

    private void start() {
        frame.add(new MainMenuPanel());
        frame.revalidate();
    }

    public void setCurrentPanel(JPanel p)
    {
        panelStack.add(p);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(p);
        frame.getContentPane().revalidate();
    }



}

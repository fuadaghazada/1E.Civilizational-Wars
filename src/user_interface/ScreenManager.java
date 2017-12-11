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

        frame.setVisible(true);
        start();

    }

    private void start() {
        setCurrentPanel(new MainMenuPanel());
    }

    public void setCurrentPanel(JPanel p)
    {
        panelStack.push(p);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(p);
        frame.getContentPane().revalidate();
        p.updateUI();
        p.requestFocusInWindow();
    }

    public void back()
    {
        panelStack.pop();
        JPanel backP = panelStack.peek();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(backP);
        backP.requestFocusInWindow();
        backP.updateUI();
        frame.getContentPane().revalidate();
    }

    public void clearHistory()
    {
        frame.getContentPane().removeAll();
        panelStack.clear();
    }



}

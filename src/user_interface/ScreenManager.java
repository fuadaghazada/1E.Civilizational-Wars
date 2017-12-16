package user_interface;

import javax.swing.*;
import java.util.Stack;

public class ScreenManager
{
    // singleton instance
    private static ScreenManager ourInstance = new ScreenManager();

    //Properties
    private JFrame frame;
    private Stack<JPanel> panelStack;

    /**
     *  Constructs the screen manager - initializing the stack and the frame
     */
    private ScreenManager()
    {
        panelStack = new Stack<>();
        frame = new JFrame("Civilizational Wars");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);

        frame.setVisible(true);
        start();

    }

    public static ScreenManager getInstance() {
        return ourInstance;
    }

    /**
     *  Sets the current panel to main menu - when the program starts
     */
    private void start() {
        setCurrentPanel(new MainMenuPanel());
    }

    /**
     *  Pushes the new panel to the stack and make it the current panel
     *  @param p - new panel adding
     */
    public void setCurrentPanel(JPanel p)
    {
        panelStack.push(p);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(p);
        frame.getContentPane().revalidate();
        p.updateUI();
        p.requestFocusInWindow();
    }

    /**
     *  Pops the current panel from the JPanel stack and sets it the current panel
     */
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

    /**
     *  Clearing the panel stack
     */
    public void clearHistory()
    {
        frame.getContentPane().removeAll();
        panelStack.clear();
    }

    // ACCESS

    public JFrame getFrame()
    {
        return frame;
    }
}

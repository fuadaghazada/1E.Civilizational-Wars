package user_interface;
import game_management.GameManager;
import game_management.LevelManager;
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class WonPanel extends JPanel {

    public WonPanel(final GameManager gameManager)
    {
        if(gameManager.hasNextLevel())
        {
            //TODO: Print won prompt, wait for a while and navigate to next level
            JPanel lblWrapper = new JPanel();
            JLabel label = new JLabel("<html>Congratulations, you won this level<br/>Prepare yourself for next level</html>", SwingConstants.CENTER);
            lblWrapper.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
            lblWrapper.add(label);
            label.setForeground(Color.GREEN);
            label.setFont(new Font("Sans Serif", Font.BOLD, 25));
            add(lblWrapper);


            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    ScreenManager.getInstance().setCurrentPanel(new GamePanel(GameManager.NEXT_LEVEL, gameManager.isMultiPlayer()));

                }
            }, 5000);

        }
        else{

            JPanel lblWrapper = new JPanel();
            JLabel label = new JLabel("<html>Congratulations, you won this game<br/></html>", SwingConstants.CENTER);
            lblWrapper.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
            lblWrapper.add(label);
            label.setForeground(Color.GREEN);
            label.setFont(new Font("Sans Serif", Font.BOLD, 25));
            add(lblWrapper);
            System.out.println("WON" + gameManager.hasNextLevel());

            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    ScreenManager.getInstance().setCurrentPanel(new MainMenuPanel());
                }
            }, 5000);

        }
    }
}

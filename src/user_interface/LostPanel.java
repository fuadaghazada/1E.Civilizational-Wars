package user_interface;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class LostPanel extends JPanel {

    public LostPanel(){
        JPanel lblWrapper = new JPanel();
        JLabel label = new JLabel("<html>Game Over..<br/></html>", SwingConstants.CENTER);
        lblWrapper.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
        lblWrapper.add(label);
        label.setForeground(Color.RED);
        label.setFont(new Font("Sans Serif", Font.BOLD, 25));
        add(lblWrapper);

        final java.util.Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ScreenManager.getInstance().setCurrentPanel(new MainMenuPanel());
            }
        }, 5000);
    }

}

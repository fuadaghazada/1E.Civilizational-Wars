package user_interface;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {


    public static final int SETTINGS_ROW = 5;
    public static final int SETTINGS_COL = 5;
    //Components
    private JLabel lblDifficulty, lblControls, lblMusic;
    private JPanel[] rows;


    private JPanel firstRow, secondRow, thirdRow, forthRow, fifthRow;

    public SettingsPanel()
    {
        setLayout(new GridLayout(SETTINGS_ROW,SETTINGS_COL));
        rows = new JPanel[SETTINGS_ROW];
        setBackground(Color.BLUE);
        init();

    }

    /*
        Initializing the components of the panel
     */
    private void init()
    {
        for(int i = 0; i < rows.length; i++)
        {
            rows[i] = createRow(i);
            rows[i].setBackground(Color.BLUE);

            this.add(rows[i]);// == null ? rows[i] : new JPanel());
        }

    }

    private JPanel createRow(int index) {

        switch (index) {
            case 1:
                firstRow = new JPanel();
                firstRow.setLayout(new GridLayout(1, 4));
                lblDifficulty = new JLabel("Difficulty: ");
                lblDifficulty.setForeground(Color.WHITE);
                firstRow.add(lblDifficulty);
                return firstRow;

            case 2:
                secondRow = new JPanel();
                secondRow.setLayout(new GridLayout(1, 3));
                lblControls = new JLabel("Controls: ");
                lblControls.setForeground(Color.WHITE);
                secondRow.add(lblControls);
                return secondRow;

            case 3:
                thirdRow = new JPanel();
                thirdRow.setLayout(new GridLayout(1, 2));
                lblMusic = new JLabel("Music: ");
                lblMusic.setForeground(Color.WHITE);
                thirdRow.add(lblMusic);
                return thirdRow;
            default:
                return new JPanel();

        }
    }

}

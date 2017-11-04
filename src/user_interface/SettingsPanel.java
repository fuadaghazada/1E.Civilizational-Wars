package user_interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SettingsPanel extends JPanel {


    public static final int SETTINGS_ROW = 5;
    public static final int SETTINGS_COL = 5;
    //Components
    private JLabel lblDifficulty, lblControls, lblMusic;
    private JPanel[] rows;


    public SettingsPanel()
    {
        setLayout(new GridLayout(SETTINGS_ROW,SETTINGS_COL));
        rows = new JPanel[SETTINGS_ROW];
        setBackground(Color.BLUE);
        this.setBorder(new EmptyBorder(20,50,20,20));
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
                JPanel firstRow = new JPanel();
                firstRow.setLayout(new GridLayout(1, 4, 10, 10));
                lblDifficulty = new JLabel("Difficulty: ");
                lblDifficulty.setForeground(Color.WHITE);


                JCheckBox cbEasy = new JCheckBox("Easy");
                JCheckBox cbMedium = new JCheckBox("Medium");
                JCheckBox cbHard = new JCheckBox("Hard");
                ButtonGroup cbGroup = new ButtonGroup();
                cbGroup.add(cbEasy);
                cbGroup.add(cbMedium);
                cbGroup.add(cbHard);
                cbEasy.setBackground(Color.BLUE);
                cbMedium.setBackground(Color.BLUE);
                cbHard.setBackground(Color.BLUE);
                cbEasy.setForeground(Color.WHITE);
                cbMedium.setForeground(Color.WHITE);
                cbHard.setForeground(Color.WHITE);
                //TODO: There must be a code for selecting the current difficulty level.


                firstRow.add(lblDifficulty);
                firstRow.add(cbEasy);
                firstRow.add(cbMedium);
                firstRow.add(cbHard);
                return firstRow;

            case 2:
                JPanel secondRow = new JPanel();
                secondRow.setLayout(new GridLayout(1, 3, 10, 10));
                lblControls = new JLabel("Controls: ");
                lblControls.setForeground(Color.WHITE);

                JPanel moveP = new JPanel();
                moveP.setLayout(new GridLayout(2,1));
                moveP.add(new Label("Move: "));

                String[] moveOptions = {"WASD", "Arrow keys"};
                JComboBox<String> comboMove = new JComboBox<>(moveOptions);

                moveP.setBackground(Color.BLUE);
                comboMove.setBackground(Color.BLUE);
                comboMove.setForeground(Color.WHITE);
                moveP.setForeground(Color.WHITE);
                moveP.add(comboMove);

                JPanel fightP = new JPanel();
                fightP.setLayout(new GridLayout(2,1));
                fightP.add(new Label("Fight"));

                String[] fightOptions = {"Z", "K" , "L"};
                JComboBox<String> comboFight = new JComboBox<>(fightOptions);
                fightP.setBackground(Color.BLUE);
                comboFight.setBackground(Color.BLUE);
                comboFight.setForeground(Color.WHITE);
                fightP.setForeground(Color.WHITE);


                fightP.add(comboFight);

                //TODO: There must be a code for selecting the current key bindings.

                secondRow.add(lblControls);
                secondRow.add(moveP);
                secondRow.add(fightP);

                return secondRow;

            case 3:
                JPanel thirdRow = new JPanel();
                thirdRow.setLayout(new GridLayout(1, 2, 10, 10));
                lblMusic = new JLabel("Music: ");
                lblMusic.setForeground(Color.WHITE);
                thirdRow.add(lblMusic);

                JCheckBox cbMusic = new JCheckBox("On/Off");
                thirdRow.add(cbMusic);

                //TODO: There must be a code for selecting the current music settings.

                cbMusic.setBackground(Color.BLUE);
                cbMusic.setForeground(Color.WHITE);
                return thirdRow;

            case 4:

                JPanel fourthRow = new JPanel();
                JButton btnBackToMenu = new JButton("Go back to menu");
                fourthRow.add(btnBackToMenu);
                return fourthRow;



            default:
                return new JPanel();

        }
    }

}

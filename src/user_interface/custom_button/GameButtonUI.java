package user_interface.custom_button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

public class GameButtonUI extends BasicButtonUI implements java.io.Serializable, MouseListener, KeyListener {

    private final static GameButtonUI m_buttonUI = new GameButtonUI();

    protected Border m_borderRaised = UIManager.getBorder("Button.border");

    protected Border m_borderLowered = UIManager.getBorder("Button.borderPressed");

    protected Color m_backgroundNormal = UIManager.getColor("Button.background");;

    protected Color m_backgroundPressed = Color.LIGHT_GRAY;

    protected Color m_foregroundNormal = Color.BLACK;

    protected Color m_foregroundActive = Color.GRAY;

    protected Color m_focusBorder = Color.GREEN;

    public static ComponentUI createUI(JComponent c)
    {
        return m_buttonUI;
    }

    public void installUI(JComponent c)
    {
        super.installUI(c);

        c.addMouseListener(this);
        c.addKeyListener(this);
    }

    public void uninstallUI(JComponent c)
    {
        super.uninstallUI(c);

        c.removeMouseListener(this);
        c.removeKeyListener(this);
    }

    public void paint(Graphics g, JComponent c)
    {
        AbstractButton b = (AbstractButton) c;

        Dimension d = b.getSize();

        g.setFont(c.getFont());
        FontMetrics fm = g.getFontMetrics();

        g.setColor(b.getForeground());

        String caption = b.getText();

        int x = (getPreferredSize(c).width - fm.stringWidth(caption)) / 2;
        int y = (getPreferredSize(c).height + fm.getAscent()) / 2;

        g.drawString(caption, x, y);
    }

    public Dimension getPreferredSize(JComponent c)
    {
        Dimension d = new Dimension(100, 50);
        if (m_borderRaised != null) {
            Insets ins = m_borderRaised.getBorderInsets(c);
            d.setSize(d.width + ins.left + ins.right, d.height + ins.top
                    + ins.bottom);
        }
        return d;
    }

    public void mouseClicked(MouseEvent e) { }

    public void mousePressed(MouseEvent e)
    {
        JComponent c = (JComponent) e.getComponent();
        c.setBorder(m_borderLowered);
        c.setBackground(m_backgroundPressed);
    }

    public void mouseReleased(MouseEvent e)
    {
        JComponent c = (JComponent) e.getComponent();
        c.setBorder(m_borderRaised);
        c.setBackground(m_backgroundNormal);
    }

    public void mouseEntered(MouseEvent e)
    {
        JComponent c = (JComponent) e.getComponent();
        c.setBorder(m_borderRaised);
        c.setForeground(m_foregroundActive);
        c.repaint();
    }

    public void mouseExited(MouseEvent e)
    {
        JComponent c = (JComponent) e.getComponent();
        c.setBorder(m_borderLowered);
        c.setForeground(m_foregroundNormal);
        c.repaint();
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
            JComponent c = (JComponent) e.getComponent();
            c.setBorder(m_borderLowered);
            c.setBackground(m_backgroundPressed);
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
            JComponent c = (JComponent) e.getComponent();
            c.setBorder(m_borderRaised);
            c.setBackground(m_backgroundNormal);
        }
    }


}

package Saper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class JavaSweeper extends JFrame {
    private Game game;
    private JPanel panel;
    private JLabel label;
    private final int cols = 9;
    private final int rows = 9;
    private final int imageSize = 50;
    private final int bombs = 10;
    public JavaSweeper() {
        game = new Game(cols, rows, bombs);
        game.start();
        setImages();
        initLabel();
        Panel();
        Frame();
    }
    public void initLabel() {
        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);
    }
    public void Panel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()) {
                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x * imageSize, coord.y * imageSize,
                            this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / imageSize;
                int y = e.getY() / imageSize;
                Coord coord = new Coord(x, y);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeftButton(coord);
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    game.pressRightButton(coord);
                }
                if (e.getButton() == MouseEvent.BUTTON2) {
                    game.start();
                }
                label.setText(setMessege());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * imageSize,
                Ranges.getSize().y * imageSize));
        add(panel);
    }
    private String setMessege() {
        switch (game.getGameState()) {
            case PLAYED: return "think twice!";
            case BOMBED: return "BOOOOM! you lose";
            case WINNER: return "good work!!!";
            default: return "Welcome!";
        }
    }
    public void Frame() {
        setTitle("Сапер");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        pack();
        setLocationRelativeTo(null);
        setIconImage(getImage("icon"));
    }

    public void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name());
        }
    }
    public Image getImage(String name) {
        String fileName = "/img/" + name.toLowerCase() + ".png";
        ImageIcon icon = null;
        URL url = getClass().getResource(fileName);

        if (url != null) {
            icon = new ImageIcon(url);
        }
        else {
            System.out.println("Error getImage");
        }
            return icon.getImage();

    }
    public static void main(String[] args) {
        new JavaSweeper();
    }
}

package fr.levelEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * @author LE GLEAU Yoann
 * @version 1, 14/02/2023
 * @pakage fr.levelEditor
 */
public class MainLevelEditor {

    private static LevelEditor levelEditor;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Sliterlink Level Editor");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.setSize((int) (screenSize.getWidth() / 2),(int) (screenSize.getHeight() / 2));
        frame.setPreferredSize(screenSize);
        frame.setSize(screenSize);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        levelEditor = new LevelEditor();
        frame.setContentPane(levelEditor);
        frame.pack();
        frame.setVisible(true);

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                levelEditor.loadLevel();
            }
        });
    }
}

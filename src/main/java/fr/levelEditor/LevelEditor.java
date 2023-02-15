package fr.levelEditor;

import fr.slitherlink.save.Level;
import fr.slitherlink.save.LevelResourceManageur;
import fr.slitherlink.save.XmlResourcesManageur;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author LE GLEAU Yoann
 * @version 1, 14/02/2023
 * @pakage fr.levelEditor
 */
public class LevelEditor extends JPanel {
    private Level curentLevel;

    private boolean isModified;

    private JButton loadButton;
    private JButton saveButton;
    private JButton newButton;
    private JPanel rootPanel;
    private JTextField textFieldId;
    private JTextField textFieldSize;
    private JLabel size;
    private JTextField textFieldSearch;
    private JPanel gridPanel;
    private JButton sizeOkButton;


    public LevelEditor() {
        setModified(false);
        add(rootPanel);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curentLevel = LevelResourceManageur.LoadLevel(Integer.parseInt(textFieldSearch.getText()));
                setModified(false);
                loadLevel();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread t = new Thread(() -> {
                    LevelResourceManageur.saveLevel(curentLevel);
                    setModified(false);
                });
                t.start();

            }
        });

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newLevel();
                setModified(false);
            }
        });

        sizeOkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curentLevel.setSize(Integer.parseInt(textFieldSize.getText()));
                creegrille();
                setModified(true);
            }
        });

    }



    public void setModified(boolean modified) {
        isModified = modified;
        saveButton.setEnabled(isModified);
    }

    private void newLevel() {
        String dir = XmlResourcesManageur.RESOURCES_PATH + "Level";
        List<String> list = Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet())
                .stream().sorted().toList();
        int id;
        if (list.isEmpty())
            id = 1;
        else
            id = Integer.parseInt(list.get(list.size() - 1).replace(".xml", "")) + 1;
        curentLevel = new Level(id, 6);
        loadLevel();
    }

    public void loadLevel() {
        if (curentLevel == null)
            return;
        textFieldId.setText(String.valueOf(curentLevel.getId()));
        textFieldSize.setText(String.valueOf(curentLevel.getSize()));
        creegrille();

    }

    private void creegrille() {

        int size = curentLevel.getSize();
        double margin = 2.3;

        gridPanel.setLayout(new GridLayout(size, size));
        gridPanel.setMaximumSize(new Dimension((int) (getWidth() / margin), (int) (getWidth() / margin)));
        Dimension btnDimension = new Dimension((int) (getWidth() / (margin*size)), (int) (getWidth() / (margin*size)));

        gridPanel.removeAll();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton button = new JButton();
                int number = curentLevel.getGrid(i,j);
                if (number!=-1)
                    button.setText(String.valueOf(number));
                button.setPreferredSize(btnDimension);
                button.setBackground(Color.WHITE);
                button.addActionListener(new GridButtonListener(button ,i, j));
                button.setVisible(true);
                gridPanel.add(button);
            }
        }
        revalidate();
        repaint();
    }


    
    private class GridButtonListener implements ActionListener {

        JButton button;
        int row, column;

        public GridButtonListener(JButton button, int row, int column) {
            this.button = button;
            this.row = row;
            this.column = column;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            setModified(true);
            switch (button.getText()) {
                case "":
                    button.setText("0");
                    break;
                case "0":
                    button.setText("1");
                    break;
                case "1":
                    button.setText("2");
                    break;
                case "2":
                    button.setText("3");
                    break;
                case "3":
                    button.setText("");
                    break;
            }
            if (button.getText().equals(""))
                curentLevel.setGrid(-1, row, column);
            else
                curentLevel.setGrid(Integer.parseInt(button.getText()),row,column);

        }
    }
}

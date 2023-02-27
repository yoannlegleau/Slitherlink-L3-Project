package fr.levelEditor;

import fr.slitherlink.Grid;
import fr.slitherlink.save.PuzzleSave;
import fr.slitherlink.save.PuzzleResourceManageur;
import fr.slitherlink.save.XmlResourcesManageur;

import javax.swing.*;
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
 * @version 2, 23/02/2023
 * @pakage fr.levelEditor
 */
public class LevelEditor extends JPanel {
    private PuzzleSave curentLevel;

    private Grid grid;

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
                curentLevel = PuzzleResourceManageur.LoadPuzzle(Integer.parseInt(textFieldSearch.getText()));
                grid = curentLevel.getSolution();
                setModified(false);
                loadLevel();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curentLevel.setGameGrid(grid);
                Thread t = new Thread(() -> {
                    PuzzleResourceManageur.saveLevel(curentLevel);
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
                grid = new Grid(curentLevel.getSize());
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
        String dir = XmlResourcesManageur.RESOURCES_PATH + "puzzle";
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
        grid = new Grid(6);
        curentLevel = new PuzzleSave(id, grid);
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


        int windowSize ;
        double margin = 1.2;
        gridPanel.setLayout(new GridLayout(size, size));
        if (getHeight() < getWidth())
            windowSize = getHeight(); //getWidth();
        else
            windowSize = getWidth();


        gridPanel.setMaximumSize(new Dimension((int) (windowSize / margin), (int) (windowSize / margin)));
        Dimension btnDimension = new Dimension((int) (windowSize / (margin*size)), (int) (windowSize / (margin*size)));

        gridPanel.removeAll();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.PLAIN, btnDimension.height/2));
                Integer number = grid.getCell(i, j).getNumber();
                if (number!=null)
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
                grid.getCell(row, column).setNumber(null);
            else
                grid.getCell(row, column).setNumber(Integer.parseInt(button.getText()));

        }
    }
}

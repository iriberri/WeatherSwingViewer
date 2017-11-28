package com.weather_viewer.gui.previews.loading;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class LoadingPreview extends JFrame {
    private JPanel rootPanel;
    private JLabel gifLabel;

    public LoadingPreview(Component component) {
        setLocationRelativeTo(component);
        getContentPane().add(rootPanel);
        setUndecorated(true);
        setFocusableWindowState(true);
        setAlwaysOnTop(true);

        setBackground(new Color(0, 0, 0, 0));

        pack();
        setResizable(false);
        setVisible(true);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.setDoubleBuffered(true);
        rootPanel.setName("");
        rootPanel.setOpaque(false);
        rootPanel.setPreferredSize(new Dimension(256, 256));
        gifLabel = new JLabel();
        gifLabel.setDoubleBuffered(true);
        gifLabel.setIcon(new ImageIcon(getClass().getResource("/gif/search.gif")));
        gifLabel.setText("");
        rootPanel.add(gifLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}

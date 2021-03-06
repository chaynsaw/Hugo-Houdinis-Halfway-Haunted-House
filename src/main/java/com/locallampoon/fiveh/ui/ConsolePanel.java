package com.locallampoon.fiveh.ui;

import com.locallampoon.fiveh.core.Game;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConsolePanel implements KeyListener {
    JPanel panel;
    JTextArea textArea;
    Font normalFont = new Font(
            PanelStyles.Global.FONT_FAMILY,
            PanelStyles.Global.FONT_WEIGHT,
            PanelStyles.Global.FONT_SIZE
    );

    public ConsolePanel() {
        panel = new JPanel();
        panel.setBounds(
                PanelStyles.ConsolePanel.X,
                PanelStyles.ConsolePanel.Y,
                PanelStyles.ConsolePanel.WIDTH,
                PanelStyles.ConsolePanel.HEIGHT
        );
        panel.setBackground(PanelStyles.Global.BG_COLOR);
        panel.setBorder(new MatteBorder(0, 0, 0, 0, Color.WHITE));
        textArea = new JTextArea();
        disableKeys(textArea.getInputMap());
        textArea.setBounds(
                PanelStyles.ConsolePanel.TXT_AREA_X,
                PanelStyles.ConsolePanel.TXT_AREA_Y,
                PanelStyles.ConsolePanel.WIDTH,
                PanelStyles.ConsolePanel.HEIGHT
        );
        textArea.setFont(normalFont);
        textArea.setBackground(PanelStyles.Global.BG_COLOR);
        textArea.setForeground(PanelStyles.Global.FG_COLOR);
        textArea.addKeyListener(this);
        panel.add(textArea);
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public void setTextArea(String text) {
        textArea.setText(text);
    }

    public void executeCommand(String command) {
        disableConsole();
        Game.handleCommand(command);
        clear();
        enableConsole();
    }

    public void clear() {
        setTextArea("");
    }

    public void enableConsole() {
        textArea.setEnabled(true);
        textArea.setFocusable(true);
        textArea.requestFocusInWindow();
    }

    public void disableConsole() {
        textArea.setEnabled(false);
    }

    private void disableKeys(InputMap inputMap) {
        String[] keys = {"UP", "DOWN", "LEFT", "RIGHT", "TAB"};
        for (String key : keys) {
            inputMap.put(KeyStroke.getKeyStroke(key), "none");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == Key.ENTER.getKey()) {
            executeCommand(textArea.getText());
        } else if (keyCode == Key.UP.getKey()) {
            executeCommand(Command.GO_NORTH.getText());
        } else if (keyCode == Key.DOWN.getKey()) {
            executeCommand(Command.GO_SOUTH.getText());
        } else if (keyCode == Key.LEFT.getKey()) {
            executeCommand(Command.GO_WEST.getText());
        } else if (keyCode == Key.RIGHT.getKey()) {
            executeCommand(Command.GO_EAST.getText());
        } else if (keyCode == Key.PG_UP.getKey()) {
            executeCommand(Command.GO_UP.getText());
        } else if (keyCode == Key.PG_DOWN.getKey()) {
            executeCommand(Command.GO_DOWN.getText());
        } else if (keyCode == Key.TAB.getKey()) {
            executeCommand(Command.FIGHT.getText());
        } else if (keyCode == Key.F1.getKey() && !Game.isHelp) {
            executeCommand(Command.HELP.getText());
        } else if (keyCode == Key.F1.getKey()) {
            executeCommand(Command.QUIT_HELP.getText());
        } else if (keyCode == Key.ESC.getKey()) {
            executeCommand(Command.QUIT.getText());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == Key.ENTER.getKey()) {
            if (textArea.getCaretPosition() != 0) {
                // reset caret position
                textArea.setCaretPosition(0);
            }
        }
    }

    public JTextArea getTextArea() {
        return this.textArea;
    }
}
package com.locallampoon.fiveh.ui;

import javax.swing.*;
import java.awt.*;

public class MainPanel {
    JFrame window;
    Container container;
    IntroPanel introPanel;
    NarrativePanel narrativePanel;
    ActionPanel actionPanel;
    ConsolePanel consolePanel;
    ArtPanel artPanel;
    StatsPanel statsPanel;
    MapPanel mapPanel;

    public MainPanel(
            IntroPanel introPanel,
            NarrativePanel narrativePanel,
            ActionPanel actionPanel,
            ConsolePanel consolePanel,
            ArtPanel artPanel,
            StatsPanel statsPanel,
            MapPanel mapPanel
    ) {
        this.introPanel = introPanel;
        this.narrativePanel = narrativePanel;
        this.actionPanel = actionPanel;
        this.consolePanel = consolePanel;
        this.artPanel = artPanel;
        this.statsPanel = statsPanel;
        this.mapPanel = mapPanel;
        // main window settings
        window = new JFrame();
        window.setSize(PanelStyles.Window.WIDTH, PanelStyles.Window.HEIGHT);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(new BorderLayout());
        container = window.getContentPane();
        // add individual panels
        container.add(introPanel.getPanel());
        hideGame();
        window.setVisible(true);
    }

    public void hideGame() {
        narrativePanel.getPanel().setVisible(false);
        actionPanel.getPanel().setVisible(false);
        consolePanel.getPanel().setVisible(false);
        artPanel.getPanel().setVisible(false);
        statsPanel.getPanel().setVisible(false);
        mapPanel.getPanel().setVisible(false);
    }

    public void showGame() {
        container.add(narrativePanel.getPanel());
        container.add(actionPanel.getPanel());
        container.add(consolePanel.getPanel());
        container.add(artPanel.getPanel());
        container.add(statsPanel.getPanel());
        container.add(mapPanel.getPanel());
        narrativePanel.getPanel().setVisible(true);
        actionPanel.getPanel().setVisible(true);
        consolePanel.getPanel().setVisible(true);
        consolePanel.enableConsole();
        artPanel.getPanel().setVisible(true);
        statsPanel.getPanel().setVisible(true);
        mapPanel.getPanel().setVisible(true);
    }

    public void hideIntro() {
        introPanel.getPanel().setVisible(false);
    }

    public NarrativePanel getNarrativePanel() {
        return narrativePanel;
    }

    public ActionPanel getActionPanel() { return actionPanel; }

    public ArtPanel getArtPanel() {
        return artPanel;
    }

    public StatsPanel getStatsPanel() {
        return statsPanel;
    }

    public ConsolePanel getConsolePanel() {
        return consolePanel;
    }

    public MapPanel getMapPanel(){
        return this.mapPanel;
    }
}
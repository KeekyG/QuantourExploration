package presentation.controller;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ComparePaneController {
	
	@FXML
	private AnchorPane pane;
	
	@FXML
	private SwingNode logNode;
	
	@FXML
	private SwingNode varianceNode;
	
	@FXML
	private void initialization() {
		
	}
	
	public AnchorPane getPane(JPanel logPanel, JPanel variancePanel) {
		createSwingContent(logPanel, variancePanel);
		return pane;
	}
	
	private void createSwingContent(JPanel logPanel, JPanel variancePanel) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	logPanel.setMinimumSize(new Dimension(960, 300));
                logNode.setContent(logPanel);
                
                variancePanel.setMinimumSize(new Dimension(960, 300));
                varianceNode.setContent(variancePanel);
            }
        });
    }
}

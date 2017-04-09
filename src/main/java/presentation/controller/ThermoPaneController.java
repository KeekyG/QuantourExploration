package presentation.controller;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ThermoPaneController {

	@FXML
	private AnchorPane pane;
	
	@FXML
	private SwingNode node;
	
	
	@FXML
	private void initialize() {
		
	}
	
	public AnchorPane getAnchorPane(JPanel panel) {
		createSwingContent(panel);
		return pane;
	}
	
	private void createSwingContent(JPanel panel) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	panel.setMinimumSize(new Dimension(960, 640));
                node.setContent(panel);
                node.minWidth(640);
                node.minHeight(960);
            }
        });
    }
}

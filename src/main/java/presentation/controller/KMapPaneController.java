package presentation.controller;

import java.awt.Color;

import javax.swing.SwingUtilities;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class KMapPaneController {
	
	@FXML
	private AnchorPane pane;
	
	@FXML
	private SwingNode node;
	
	@FXML
	private void initialize() {
		
	}
	
	public AnchorPane getAnchorPane(JFreeChart chart) {
		createSwingContent(chart);
		return pane;
	}
	
	private void createSwingContent(JFreeChart chart) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	ChartPanel panel = new ChartPanel(chart);
            	panel.setSize(960, 640);
                node.setContent(panel);
                node.minWidth(640);
                node.minHeight(960);
            }
        });
    }
	
	
}

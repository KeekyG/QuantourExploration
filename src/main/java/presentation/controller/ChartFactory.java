package presentation.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.jfree.chart.JFreeChart;

import bl.KMapBl;
import bl.StockBl;
import blService.KMapBlService;
import blService.StockBlService;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import presentation.Runner;
import presentation.fxml.KMapPaneController;
import utility.TimeUtility;
import utility.VerifyUtility;
import vo.ShareLineVO;
import vo.StockShareVO;

public class ChartFactory {
	
	private StockBlService stockBl;
	private KMapBlService kMapBl;
	
	public ChartFactory() {
		this.stockBl = new StockBl();
		this.kMapBl = new KMapBl();
	}
	
	public AnchorPane getKMap(LocalDate beginDate, LocalDate endDate, String content) {
		ShareLineVO shareLineVO;
		if (VerifyUtility.isInteger(content)) {
			shareLineVO = stockBl.getShareLine(TimeUtility.localDateToDate(beginDate), TimeUtility.localDateToDate(endDate), content);
		} else {
			shareLineVO = stockBl.getShareLineByName(TimeUtility.localDateToDate(beginDate), TimeUtility.localDateToDate(endDate), content);
		}
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Runner.class.getResource("fxml/KMapPane.fxml"));
		AnchorPane anchorPane = null;
		try {
			anchorPane = (AnchorPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		KMapPaneController controller = loader.getController();
		
		return controller.getAnchorPane(kMapBl.kTest(shareLineVO));
	}
}

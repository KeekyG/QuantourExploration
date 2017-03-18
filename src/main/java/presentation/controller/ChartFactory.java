package presentation.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

import javax.swing.JPanel;

import bl.CompareBl;
import bl.KMapBl;
import bl.MarketThermometerBl;
import bl.StockBl;
import blService.CompareBlService;
import blService.KMapBlService;
import blService.MarketThermometerBlService;
import blService.StockBlService;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import presentation.Runner;
import utility.TimeUtility;
import utility.VerifyUtility;
import vo.ShareLineVO;
import vo.StockCodesVO;

public class ChartFactory {
	
	private StockBlService stockBl;
	private KMapBlService kMapBl;
	private MarketThermometerBlService marketThermometerBl;
	
	public ChartFactory() {
		this.stockBl = new StockBl();
		this.kMapBl = new KMapBl();
		this.marketThermometerBl = new MarketThermometerBl();
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
		AnchorPane pane = null;
		try {
			pane = (AnchorPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		KMapPaneController controller = loader.getController();
		
		return controller.getAnchorPane(kMapBl.kTest(shareLineVO));
	}
	
	public AnchorPane getThermo(LocalDate date) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Runner.class.getResource("fxml/ThermoPane.fxml"));
		AnchorPane pane = null;
		try {
			pane = (AnchorPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ThermoPaneController controller = loader.getController();
		return controller.getAnchorPane(marketThermometerBl.drawThermometer(TimeUtility.localDateToDate(date)));
	}
	
	public AnchorPane getCompareResult(String firstContent, String secondContent, LocalDate beginDate, LocalDate endDate) {
		HashMap<String, String> nameMap = StockCodesVO.getInstance().getNameMap();
		if (!VerifyUtility.isInteger(firstContent)) {
			firstContent = nameMap.get(firstContent);
		}
		if (!VerifyUtility.isInteger(secondContent)) {
			secondContent = nameMap.get(secondContent);
		}
		
		CompareBlService compareBlService = new CompareBl(firstContent, secondContent, TimeUtility.localDateToDate(beginDate), TimeUtility.localDateToDate(endDate));
		JPanel logPanel = compareBlService.drawLogValue();
		JPanel variancePanel = compareBlService.drawLastValue();
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Runner.class.getResource("fxml/ComparePane.fxml"));
		AnchorPane pane = null;
		try {
			pane = (AnchorPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ComparePaneController controller = loader.getController();
		return controller.getPane(logPanel, variancePanel);
	}
}

package presentation.controller;

import java.io.IOException;
import java.time.LocalDate;
import bl.KMapBl;
import bl.MarketThermometerBl;
import bl.StockBl;
import blService.KMapBlService;
import blService.MarketThermometerBlService;
import blService.StockBlService;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import presentation.Runner;
import utility.TimeUtility;
import utility.VerifyUtility;
import vo.ShareLineVO;

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
		AnchorPane anchorPane = null;
		try {
			anchorPane = (AnchorPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		KMapPaneController controller = loader.getController();
		
		return controller.getAnchorPane(kMapBl.kTest(shareLineVO));
	}
	
	public AnchorPane getThermo(LocalDate date) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Runner.class.getResource("fxml/ThermoPane.fxml"));
		AnchorPane anchorPane = null;
		try {
			anchorPane = (AnchorPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("before");
		ThermoPaneController controller = loader.getController();
		System.out.println("after");
		System.out.println(controller);
		
		return controller.getAnchorPane(marketThermometerBl.drawThermometer(TimeUtility.localDateToDate(date)));
	}
}

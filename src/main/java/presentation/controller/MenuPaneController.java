package presentation.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.controlsfx.control.textfield.TextFields;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;
import utility.VerifyUtility;
import vo.StockCodesVO;

public class MenuPaneController {

	@FXML
	private AnchorPane pane;

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private VBox listBox;

	@FXML
	private VBox stockBox;

	@FXML
	private VBox compareBox;

	@FXML
	private VBox thermoBox;

	@FXML
	private ToggleButton stockToggleButton;

	@FXML
	private ToggleButton compareToggleButton;

	@FXML
	private ToggleButton thermoToggleButton;

	@FXML
	private ToggleGroup toggleGroup;

	@FXML
	private TextField stockTextField;

	@FXML
	private DatePicker stockBeginDatePicker;

	@FXML
	private DatePicker stockEndDatePicker;

	@FXML
	private Button stockButton;

	@FXML
	private TextField compareFirstTextField;

	@FXML
	private TextField compareSecondTextField;

	@FXML
	private DatePicker compareBeginDatePicker;

	@FXML
	private DatePicker compareEndDatePicker;

	@FXML
	private Button compareButton;

	@FXML
	private DatePicker thermoDatePicker;

	@FXML
	private Button thermoButton;
	
	@FXML
	private AnchorPane centerPane;

	private ArrayList<Node> stockNodeList;

	private ArrayList<Node> compareNodeList;

	private ArrayList<Node> thermoNodeList;
	
	private ChartFactory factory;

	@FXML
	private void initialize() {
		stockNodeList = new ArrayList<>();
		compareNodeList = new ArrayList<>();
		thermoNodeList = new ArrayList<>();
		stockNodeList.addAll(stockBox.getChildren());
		compareNodeList.addAll(compareBox.getChildren());
		thermoNodeList.addAll(thermoBox.getChildren());
		stockBox.getChildren().clear();
		compareBox.getChildren().clear();
		thermoBox.getChildren().clear();
		bind(stockTextField);
		bind(compareFirstTextField);
		bind(compareSecondTextField);
		forbidDate(stockBeginDatePicker);
		forbidDate(stockEndDatePicker);
		forbidDate(compareBeginDatePicker);
		forbidDate(compareEndDatePicker);
		forbidDate(thermoDatePicker);
	}

	@FXML
	private void handleStockToggleButton() {
		showContents(stockBox, stockNodeList);
	}

	@FXML
	private void handleCompareToggleButton() {
		showContents(compareBox, compareNodeList);
	}

	@FXML
	private void handleThermoToggleButton() {
		showContents(thermoBox, thermoNodeList);
	}
	
	@FXML
	private void handleStockInquireButton() {
		if(verifyStock(stockTextField) && verifyDateBefore(stockBeginDatePicker, stockEndDatePicker)) {
			AnchorPane pane = factory.getKMap(stockBeginDatePicker.getValue(), stockEndDatePicker.getValue(), stockTextField.getText());
			centerPane.getChildren().clear();
			centerPane.getChildren().add(pane);
		}
		
	}
	
	@FXML
	private void handleCompareInquireButton() {
		if(verifyStock(compareFirstTextField) && verifyStock(compareSecondTextField) && verifyDateBefore(compareBeginDatePicker, compareEndDatePicker)){
			AnchorPane pane = factory.getCompareResult(compareFirstTextField.getText(), compareSecondTextField.getText(), compareBeginDatePicker.getValue(), compareEndDatePicker.getValue());
			centerPane.getChildren().clear();
			centerPane.getChildren().add(pane);
		}
		
	}
	
	@FXML
	private void handleThermoInquireButton() {
		if(verifyDate(thermoDatePicker)) {
			AnchorPane pane = factory.getThermo(thermoDatePicker.getValue());
			centerPane.getChildren().clear();
			centerPane.getChildren().add(pane);
		}
	}

	private void showContents(VBox box, ArrayList<Node> nodes) {
		if (box.getChildren().isEmpty()) {
			hide();
			box.getChildren().addAll(nodes);
			fade(box);
		} else {
			box.getChildren().clear();
		}
	}

	private void hide() {
		stockBox.getChildren().clear();
		compareBox.getChildren().clear();
		thermoBox.getChildren().clear();
	}

	private void fade(Node no) {
		FadeTransition fade = new FadeTransition(Duration.seconds(1), no);
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.setCycleCount(1);
		fade.setAutoReverse(true);
		fade.play();
	}

	private void bind(TextField textField) {
		TextFields.bindAutoCompletion(textField, StockCodesVO.getInstance().getCodeList());
		TextFields.bindAutoCompletion(textField, StockCodesVO.getInstance().getNameMap().keySet());
	}
	
	private void forbidDate(DatePicker datePicker) {
		datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {

			@Override
			public DateCell call(DatePicker param) {
				return new ForbidDateCell();
			}
		});		
	}
	
	private boolean verifyStock(TextField textField) {
		String content = textField.getText();
		if (content == null || content.isEmpty()) {
			VerifyUtility.showWarning("输入为空", "请输入股票信息");
			return false;
		} else if (!StockCodesVO.getInstance().getCodeList().contains(content) && !StockCodesVO.getInstance().getNameMap().keySet().contains(content)) {
			VerifyUtility.showWarning("输入错误", "不存在股票"+content);
			return false;
		} else {
			return true;
		}
	}
	
	private boolean verifyDate(DatePicker datePicker) {
		if (datePicker.getValue() == null) {
			VerifyUtility.showWarning("日期为空", "请选择日期");
			return false;
		} else {
			return true;
		}
	}
	
	private boolean verifyDateBefore(DatePicker beforeDatePicker, DatePicker latterDatePicker) {
		if (verifyDate(beforeDatePicker) && verifyDate(latterDatePicker)) {
			LocalDate before = beforeDatePicker.getValue();
			LocalDate latter = latterDatePicker.getValue();
			if (!before.isBefore(latter)) {
				VerifyUtility.showWarning("日期顺序错误", "开始日期应该在结束日期之前");
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	public void setFactory(ChartFactory factory) {
		this.factory = factory;
	}
}

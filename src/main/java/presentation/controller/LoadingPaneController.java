package presentation.controller;

import com.jfoenix.controls.JFXProgressBar;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import vo.StockCodesVO;

public class LoadingPaneController {

	@FXML 
	private AnchorPane pane;
	
	
	public LoadingPaneController() {
		
	}
	
	@FXML
	private void initialize() {
		
		initCodes();
	}

	private void initCodes() {
		//System.out.println(bar);
		/*StockCodesVO stockCodesVO = StockCodesVO.getInstance();
		System.out.println(stockCodesVO.getCodeList());*/
		
		Task<Void> progressTask = new Task<Void>(){  
			  
            @Override  
            protected void succeeded() {  
                super.succeeded();   
                updateMessage("Succeeded");  
            }  
  
            @Override  
            protected void cancelled() {  
                super.cancelled();   
                updateMessage("Cancelled");  
            }  
  
            @Override  
            protected void failed() {  
                super.failed();   
                updateMessage("Failed");  
            }  
  
            @Override  
            protected Void call() throws Exception {  
            	StockCodesVO stockCodesVO = StockCodesVO.getInstance();
        		System.out.println(stockCodesVO.getCodeList());
                return null;  
            }  
      };   
		
		JFXProgressBar bar = new JFXProgressBar();
		bar.setPrefWidth(400);
		/*Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(bar.progressProperty(), 0)),
				new KeyFrame(Duration.seconds(5), new KeyValue(bar.progressProperty(), 1)));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();*/
		bar.progressProperty().bind(progressTask.progressProperty());
		pane.getChildren().add(bar);
		AnchorPane.setBottomAnchor(bar, (double) 20);
		
		new Thread(progressTask).start();   
	}
	
}

package presentation;

import java.io.IOException;

import com.jfoenix.controls.JFXProgressBar;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vo.StockCodesVO;

public class Runner extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Runner.class.getResource("fxml/LoadingPane.fxml"));
		AnchorPane pane = null;
		try {
			pane = (AnchorPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(pane);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		load(pane, primaryStage);
		//primaryStage.close();
		
	}
	
	private void load(AnchorPane pane, Stage primaryStage) {
		Task<Integer> progressTask = new Task<Integer>(){  
			  
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
            protected Integer call() throws Exception {  
            	StockCodesVO stockCodesVO = StockCodesVO.getInstance();
        		System.out.println(stockCodesVO.getCodeList());
        		long sysDate = System.currentTimeMillis();
        		while (true) {
					if (System.currentTimeMillis() - sysDate > 2500) {
						break;
					}
				}
                return 1;
                
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
		
		Thread taskThread = new Thread(progressTask);
		taskThread.start();
		
		progressTask.valueProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				if (newValue.intValue() == 1) {
					System.out.println("hhh");
					newWindow(primaryStage);
				}
			}
		});
		
	}
	
	private void newWindow(Stage primaryStage) {
		primaryStage.close();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

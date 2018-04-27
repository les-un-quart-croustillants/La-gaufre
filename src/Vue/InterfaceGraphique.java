package Vue;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InterfaceGraphique extends Application {

	final static float dt = 0.0166f; //temps entre 2 frames en s (60 fps)
	
	public static void creer(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		PanePrincipal gp = new PanePrincipal();
        Scene s = new Scene(gp, 800, 600);
        primaryStage.setScene(s);
		primaryStage.show();
		
		/*new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {
				//gp.Update(); ??
				gp.Draw();
			}
		}.start();*/
		gp.Draw();
		
		s.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override 
		    public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	gp.Draw();
		    }
		});
		s.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
		        gp.Draw();
		    }
		});

	}

}

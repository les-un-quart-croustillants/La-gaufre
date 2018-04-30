package Vue;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
		primaryStage.setMinHeight(400);
		primaryStage.setMinWidth(400);
		
		new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {
				gp.Update();
				gp.Draw();
			}
		}.start();

	}

}

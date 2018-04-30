package Vue;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Vue.PaneMenu.MenuAction;

public class InterfaceGraphique extends Application {
	public enum Appli_state {
		MENU(0), NEW_GAME(1), LOAD(2), QUIT(3);
		
		public int value;
		
		Appli_state(int value) {
			this.value = value;
		}
	}
	
	final static float dt = 0.0166f; //temps entre 2 frames en s (60 fps)
	Appli_state etat = Appli_state.MENU;
	
	public static void creer(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {	
		PanePrincipal gp = new PanePrincipal();
		PaneMenu m = new PaneMenu(gp.getWidth(), gp.getHeight());
        Scene partie = new Scene(gp, 800, 600);
        Scene menu = new Scene(m, 800, 600);
        primaryStage.setScene(menu);  
		primaryStage.show();
		primaryStage.setMinHeight(400);
		primaryStage.setMinWidth(400);
		
		new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {
				switch(etat) {
				case MENU:
					switch(m.Choix()) {
					case NEW_GAME:
						primaryStage.setScene(partie);
						etat = Appli_state.NEW_GAME;
						return;
					case QUIT:
						// On ferme l'appli
						primaryStage.close();
						break;
					case LOAD:
						etat = Appli_state.LOAD;
						break;
					case NOTHING:
						// On ne fait rien
						break;
					}
					break;
				case NEW_GAME:
					// On joue la partie comme avant
					gp.Update();
					gp.Draw();
					break;
				case LOAD:
					// Code du chargement
					break;
				}
				
			}
		}.start();
	}
}

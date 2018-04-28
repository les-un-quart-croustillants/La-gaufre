package Vue;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ImageBouton extends Button {
	private String STYLE_NORMAL = "-fx-background-size: 80%; -fx-background-position: center;\r\n"
			+ "-fx-background-repeat: no-repeat; -fx-background-color: transparent;";
	private String STYLE_PRESSED = "-fx-background-size: 100%; -fx-background-position: center;\r\n"
			+ "-fx-background-repeat: no-repeat; -fx-background-color: transparent;";
	private String STYLE_ENTERED = "-fx-background-size: 100%; -fx-background-position: center;\r\n"
			+ "-fx-background-repeat: no-repeat; -fx-background-color: transparent;";;
			

	public ImageBouton(String img) {
		
		STYLE_NORMAL+="-fx-background-image: url("+img+");";
		STYLE_PRESSED+="-fx-background-image: url("+img+");";
		STYLE_ENTERED+="-fx-background-image: url("+img+");";

		setStyle(STYLE_NORMAL);

		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setStyle(STYLE_PRESSED);
			}
		});

		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setStyle(STYLE_NORMAL);
			}
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setStyle(STYLE_ENTERED);
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setStyle(STYLE_NORMAL);
			}
		});
	}
}

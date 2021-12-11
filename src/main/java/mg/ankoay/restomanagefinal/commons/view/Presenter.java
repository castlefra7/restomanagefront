package mg.ankoay.restomanagefinal.commons.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Presenter {
	protected Scene scene;

	private Stage primaryStage;

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Scene getScene() {
		return scene;
	}

}

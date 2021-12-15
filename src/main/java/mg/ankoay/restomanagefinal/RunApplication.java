package mg.ankoay.restomanagefinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mg.ankoay.restomanagefinal.login.view.LoginCtl;
import mg.ankoay.restomanagefinal.login.view.LoginPresenter;

public class RunApplication extends Application {
	protected static int counter = 0;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("POS Restaurant");
		
		FXMLLoader login = new FXMLLoader(
				getClass().getResource("/mg/ankoay/restomanagefinal/login/view/Login.fxml"));
		Parent root = login.load();
		LoginCtl loginCtl = login.getController();

		Scene scene = new Scene(root);

		LoginPresenter loginPres = new LoginPresenter(loginCtl, scene);
		loginPres.setPrimaryStage(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setFullScreenExitHint("");
	}
}

//Screen screen = Screen.getPrimary();
//Rectangle2D bounds = screen.getVisualBounds();
//Scene scene = new Scene(root, bounds.getWidth() - 100, bounds.getHeight()
//-100);
//protected Stage lastOpenStage;
//this.lastOpenStage = primaryStage;
//Button openButton = new Button("Open");
//openButton.setOnAction(e -> open(++counter));
//root.setBottom(openButton);
//	private void open(int stageNumber) {
//		Stage stage = new Stage();
//		stage.setTitle("#" + stageNumber);
//		Button sayHelloButton = new Button("Say Hello");
//		sayHelloButton.setOnAction(e -> System.out.println("Hello from #" + stageNumber));
//		Button openButton = new Button("Open");
//		openButton.setOnAction(e -> open(++counter));
//		VBox root = new VBox();
//		root.getChildren().addAll(sayHelloButton, openButton);
//		Scene scene = new Scene(root, 200, 200);
//		stage.setScene(scene);
//		stage.setX(this.lastOpenStage.getX() + 50);
//		stage.setY(this.lastOpenStage.getY() + 50);
//		this.lastOpenStage = stage;
//		System.out.println("Before stage.showAndWait(): " + stageNumber);
//		// Show the stage and wait for it to close
//		stage.showAndWait();
//		System.out.println("After stage.showAndWait(): " + stageNumber);
//	}

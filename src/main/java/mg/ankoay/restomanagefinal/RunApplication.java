package mg.ankoay.restomanagefinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mg.ankoay.restomanagefinal.productlist.model.ProductListModel;

public class RunApplication extends Application {
	protected static int counter = 0;
	protected Stage lastOpenStage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ProductListModel.getInstance().loadData();

		FXMLLoader prdList = new FXMLLoader(
				getClass().getResource("/mg/ankoay/restomanagefinal/productlist/view/ProductList.fxml"));
		Parent root = prdList.load();

// Show the Scene
		// Screen screen = Screen.getPrimary();
		// Rectangle2D bounds = screen.getVisualBounds();
		// Scene scene = new Scene(root, bounds.getWidth() - 100, bounds.getHeight() -
		// 100);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setFullScreen(true);

		this.lastOpenStage = primaryStage;
	}

	// Button openButton = new Button("Open");
	// openButton.setOnAction(e -> open(++counter));
	// root.setBottom(openButton);
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
}

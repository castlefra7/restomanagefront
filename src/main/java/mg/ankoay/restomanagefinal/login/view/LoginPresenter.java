package mg.ankoay.restomanagefinal.login.view;

import org.apache.http.conn.HttpHostConnectException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Screen;
import mg.ankoay.restomanagefinal.commons.model.User;
import mg.ankoay.restomanagefinal.commons.view.Presenter;
import mg.ankoay.restomanagefinal.menu.view.MenuCtl;
import mg.ankoay.restomanagefinal.menu.view.MenuPresenter;

public class LoginPresenter extends Presenter {
	LoginCtl view;
	User model;

	public LoginPresenter(LoginCtl _view, Scene _scene) {
		this.model = User.getInstance();
		this.view = _view;
		this.scene = _scene;
		attachListeners();
	}

	private void attachListeners() {
		this.view.btnLogin.setOnAction(event -> {
			// System.out.println(this.model.getName() + " " + this.model.getPassword());
			try {

				if (this.model.login()) {
					this.getPrimaryStage().setTitle("Pos Restaurant - " + User.getInstance().getName().toUpperCase());
					showMenu();
				}
			} catch (HttpHostConnectException ex) {
				ex.printStackTrace();

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur de connexion");
				alert.setHeaderText("Veuillez vérifier que le serveur est disponible");

				alert.showAndWait();
			} catch (Exception ex) {
				ex.printStackTrace();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur de connexion");
				alert.setHeaderText("Veuillez vérifier vos informations de connexion");
				alert.showAndWait();
			}

		});
	}

	private void showMenu() {
		// Open Scene
		try {
			FXMLLoader menu = new FXMLLoader(getClass().getResource("/mg/ankoay/restomanagefinal/menu/view/Menu.fxml"));
			Parent root = menu.load();
			MenuCtl menuCtl = menu.getController();

			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight() - 32);
			scene.getStylesheets()
					.add(getClass().getResource("/mg/ankoay/restomanagefinal/menu/view/menu.css").toExternalForm());

			MenuPresenter menuPres = new MenuPresenter(menuCtl, scene);
			menuPres.setPrimaryStage(this.getPrimaryStage());

			this.getPrimaryStage().setScene(scene);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

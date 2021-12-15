package mg.ankoay.restomanagefinal.login.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import mg.ankoay.restomanagefinal.commons.model.User;
import mg.ankoay.restomanagefinal.commons.view.Presenter;
import mg.ankoay.restomanagefinal.menu.view.MenuCtl;
import mg.ankoay.restomanagefinal.menu.view.MenuPresenter;
import mg.ankoay.restomanagefinal.productlist.model.ProductListModel;
import mg.ankoay.restomanagefinal.productlist.view.ProductListPresenter;

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
			//System.out.println(this.model.getName() + " " + this.model.getPassword());
			showMenu();
		});
	}

	private void showMenu() {
		// Open Scene
		try {
			ProductListModel.getInstance().loadData();

			FXMLLoader menu = new FXMLLoader(
					getClass().getResource("/mg/ankoay/restomanagefinal/menu/view/Menu.fxml"));
			Parent root =  menu.load();
			MenuCtl menuCtl =  menu.getController();

			Scene scene = new Scene(root);

			MenuPresenter menuPres = new MenuPresenter(menuCtl, scene);
			menuPres.setPrimaryStage(this.getPrimaryStage());

			this.getPrimaryStage().setScene(scene);
			//this.getPrimaryStage().setFullScreen(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

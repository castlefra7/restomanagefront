package mg.ankoay.restomanagefinal.closecashier.view;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import mg.ankoay.restomanagefinal.closecashier.model.CloseCashierModel;
import mg.ankoay.restomanagefinal.commons.view.Presenter;

public class CloseCashierPresenter extends Presenter {
	CloseCashierCtl view;
	CloseCashierModel model;
	private final Scene sceneMenu;

	public CloseCashierPresenter(CloseCashierCtl _view, Scene _parent, Scene _sceneMenu) {
		this.view = _view;
		this.scene = _parent;
		this.sceneMenu = _sceneMenu;
		this.model = CloseCashierModel.getInstance();
		attachListeners();
	}

	private void attachListeners() {
		this.view.btnBack.setOnAction(event -> {
			this.getPrimaryStage().setScene(this.sceneMenu);
		});

		this.view.btnValidate.setOnAction(event -> {
			try {
				// Insert the Information
				if (this.model.close()) {
					// change the stage to menu stage
					this.getPrimaryStage().setScene(this.sceneMenu);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText(ex.getMessage());
				alert.showAndWait();
			}

		});
	}

}

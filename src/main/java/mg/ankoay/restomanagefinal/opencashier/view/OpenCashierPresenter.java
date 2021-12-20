package mg.ankoay.restomanagefinal.opencashier.view;

import javafx.scene.Scene;
import mg.ankoay.restomanagefinal.commons.view.Presenter;
import mg.ankoay.restomanagefinal.opencashier.model.OpenCashierModel;

public class OpenCashierPresenter extends Presenter {
	OpenCashierCtl view;
	OpenCashierModel model;
	private final Scene sceneMenu;
	
	public OpenCashierPresenter(OpenCashierCtl _view, Scene _parent, Scene _sceneMenu) {
		this.view = _view;
		this.scene = _parent;
		this.sceneMenu = _sceneMenu;
		this.model = OpenCashierModel.getInstance();
		attachListeners();
	}
	
	private void attachListeners() {
		this.view.btnBack.setOnAction(event -> {
			this.getPrimaryStage().setScene(this.sceneMenu);
		});
		
		this.view.btnValidate.setOnAction(event -> {
			try {
				// Insert the Information
				if(this.model.open()) {
					// change the stage to menu stage
					this.getPrimaryStage().setScene(this.sceneMenu);
				}	
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
		});
	}
	
}

package mg.ankoay.restomanagefinal.menu.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import mg.ankoay.restomanagefinal.commons.view.Presenter;
import mg.ankoay.restomanagefinal.productlist.model.ProductListModel;
import mg.ankoay.restomanagefinal.productlist.view.ProductListCtl;
import mg.ankoay.restomanagefinal.productlist.view.ProductListPresenter;

public class MenuPresenter extends Presenter {
	MenuCtl view;

	public MenuPresenter(MenuCtl _view, Scene _parent) {
		this.view = _view;
		this.scene = _parent;
		attachListeners();
	}
	
	private void attachListeners() {
		this.view.btnjourneyBegin.setOnAction(event -> {
			showProductList();
		});
	}
	
	private void showProductList() {
		// Open Scene
		try {
			ProductListModel.getInstance().loadData();

			FXMLLoader prdList = new FXMLLoader(
					getClass().getResource("/mg/ankoay/restomanagefinal/productlist/view/ProductList.fxml"));
			Parent root = prdList.load();
			ProductListCtl productListCtl = prdList.getController();

			Scene scene = new Scene(root);

			ProductListPresenter productListPres = new ProductListPresenter(productListCtl, scene);
			productListPres.setPrimaryStage(this.getPrimaryStage());

			this.getPrimaryStage().setScene(scene);
			this.getPrimaryStage().setFullScreen(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

package mg.ankoay.restomanagefinal.menu.view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Screen;
import mg.ankoay.restomanagefinal.closecashier.model.CloseCashierModel;
import mg.ankoay.restomanagefinal.closecashier.view.CloseCashierCtl;
import mg.ankoay.restomanagefinal.closecashier.view.CloseCashierPresenter;
import mg.ankoay.restomanagefinal.commons.view.Presenter;
import mg.ankoay.restomanagefinal.opencashier.view.OpenCashierCtl;
import mg.ankoay.restomanagefinal.opencashier.view.OpenCashierPresenter;
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
		this.view.btnPOS.setOnAction(event -> {
			showProductList();
		});
		this.view.btnJourneyBegin.setOnAction(event -> {
			showOpenCashier();
		});
		this.view.btnJourneyEnd.setOnAction(event -> {
			showCloseCashier();
		});
	}
	
	private void showCloseCashier() {
		try {
			CloseCashierModel.getInstance().loadData();
			FXMLLoader closeCash = new FXMLLoader(
					getClass().getResource("/mg/ankoay/restomanagefinal/closecashier/view/CloseCashier.fxml"));
			Parent root = closeCash.load();
			CloseCashierCtl closeCashCtl = closeCash.getController();

			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight() - 32);
			scene.getStylesheets().add( getClass().getResource("/mg/ankoay/restomanagefinal/closecashier/view/closecashier.css").
					toExternalForm());

			CloseCashierPresenter closeCashPres = new CloseCashierPresenter(closeCashCtl, scene, this.getScene());
			closeCashPres.setPrimaryStage(this.getPrimaryStage());

			this.getPrimaryStage().setScene(scene);

		} catch (Exception ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText(ex.getMessage());
			alert.showAndWait();
		}
	}

	private void showOpenCashier() {
		try {
			FXMLLoader openCashier = new FXMLLoader(
					getClass().getResource("/mg/ankoay/restomanagefinal/opencashier/view/OpenCashier.fxml"));
			Parent root = openCashier.load();
			OpenCashierCtl openCashierCtl = openCashier.getController();

			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight() - 32);
			scene.getStylesheets().add( getClass().getResource("/mg/ankoay/restomanagefinal/opencashier/view/opencashier.css").
					toExternalForm());

			OpenCashierPresenter openCashierPres = new OpenCashierPresenter(openCashierCtl, scene, this.getScene());
			openCashierPres.setPrimaryStage(this.getPrimaryStage());

			this.getPrimaryStage().setScene(scene);

		} catch (Exception ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText(ex.getMessage());
			alert.showAndWait();
		}
	}

	private void showProductList() {
		try {
// TODO: Check that the cashier is open	
			ProductListModel.getInstance().loadData();

			FXMLLoader prdList = new FXMLLoader(
					getClass().getResource("/mg/ankoay/restomanagefinal/productlist/view/ProductList.fxml"));
			Parent root = prdList.load();
			ProductListCtl productListCtl = prdList.getController();

			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight() - 32);
			scene.getStylesheets().add( getClass().getResource("/mg/ankoay/restomanagefinal/productlist/view/productlist.css").
					toExternalForm());

			ProductListPresenter productListPres = new ProductListPresenter(productListCtl, scene, this.getScene());
			productListPres.setPrimaryStage(this.getPrimaryStage());

			this.getPrimaryStage().setScene(scene);

		} catch (Exception ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText(ex.getMessage());
			alert.showAndWait();
		}
	}

}

package mg.ankoay.restomanagefinal.productorders.view;

import java.time.LocalDate;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Screen;
import mg.ankoay.restomanagefinal.commons.model.Product;
import mg.ankoay.restomanagefinal.commons.model.Table;
import mg.ankoay.restomanagefinal.commons.view.Presenter;
import mg.ankoay.restomanagefinal.productlist.model.ProductListModel;
import mg.ankoay.restomanagefinal.productlist.view.ProductListCtl;
import mg.ankoay.restomanagefinal.productlist.view.ProductListPresenter;
import mg.ankoay.restomanagefinal.productorders.model.ProductOrder;
import mg.ankoay.restomanagefinal.productorders.model.ProductOrderModel;

public class ProductOrderPresenter extends Presenter {
	private ProductOrderCtl view;
	private ProductOrderModel model;
	private final Scene scenePrdList;
	private final Scene sceneMenu;

	public ProductOrderPresenter(ProductOrderCtl _view, Scene _scene, Scene _scenePrdList, Scene _sceneMenu) {
		this.model = ProductOrderModel.getInstance();
		this.view = _view;
		this.scene = _scene;
		this.scenePrdList = _scenePrdList;
		this.sceneMenu = _sceneMenu;
		this.attachEvents();
	}

	public void attachEvents() {

		this.model.getProducOrdertSelected().addListener((obs, oldVal, newVal) -> {
			if (newVal == null) {
				this.view.btnUpdate.setDisable(true);
				this.view.btnPay.setDisable(true);
				this.view.txtOrderDetailsDt.setText("");
				this.view.tblOrderDetails.getItems().clear();
				this.view.txtOrderDetailsTable.setText("");
				this.view.syncTxtOrderDetailsTotal();
			}
		});

		this.view.btnPrev.setOnAction(event -> {
			LocalDate dt = this.view.dtpDate.getValue();
			LocalDate prev = dt.minusDays(1);
			this.view.dtpDate.setValue(prev);
		});

		this.view.btnNext.setOnAction(event -> {
			LocalDate dt = this.view.dtpDate.getValue();
			LocalDate next = dt.plusDays(1);

			this.view.dtpDate.setValue(next);
		});

		this.view.dtpDate.setOnAction(event -> {
			try {
				ProductOrderModel.getInstance().loadData(this.view.dtpDate.getValue().toString());
			} catch (Exception ex) {
				ex.printStackTrace();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText(ex.getMessage());

				alert.showAndWait();
			}
		});

		this.view.btnBack.setOnAction(e -> {
			// if (ProductListModel.getInstance().isUpdateProperty().get())
			try {
				ProductListModel.getInstance().loadData();

				this.getPrimaryStage().setScene(this.scenePrdList);
			} catch (Exception ex) {
				ex.printStackTrace();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText(ex.getMessage());

				alert.showAndWait();
			}

		});

		this.view.tblOrdersUnpaid.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newVal) -> {
			showSlctOrder(newVal, false);
		});

		this.view.tblOrdersPaid.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newVal) -> {
			showSlctOrder(newVal, true);
		});

		this.view.tblOrdersUnpaid.focusedProperty().addListener((obs, old, newVal) -> {
			if (newVal == false) {
				if (this.view.tblOrdersUnpaid.getSelectionModel().isEmpty() == false) {
					this.view.tblOrdersUnpaid.getSelectionModel().clearSelection();
				}
			}
		});

		this.view.tblOrdersPaid.focusedProperty().addListener((obs, old, newVal) -> {
			if (newVal == false) {
				if (this.view.tblOrdersPaid.getSelectionModel().isEmpty() == false) {
					this.view.tblOrdersPaid.getSelectionModel().clearSelection();
				}
			}
		});

		this.view.btnUpdate.setOnAction(event -> {
			showProductList();
		});

		this.view.btnPay.setOnAction(event -> {
			try {
				this.model.pay();
				this.model.loadData(null);

				this.model.getProducOrdertSelected().setValue(null);

				this.view.btnPay.setDisable(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	private void showProductList() {
		try {
// TODO: Check that the cashier is open	
			ProductListModel prdListModel = ProductListModel.getInstance();
			prdListModel.loadData();

			FXMLLoader prdList = new FXMLLoader(
					getClass().getResource("/mg/ankoay/restomanagefinal/productlist/view/ProductList.fxml"));
			Parent root = prdList.load();
			ProductListCtl productListCtl = prdList.getController();

			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight() - 32);
			scene.getStylesheets().add(getClass()
					.getResource("/mg/ankoay/restomanagefinal/productlist/view/productlist.css").toExternalForm());

			ProductListPresenter productListPres = new ProductListPresenter(productListCtl, scene, this.sceneMenu);
			productListPres.setPrimaryStage(this.getPrimaryStage());

			this.getPrimaryStage().setScene(scene);

			prdListModel.setPrdOrder(this.model.getProducOrdertSelected().get());
			prdListModel.isUpdateProperty().set(true);

// FILL THE SELECTED PRODUCTS
			for (Table table : prdListModel.getTableList()) {
				if (Integer.valueOf(table.getId()) == Integer
						.valueOf(this.model.getProducOrdertSelected().get().getTable().getId())) {
					prdListModel.getTableSelected().set(table);
				}
			}

			for (Product prod : this.model.getProducOrdertSelected().get().getProducts()) {
				ProductListModel.getInstance().addProduct(prod);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void showSlctOrder(ProductOrder newVal, boolean enablBtn) {
		ProductOrder selectedValue = newVal;
		if (selectedValue != null) {
			this.model.getProducOrdertSelected().setValue(selectedValue);
			this.view.btnPay.setDisable(enablBtn);
			this.view.btnUpdate.setDisable(enablBtn);
		}

		if (newVal != null) {
			this.view.txtOrderDetailsDt.setText(selectedValue.getDate().toString());
			this.view.tblOrderDetails.setItems(selectedValue.getProducts());
			this.view.txtOrderDetailsTable.setText(selectedValue.getTable().getName());
			this.view.syncTxtOrderDetailsTotal();
		}
	}
}

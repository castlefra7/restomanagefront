package mg.ankoay.restomanagefinal.productlist.view;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Screen;
import javafx.util.Duration;
import mg.ankoay.restomanagefinal.commons.model.Category;
import mg.ankoay.restomanagefinal.commons.model.Product;
import mg.ankoay.restomanagefinal.commons.model.Table;
import mg.ankoay.restomanagefinal.commons.view.Presenter;
import mg.ankoay.restomanagefinal.productlist.model.ProductListModel;
import mg.ankoay.restomanagefinal.productorders.model.ProductOrder;
import mg.ankoay.restomanagefinal.productorders.model.ProductOrderModel;
import mg.ankoay.restomanagefinal.productorders.view.ProductOrderCtl;
import mg.ankoay.restomanagefinal.productorders.view.ProductOrderPresenter;

public class ProductListPresenter extends Presenter {
	private final ProductListModel model;
	private final ProductListCtl view;
	private Timeline wonder;
	private Scene sceneMenu;

	public ProductListPresenter(ProductListCtl _productListCtl, Scene _parent, Scene _sceneMenu) {
		this.model = ProductListModel.getInstance();
		this.view = _productListCtl;
		this.scene = _parent;
		this.sceneMenu = _sceneMenu;
		this.attachEvents();
	}

	public void attachEvents() {
		leftEvent();
		centerEvent();
	}

	public void leftEvent() {

		this.model.isUpdateProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				this.view.btnUpdateOrder.setVisible(true);
				this.view.btnPay.setVisible(false);
				this.view.btnOrder.setVisible(false);
				this.view.btnUpdateOrder.setOnAction(event -> {
					try {
						this.model.updateOrder();
						this.showProductOrder();
					} catch (Exception ex) {
						ex.printStackTrace();
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Erreur");
						alert.setHeaderText(ex.getMessage());
						alert.showAndWait();
					}

				});
			} else {
				this.view.btnUpdateOrder.setVisible(false);
				this.view.btnPay.setVisible(true);
				this.view.btnOrder.setVisible(true);
			}

		});

		this.view.totalPriceLbl.textProperty().bind(this.model.getTotalPrice().asString(Locale.FRENCH, "%,.2f MGA"));

		this.view.btnBack.setOnAction(event -> {
			this.getPrimaryStage().setScene(this.sceneMenu);
		});

		this.view.sldProdTbl.getSelectionModel().selectedItemProperty().addListener((obs, oldSelect, newSelect) -> {
			Product selectedValue = newSelect;
			this.model.getProductSelected().setValue(selectedValue);

			if (selectedValue != null) {
				this.view.qtyTxt.setText(String.valueOf(selectedValue.getQuantity()));
				this.view.prodNameLbl.setText(selectedValue.getName());
			} else {
				this.view.qtyTxt.setText("");
				this.view.prodNameLbl.setText("");
			}
		});

		this.view.incQtyBtn.setOnAction(e -> {
			Product selectedValue = this.model.getProductSelected().getValue();
			if (selectedValue != null) {
				selectedValue.setQuantity(selectedValue.getQuantity() + 1);
				this.view.qtyTxt.setText(String.valueOf(selectedValue.getQuantity()));
			}
		});

		this.view.decQtyBtn.setOnAction(e -> {
			Product selectedValue = this.model.getProductSelected().getValue();
			if (selectedValue != null) {
				if (selectedValue.getQuantity() == 1) {
					this.model.getProductSltList().remove(selectedValue);
				} else {
					selectedValue.setQuantity(selectedValue.getQuantity() - 1);
					this.view.qtyTxt.setText(String.valueOf(selectedValue.getQuantity()));
				}
			}
		});

		this.view.btnOrder.setOnAction(e -> {
			this.sendOrder(false);
		});

		this.view.btnPay.setOnAction(e -> {
			this.sendOrder(true);
		});

		this.view.cmbTables.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			Table selectedTable = newVal;
			this.model.getTableSelected().setValue(selectedTable);
		});

		this.view.btnAllOrders.setOnAction(event -> {
			showProductOrder();
		});
	}

	public void centerEvent() {
		EventHandler<ActionEvent> catHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Button source = (Button) event.getSource();
				Category categ = (Category) source.getUserData();
				model.filterProduct(categ.getId());
				event.consume();
			}
		};

		EventHandler<ActionEvent> prodHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Button source = (Button) event.getSource();
				Product prod = (Product) source.getUserData();
				model.addProduct(prod);
				event.consume();
			}
		};

		for (Category cat : this.model.getCategoryList()) {
			Button btn = new Button(cat.getName());
			btn.setPrefSize(250, 100);
			btn.setUserData(cat);
			btn.setOnAction(catHandler);
			this.view.categories.getChildren().add(btn);
		}
		fillProducts(prodHandler);

		this.model.getProductList().addListener(new ListChangeListener<Product>() {
			@Override
			public void onChanged(Change<? extends Product> arg0) {
				view.products.getChildren().clear();
				fillProducts(prodHandler);
			}
		});
	}

// LEFT PANE METHODS
	private void sendOrder(boolean paid) {
		try {
// Checking can open new scene
			if (this.model.getProductSltList().size() < 1 || this.model.getTableSelected().getValue() == null)
				return;
// Adding new items
			ProductOrder prdOrd = new ProductOrder();
			prdOrd.setTable(this.model.getTableSelected().getValue());
			prdOrd.setDate(new Timestamp((new Date()).getTime()));
			for (Product product : this.model.getProductSltList()) {
				prdOrd.getProducts().add(new Product(product.getId(), product.getName(), product.getPrice(),
						product.getIdCategory(), product.getQuantity()));
				product.reset();
			}
// TODO: Remove ProductOrderModel.getInstance().getProductOrders().add(prdOrd);
// Send to DB
			prdOrd.sendToDB(paid);
// Clean selected products
			this.model.getProductSltList().clear();
// Show status
			tempStatus();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void tempStatus() {
		view.status.setStyle("-fx-padding: 7px;");
		view.status.setText("Succès");
		wonder = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				view.status.setText("");
				view.status.setStyle("-fx-padding: 0px;");
				wonder.stop();
			}
		}));
		// wonder.setCycleCount(Timeline.INDEFINITE);
		wonder.play();
	}

	private void showProductOrder() {
// Open Scene		 
		try {
			ProductOrderModel.getInstance().loadData(null);
			FXMLLoader prdOrder = new FXMLLoader(
					getClass().getResource("/mg/ankoay/restomanagefinal/productorders/view/ProductOrder.fxml"));
			Parent root = prdOrder.load();
			ProductOrderCtl prdCtl = prdOrder.getController();

			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight() - 32);
			scene.getStylesheets().add(getClass()
					.getResource("/mg/ankoay/restomanagefinal/productorders/view/productorders.css").toExternalForm());

			ProductOrderPresenter prdOrdPres = new ProductOrderPresenter(prdCtl, scene, this.getScene(),
					this.sceneMenu);
			prdOrdPres.setPrimaryStage(this.getPrimaryStage());

			this.getPrimaryStage().setScene(scene);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

// CENTER PANE METHODS
	private void fillProducts(EventHandler<ActionEvent> event) {
		for (Product prd : this.model.getProductList()) {
			Button btn = new Button(prd.getName() + " " + prd.getPrice());
			btn.setPrefSize(250, 100);
			btn.setUserData(prd);
			btn.setOnAction(event);
			this.view.products.getChildren().add(btn);
		}
	}

}

// this.view.totalPriceLbl.textProperty().bind(Bindings.createStringBinding(
// () -> numbFormat.format(this.model.getTotalPrice().get()) + " MGA",
// this.model.getTotalPrice()));

package mg.ankoay.restomanagefinal.productlist.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import mg.ankoay.restomanagefinal.commons.model.Product;
import mg.ankoay.restomanagefinal.commons.model.Table;
import mg.ankoay.restomanagefinal.productlist.model.ProductListModel;

public class ProductListCtl implements Initializable {

// Left Pane	
	@FXML
	TableView<Product> sldProdTbl;
	@FXML
	VBox container;
	@FXML
	Button btnDecQty;
	@FXML
	Button btnIncQty;
	@FXML
	TextField txtQty;
	@FXML
	Label lblProdName;
	@FXML
	Label lblTotalPrice;
	@FXML
	Button btnOrder;
	@FXML
	Button btnPay;
	@FXML
	ComboBox<Table> cmbTables;
	@FXML
	Label status;
	@FXML
	Button btnAllOrders;
	@FXML
	Button btnUpdateOrder;
	@FXML
	TextField txtLaterPay;
	

// Center Pane	
	@FXML
	FlowPane categories;
	
	@FXML
	Button btnBack;
	@FXML
	Button btnExpense;

	@FXML
	FlowPane products;

	ProductListModel model;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.model = ProductListModel.getInstance();
		this.txtQty.setEditable(false);
		
		this.btnUpdateOrder.setVisible(false);
		
		this.cmbTables.setItems(this.model.getTableList());
		this.status.setText("");
		
		bindFieldsToModel();
		conf();
	}
	
	private void conf() {
		this.txtLaterPay.setPromptText("Spécifier si c'est une échéance");
	}

	private void bindFieldsToModel() {
		this.sldProdTbl.setItems(this.model.getProductSltList());
		
		this.model.laterPaymentProperty().bindBidirectional(this.txtLaterPay.textProperty());
		
		this.model.getTableSelected().addListener((obs, oldVal, newVal) -> {
			this.cmbTables.getSelectionModel().select(newVal);
		});
	}

}

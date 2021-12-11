package mg.ankoay.restomanagefinal.productlist.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
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
	Button decQtyBtn;
	@FXML
	Button incQtyBtn;
	@FXML
	TextField qtyTxt;
	@FXML
	Label prodNameLbl;
	@FXML
	Label totalPriceLbl;
	@FXML
	Button orderBtn;
	@FXML
	ComboBox<Table> cmbTables;

// Center Pane	
	@FXML
	FlowPane categories;

	@FXML
	FlowPane products;

	ProductListModel model;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.model = ProductListModel.getInstance();
		this.qtyTxt.setEditable(false);
		
		this.cmbTables.setItems(this.model.getTableList());
		
		bindFieldsToModel();
	}

	public void bindFieldsToModel() {
		this.sldProdTbl.setItems(this.model.getProductSltList());
	}

}

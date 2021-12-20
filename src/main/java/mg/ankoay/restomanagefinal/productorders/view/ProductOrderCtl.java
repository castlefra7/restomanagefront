package mg.ankoay.restomanagefinal.productorders.view;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mg.ankoay.restomanagefinal.commons.model.Product;
import mg.ankoay.restomanagefinal.productorders.model.ProductOrder;
import mg.ankoay.restomanagefinal.productorders.model.ProductOrderModel;

public class ProductOrderCtl implements Initializable {
	@FXML
	Button btnBack;
	@FXML
	TableView<ProductOrder> tblOrders;
	@FXML
	TableView<Product> tblOrderDetails;
	@FXML
	TextField txtOrderDetailsDt;
	@FXML
	TextField txtOrderDetailsTotal;
	@FXML
	TextField txtOrderDetailsTable;
	
	private ProductOrderModel model;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.model = ProductOrderModel.getInstance();
		bindFieldsToModel();
		conf();
	}
	
	public void syncTxtOrderDetailsTotal() {
		if(this.model.getProducOrdertSelected() != null) {
			this.txtOrderDetailsTotal.setText(NumberFormat.getInstance().format(this.model.getProducOrdertSelected().get().getTotal()) + " Ar");
		}
	}
	
	public void conf() {
		this.txtOrderDetailsTotal.setEditable(false);
		this.txtOrderDetailsDt.setEditable(false);
		this.txtOrderDetailsTable.setEditable(false);
	}
	
	public void bindFieldsToModel() {
		this.tblOrders.setItems(this.model.getProductOrders());
	}

}

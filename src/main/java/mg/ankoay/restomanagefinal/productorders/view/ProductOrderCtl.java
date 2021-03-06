package mg.ankoay.restomanagefinal.productorders.view;

import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mg.ankoay.restomanagefinal.commons.model.Product;
import mg.ankoay.restomanagefinal.productorders.model.Expense;
import mg.ankoay.restomanagefinal.productorders.model.ProductOrder;
import mg.ankoay.restomanagefinal.productorders.model.ProductOrderModel;

public class ProductOrderCtl implements Initializable {
	@FXML
	Button btnBack;
	@FXML
	TableView<ProductOrder> tblOrdersUnpaid;
	@FXML
	TableView<Product> tblOrderDetails;
	@FXML
	TextField txtOrderDetailsDt;
	@FXML
	TextField txtOrderDetailsTotal;
	@FXML
	TextField txtOrderDetailsTable;
	@FXML
	Button btnPay;
	@FXML
	Button btnUpdate;

	@FXML
	DatePicker dtpDate;
	@FXML
	Button btnPrev;
	@FXML
	Button btnNext;
	@FXML
	TableView<ProductOrder> tblOrdersPaid;

	@FXML
	TableView<Expense> tblExpenses;
	
	@FXML
	ComboBox<String> cmbTypesLate;

	private ProductOrderModel model;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.model = ProductOrderModel.getInstance();
		Locale locale = new Locale("fr", "FR");
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
		String currDt = dateFormat.format(new Date());
		bindFieldsToModel();
		conf();
	}

	public void syncTxtOrderDetailsTotal() {
		if (this.model.getProducOrdertSelected().get() != null) {
			this.txtOrderDetailsTotal.setText(
					NumberFormat.getInstance().format(this.model.getProducOrdertSelected().get().getTotal()) + " Ar");
		} else {
			this.txtOrderDetailsTotal.setText(NumberFormat.getInstance().format(0) + " Ar");
		}
	}

	public void conf() {
		this.txtOrderDetailsTotal.setEditable(false);
		this.txtOrderDetailsDt.setEditable(false);
		this.txtOrderDetailsTable.setEditable(false);

		this.btnUpdate.setDisable(true);
		this.btnPay.setDisable(true);

		this.dtpDate.setEditable(false);
		this.dtpDate.setValue(LocalDate.now());
		

		this.cmbTypesLate.getItems().add("Tous");
		this.cmbTypesLate.getItems().add("Avec ??ch??ance");
		this.cmbTypesLate.getItems().add("Pas d'??ch??ance");
		
		this.cmbTypesLate.getSelectionModel().select(0);
		
	}

	public void bindFieldsToModel() {
		
		
	
		this.tblOrdersUnpaid.setItems(this.model.getFilteredProductOrdersUnpaid());		
		this.tblOrdersPaid.setItems(this.model.getProductOrdersPaid());
		this.tblExpenses.setItems(this.model.getExpenses());
	}

}

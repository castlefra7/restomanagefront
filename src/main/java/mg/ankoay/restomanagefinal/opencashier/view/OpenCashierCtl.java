package mg.ankoay.restomanagefinal.opencashier.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mg.ankoay.restomanagefinal.opencashier.model.OpenCashierModel;

public class OpenCashierCtl implements Initializable {
	@FXML
	Button btnValidate;
	@FXML
	TextField txtFund;
	@FXML
	Button btnBack;
	
	OpenCashierModel model;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.model = OpenCashierModel.getInstance();		
		bindFieldsToModel();
	}
	
	private void bindFieldsToModel() {
		this.txtFund.textProperty().bindBidirectional(this.model.fundProperty());
	}
	
}

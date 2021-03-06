package mg.ankoay.restomanagefinal.opencashier.view;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import mg.ankoay.restomanagefinal.commons.utils.Utils;
import mg.ankoay.restomanagefinal.opencashier.model.OpenCashierModel;

public class OpenCashierCtl implements Initializable {
	@FXML
	Button btnValidate; 
	@FXML
	Label lblDt;
	@FXML
	TextField txtFund;
	@FXML
	Button btnBack;
	
	OpenCashierModel model;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.model = OpenCashierModel.getInstance();		
		bindFieldsToModel();
		conf();
	}
	
	private void conf() {
		String pattern = "EEEEE dd MMM yyyy";
		SimpleDateFormat sdt =
		        new SimpleDateFormat(pattern, new Locale("fr", "FR"));
		
		this.lblDt.setText(Utils.capitalize(sdt.format(new Date())));
	}
	
	private void bindFieldsToModel() {
		this.txtFund.textProperty().bindBidirectional(this.model.fundProperty(), new NumberStringConverter());
	}
	
}

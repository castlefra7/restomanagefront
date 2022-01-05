package mg.ankoay.restomanagefinal.closecashier.view;

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
import mg.ankoay.restomanagefinal.closecashier.model.CloseCashierModel;
import mg.ankoay.restomanagefinal.commons.utils.Utils;

public class CloseCashierCtl implements Initializable {
	@FXML
	Button btnValidate;
	@FXML
	Label lblDt;
	@FXML
	TextField txtRealAmnt;
	@FXML
	TextField txtSysAmnt;
	@FXML
	Button btnBack;

	CloseCashierModel model;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.model = CloseCashierModel.getInstance();
		bindFieldsToModel();
		conf();
	}

	private void conf() {
		this.txtSysAmnt.setEditable(false);
		this.txtSysAmnt.setDisable(true);
		
		String pattern = "EEEEE dd MMM yyyy";
		SimpleDateFormat sdt =
		        new SimpleDateFormat(pattern, new Locale("fr", "FR"));
		
		this.lblDt.setText(Utils.capitalize(sdt.format(new Date())));
	}

	private void bindFieldsToModel() {
		this.txtRealAmnt.textProperty().bindBidirectional(this.model.realAmntProperty(), new NumberStringConverter());
		this.txtSysAmnt.textProperty().bind(this.model.sysAmntProperty().asString(Locale.FRENCH, "%,.2f MGA"));
	}

}

package mg.ankoay.restomanagefinal.menu.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MenuCtl implements Initializable {
	@FXML
	Button btnJourneyBegin;
	@FXML
	Button btnJourneyEnd;
	@FXML
	Button btnOrders;
	@FXML
	Button btnPOS;
	
	@FXML
	Label lblUnpaidLatePay;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
}

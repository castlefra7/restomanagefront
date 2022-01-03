package mg.ankoay.restomanagefinal.topmenu.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class TopMenuCtl implements Initializable {
	@FXML
	private MenuItem mnQuit;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		attachEvents();
	}
	
	private void attachEvents() {
		this.mnQuit.setOnAction(event -> {
			Platform.exit();
			System.exit(0);
		});
	}

}

package mg.ankoay.restomanagefinal.login.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mg.ankoay.restomanagefinal.commons.model.User;

public class LoginCtl  implements Initializable {
	@FXML
	Button btnLogin;
	@FXML
	TextField txtUsername;
	@FXML
	PasswordField txtPassword;
	
	User model;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.model = User.getInstance();
		bindFieldsToModel();
	}
	
	private void bindFieldsToModel() {
		this.txtUsername.textProperty().bindBidirectional(this.model.nameProperty());
		this.txtPassword.textProperty().bindBidirectional(this.model.passwordProperty());
	}

}

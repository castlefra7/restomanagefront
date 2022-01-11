package mg.ankoay.restomanagefinal.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mg.ankoay.restomanagefinal.commons.utils.Utils;

public class GenericForm {

	// TODO: This only supports stringproperty
	public static GridPane getForm(GenericObject object, Stage stage) throws Exception {
		GridPane gridPane = new GridPane();
		
		Field[] fields = object.getClass().getDeclaredFields();
		
		Label lblTitle = new Label(object.title());
		lblTitle.setStyle("-fx-font-weight:900;-fx-font-size:24px;");
		gridPane.add(lblTitle, 0, 0, 2, 1);
		
		for(int iF = 0; iF < fields.length; iF++) {
			if(fields[iF].getType().getSimpleName().compareTo("StringProperty") == 0) {
				Label lbl = new Label(Utils.capitalize(fields[iF].getName()));
				TextField txt = new TextField();
				//System.out.println(fields[iF].get(object));
				txt.textProperty().bindBidirectional(getProperty(fields[iF], object));
				gridPane.add(lbl, 0, iF + 1);
				gridPane.add(txt, 1, iF + 1);
			}
		}
		
		Button btnSave = new Button("Enregistrer");
		
		gridPane.add(btnSave, 0, fields.length + 1);
		
		btnSave.setOnAction(event -> {
			try {
				object.save();
				stage.close();
			} catch(Exception ex) {
				ex.printStackTrace();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText(ex.getMessage());
				alert.showAndWait();
			}
			
		});
		
		gridPane.setPadding(new Insets(10,10,10,10));
		
		
		return gridPane;
	}
	
	private static StringProperty getProperty(Field field, GenericObject object) throws Exception {
		StringProperty result = null;
		
		Method[] methods= object.getClass().getMethods();
		
		String mtdName = field.getName() + "Property";
		for(int iM = 0; iM < methods.length; iM++) {
			if(methods[iM].getName().compareTo(mtdName) == 0) {
				result = (StringProperty)methods[iM].invoke(object, new Object[0]);
				break;
			}
		}
		
		return result;
	}
}

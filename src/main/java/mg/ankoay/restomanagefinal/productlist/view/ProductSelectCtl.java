package mg.ankoay.restomanagefinal.productlist.view;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import mg.ankoay.restomanagefinal.productlist.model.ProductListModel;
import mg.ankoay.restomanagefinal.productlist.model.Product;

public class ProductSelectCtl implements Initializable {
	@FXML
	private TableView<Product> sldProdTbl;
	@FXML
	private VBox container;
	@FXML
	private Button decQtyBtn;
	@FXML
	private Button incQtyBtn;
	@FXML
	private TextField qtyTxt;
	@FXML
	private Label prodNameLbl;
	@FXML
	private Label totalPriceLbl;

	private static NumberFormat numbFormat = NumberFormat.getInstance(new Locale("en", "US"));

	private ProductListModel model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.model = ProductListModel.getInstance();

		this.sldProdTbl.setItems(this.model.getProductSltList());

// Listeners
		this.totalPriceLbl.textProperty().bind(Bindings.createStringBinding(
				() -> numbFormat.format(this.model.getTotalPrice().get()) + " MGA", this.model.getTotalPrice()));
		this.sldProdTbl.getSelectionModel().selectedItemProperty().addListener((obs, oldSelect, newSelect) -> {
			if (newSelect != null) {
				removePrevBinding();
				model.getProductSelected().setValue(obs.getValue());
				modifProduct(obs);
			}
		});

	}

	@FXML
	public void decQty() {
		int currentQty = Integer.parseInt(qtyTxt.getText());
// TODO: Maybe remove when currentQty == 1 ?(cause its gonna be 0 if keeping decreasing it)		
		if (currentQty > 1) {
			currentQty--;
			qtyTxt.setText(String.valueOf(currentQty));
		}
	}

	@FXML
	public void incQty() {
		int currentQty = Integer.parseInt(qtyTxt.getText());
		currentQty++;
// TODO: There should be stock limit here
		qtyTxt.setText(String.valueOf(currentQty));

	}

	private void removePrevBinding() {
		if (this.model.getProductSelected().getValue() != null) {
			qtyTxt.textProperty().unbindBidirectional(this.model.getProductSelected().getValue().quantityProperty());
			prodNameLbl.textProperty().unbind();
		}
	}

	private void modifProduct(ObservableValue<? extends Product> prod) {
		try {
			qtyTxt.textProperty().bindBidirectional(prod.getValue().quantityProperty(), numbFormat);
			prodNameLbl.textProperty().bind(prod.getValue().nameProperty());

		} catch (Exception ex) {
			Logger.getLogger("productselectctl").log(Level.SEVERE, ex.getMessage());
		}

	}

}

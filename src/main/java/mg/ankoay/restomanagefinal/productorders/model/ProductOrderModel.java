package mg.ankoay.restomanagefinal.productorders.model;



import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductOrderModel {
	private ObservableList<ProductOrder> productOrders = FXCollections.observableArrayList();
	private ObjectProperty<ProductOrder> productOrderSelected = new SimpleObjectProperty<>();

	private static final ProductOrderModel INSTANCE = new ProductOrderModel();

	public static ProductOrderModel getInstance() {
		return INSTANCE;
	}

// Getters

	public ObjectProperty<ProductOrder> getProducOrdertSelected() {
		return this.productOrderSelected;
	}

	public ObservableList<ProductOrder> getProductOrders() {
		return this.productOrders;
	}
}

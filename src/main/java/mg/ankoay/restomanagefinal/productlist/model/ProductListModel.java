package mg.ankoay.restomanagefinal.productlist.model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class ProductListModel {
	private final ObservableList<Product> productList = FXCollections.observableArrayList(
			product -> new Observable[] { product.idProperty(), product.nameProperty(), product.priceProperty() });

	private final ObservableList<Category> categoryList = FXCollections
			.observableArrayList(category -> new Observable[] { category.idProperty(), category.nameProperty() });

	private final ObservableList<Product> productSltList = FXCollections.observableArrayList(
			product -> new Observable[] { product.idProperty(), product.nameProperty(), product.priceProperty() });

	private final List<Product> productAll = new ArrayList<>();

	private final ObjectProperty<Product> productSelected = new SimpleObjectProperty<>();
	private final SimpleDoubleProperty totalPrice = new SimpleDoubleProperty();

	private static final ProductListModel INSTANCE = new ProductListModel();

	public static ProductListModel getInstance() {
		return INSTANCE;
	}

	// Constructors
	public ProductListModel() {
// TODO: Get it from Database		
		productAll.add(new Product("1", "burger poulet", 10000, "1", 1));
		productAll.add(new Product("2", "panini", 15000, "1", 1));
		productAll.add(new Product("3", "glace simple", 15000, "2", 1));
		for (int i = 4; i <= 100; i++) {
			productAll.add(new Product(String.valueOf(i), "a", 12000, "1", 1));
		}
// Listeners
		ListChangeListener<Product> sltListProdListener = new ListChangeListener<Product>() {
			@Override
			public void onChanged(Change<? extends Product> prod) {
				totalPrice.setValue(totalPrice());
			}
		};

		ChangeListener<Number> qtyListener = new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				totalPrice.setValue(totalPrice());
			}
		};

		productSltList.addListener(sltListProdListener);
		productSelected.addListener(new ChangeListener<Product>() {

			@Override
			public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {
				if (productSelected.getValue() != null) {
					productSelected.getValue().quantityProperty().removeListener(qtyListener);
					productSelected.getValue().quantityProperty().addListener(qtyListener);
				}
			}
		});
	}

// Functions
	public double totalPrice() {
		double result = 0;
		for (Product prod : this.productSltList) {
			result += (prod.getQuantity() * prod.getPrice());
		}
		return result;
	}

	public void addProduct(Product prod) {
		if (this.productSltList.indexOf(prod) < 0)
			this.productSltList.add(prod);
	}

	public void filterProduct(String idCategory) {
		this.productList.clear();
		if (idCategory.contentEquals("0")) {
			this.productList.setAll(productAll);
		} else {
			for (Product prod : productAll) {
				if (prod.getIdCategory().contentEquals(idCategory)) {
					this.productList.add(prod);
				}
			}
		}
	}

	public void loadData() {
		productList.setAll(productAll);
		this.categoryList.setAll(new Category("0", "Tous"), new Category("1", "Burger"), new Category("2", "Glace"));

	}

// Getters
	public ObservableList<Product> getProductList() {
		return productList;
	}

	public ObservableList<Category> getCategoryList() {
		return categoryList;
	}

	public ObservableList<Product> getProductSltList() {
		return productSltList;
	}

	public ObjectProperty<Product> getProductSelected() {
		return productSelected;
	}

	public SimpleDoubleProperty getTotalPrice() {
		return totalPrice;
	}

}

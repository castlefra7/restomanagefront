package mg.ankoay.restomanagefinal.productlist.model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import mg.ankoay.restomanagefinal.commons.attributes.CategoryAttr;
import mg.ankoay.restomanagefinal.commons.attributes.ProductAttr;
import mg.ankoay.restomanagefinal.commons.attributes.TableAttr;
import mg.ankoay.restomanagefinal.commons.model.Category;
import mg.ankoay.restomanagefinal.commons.model.Product;
import mg.ankoay.restomanagefinal.commons.model.Table;
import mg.ankoay.restomanagefinal.commons.utils.ResponseBody;
import mg.ankoay.restomanagefinal.commons.utils.Utils;
import mg.ankoay.restomanagefinal.productorders.model.ProductOrder;

public class ProductListModel {
	private String URL = "http://localhost:8080/api/back";

	private final ObservableList<Product> productList = FXCollections.observableArrayList(
			product -> new Observable[] { product.idProperty(), product.nameProperty(), product.priceProperty() });
	private final ObservableList<Category> categoryList = FXCollections
			.observableArrayList(category -> new Observable[] { category.idProperty(), category.nameProperty() });

	private final ObservableList<Product> productSltList = FXCollections
			.observableArrayList(product -> new Observable[] { product.quantityProperty() });
	private final ObjectProperty<Product> productSelected = new SimpleObjectProperty<>();

	private final List<Product> productAll = new ArrayList<>();
	private final SimpleDoubleProperty totalPrice = new SimpleDoubleProperty();

	private ObservableList<Table> tableList = FXCollections.observableArrayList();
	private ObjectProperty<Table> tableSelected = new SimpleObjectProperty<>();

	private static final ProductListModel INSTANCE = new ProductListModel();

	private ProductOrder prdOrder;
	private final SimpleBooleanProperty isUpdate = new SimpleBooleanProperty();

	private final StringProperty laterPayment = new SimpleStringProperty();
	
	public ProductListModel() {
		attachListeners();
	}

	public void attachListeners() {
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

	public void updateOrder() throws Exception {
// FILL WITH NEW PRODUCTS
		prdOrder.getProducts().clear();
		for (Product prod : this.getProductSltList()) {
			prdOrder.getProducts().add(prod);
		}
// SET SELECTED TABLE	
		prdOrder.setTable(this.tableSelected.get());
		prdOrder.setLaterPayment(this.laterPaymentProperty().get());
// SEND THE UPDATE		
		prdOrder.update();
	}

	public double totalPrice() {
		double result = 0;
		for (Product prod : this.productSltList) {
			result += (prod.getQuantity() * prod.getPrice());
		}
		return result;
	}

	public void addProduct(Product prod) {
		boolean isThere = false;

		for (Product other : this.productSltList) {
			if (other.getId().equals(prod.getId())) {
				isThere = true;
				break;
			}
		}

		if (!isThere) {
			this.productSltList.add(prod);
		}

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

	private void emptyData() {
		this.categoryList.clear();
		this.productAll.clear();
		this.productList.clear();
		this.tableList.clear();
		this.productSelected.setValue(null);
		this.productSltList.clear();
		this.prdOrder = null;
		
		this.laterPayment.set(null);
		this.isUpdateProperty().set(false);
	}

	public void loadData() throws Exception {
	
		emptyData();
		Gson gson = new Gson();

		Type type = new TypeToken<ResponseBody<CategoryAttr>>() {
		}.getType();
		ResponseBody<CategoryAttr> respCat = gson.fromJson(Utils.getJSON(URL + "/product-categories"), type);
		List<CategoryAttr> categories = respCat.getData();
		for (CategoryAttr categ : categories) {
			Category trueCateg = new Category(String.valueOf(categ.getId()), categ.getName());
			this.categoryList.add(trueCateg);
		}

		Type typeProd = new TypeToken<ResponseBody<ProductAttr>>() {
		}.getType();
		ResponseBody<ProductAttr> respProd = gson.fromJson(Utils.getJSON(URL + "/products"), typeProd);
		List<ProductAttr> products = respProd.getData();
		for (ProductAttr prod : products) {
			Product trueProd = new Product(String.valueOf(prod.getId()), prod.getName(), prod.getPrice(),
					String.valueOf(prod.getCategory().getId()), 1);
			productAll.add(trueProd);
		}
		this.productList.setAll(productAll);

		Type typeTbl = new TypeToken<ResponseBody<TableAttr>>() {
		}.getType();
		ResponseBody<TableAttr> respTbl = gson.fromJson(Utils.getJSON(URL + "/tables"), typeTbl);
		List<TableAttr> tables = respTbl.getData();
		for (TableAttr tbl : tables) {
			Table trueTbl = new Table(String.valueOf(tbl.getId()), tbl.getName());
			this.tableList.add(trueTbl);
		}

	
	}

	public static ProductListModel getInstance() {
		return INSTANCE;
	}

// Getters
	public ObservableList<Product> getProductList() {
		return productList;
	}

	public SimpleBooleanProperty isUpdateProperty() {
		return this.isUpdate;
	}

	public ProductOrder getPrdOrder() {
		return prdOrder;
	}

	public void setPrdOrder(ProductOrder prdOrder) {
		this.prdOrder = prdOrder;
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

	public SimpleDoubleProperty totalPriceProperty() {
		return totalPrice;
	}

	public ObservableList<Table> getTableList() {
		return this.tableList;
	}

	public ObjectProperty<Table> getTableSelected() {
		return this.tableSelected;
	}
	
	public StringProperty laterPaymentProperty() {
		return this.laterPayment;
	}

}

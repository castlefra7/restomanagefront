package mg.ankoay.restomanagefinal.productlist.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import mg.ankoay.restomanagefinal.productlist.model.Category;
import mg.ankoay.restomanagefinal.productlist.model.ProductListModel;
import mg.ankoay.restomanagefinal.productlist.model.Product;

public class ProductListCtl implements Initializable {
	

	@FXML
	private FlowPane categories;
	
	@FXML
	private FlowPane products;
	
	private ProductListModel model;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.model = ProductListModel.getInstance();
		
// Event Handlers		
		EventHandler<ActionEvent> catHandler = new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	Button source = (Button)event.getSource();
		    	Category categ = (Category)source.getUserData();
		        model.filterProduct(categ.getId());
		        event.consume();
		    }
		};
		
		EventHandler<ActionEvent> prodHandler = new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	Button source = (Button)event.getSource(); 
		    	Product prod = (Product)source.getUserData();
		    	model.addProduct(prod);
		        event.consume();
		    }
		};
		
// Add Nodes		
		fillCategories(catHandler);
		fillProducts(prodHandler);
		
		this.model.getProductList().addListener(new ListChangeListener<Product>() {
			@Override
			public void onChanged(Change<? extends Product> arg0) {
				products.getChildren().clear();
				fillProducts(prodHandler);
			}
		});
		
	}

	private void fillCategories(EventHandler<ActionEvent> event) {
		for(Category cat: this.model.getCategoryList()) {
			Button btn = new Button(cat.getName());
			btn.setPrefSize(250, 100);
			btn.setUserData(cat);
			btn.setOnAction(event);
			categories.getChildren().add(btn);
		}
	}
	
	private void fillProducts(EventHandler<ActionEvent> event) {
		for(Product prd: this.model.getProductList()) {
			Button btn = new Button(prd.getName() + " " + prd.getPrice());
			btn.setPrefSize(250, 100);
			btn.setUserData(prd);
			btn.setOnAction(event);
			products.getChildren().add(btn);
		}
	}
	
}

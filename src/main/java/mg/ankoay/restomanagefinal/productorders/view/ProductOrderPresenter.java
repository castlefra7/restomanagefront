package mg.ankoay.restomanagefinal.productorders.view;

import javafx.scene.Scene;
import mg.ankoay.restomanagefinal.commons.view.Presenter;
import mg.ankoay.restomanagefinal.productorders.model.ProductOrder;
import mg.ankoay.restomanagefinal.productorders.model.ProductOrderModel;

public class ProductOrderPresenter extends Presenter {
	private ProductOrderCtl view;
	private ProductOrderModel model;
	private final Scene scenePrdList;
	
	public ProductOrderPresenter(ProductOrderCtl _view, Scene _scene, Scene _scenePrdList) {
		this.model = ProductOrderModel.getInstance();
		this.view = _view;
		this.scene = _scene;
		this.scenePrdList = _scenePrdList;
		this.attachEvents();
	}
	
	public void attachEvents() {
		this.view.btnBack.setOnAction(e -> {
			this.getPrimaryStage().setScene(this.scenePrdList);	
			this.getPrimaryStage().setFullScreen(true);
		});
		
		this.view.tblOrders.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			ProductOrder selectedValue = newVal;
			this.model.getProducOrdertSelected().setValue(selectedValue);
			
			if(newVal != null) {
				this.view.txtOrderDetailsDt.setText(selectedValue.getDate().toString());
				this.view.tblOrderDetails.setItems(selectedValue.getProducts());
				this.view.txtOrderDetailsTable.setText(selectedValue.getTable().getName());
				this.view.syncTxtOrderDetailsTotal();
			} else {
				this.view.txtOrderDetailsDt.setText("");

			}
		});
		
	}
}

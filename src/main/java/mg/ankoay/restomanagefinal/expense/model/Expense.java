package mg.ankoay.restomanagefinal.expense.model;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mg.ankoay.restomanagefinal.commons.utils.ResponseBody;
import mg.ankoay.restomanagefinal.commons.utils.Utils;
import mg.ankoay.restomanagefinal.productorders.attributes.ExpenseAttr;
import mg.ankoay.restomanagefinal.utils.GenericObject;

public class Expense implements GenericObject {
	private String URL = "http://localhost:8080/api/back";
	
	private StringProperty montant = new SimpleStringProperty(this, "montant", null);
	private StringProperty motif = new SimpleStringProperty(this, "motif", null);
	
	
	public StringProperty montantProperty() {
		return this.montant;
	}
	
	public StringProperty motifProperty() {
		return this.motif;
	}
	
	@Override
	public String title() {
		return "Dépense diverse";
	}

	@Override
	public void save() throws Exception {
		// TODO Auto-generated method stub
		
		Gson gson = new Gson();
		Type type = new TypeToken<ResponseBody<Object>>() {
		}.getType();
		
		ExpenseAttr attr = new ExpenseAttr();
		attr.setAmount(Double.valueOf(this.montant.get()));
		attr.setReason(this.motif.get());
		
		String entity = gson.toJson(attr);
		ResponseBody<Object> resp = gson.fromJson(Utils.postJSON(URL + "/expenses/", entity), type);
		if (resp.getStatut().getCode() != 200) {
			throw new Exception(resp.getStatut().getMessage());
		}
	}
}

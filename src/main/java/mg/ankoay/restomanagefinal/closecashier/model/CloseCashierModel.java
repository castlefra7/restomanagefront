package mg.ankoay.restomanagefinal.closecashier.model;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.beans.property.SimpleDoubleProperty;
import mg.ankoay.restomanagefinal.commons.attributes.StatSellingAttr;
import mg.ankoay.restomanagefinal.commons.model.User;
import mg.ankoay.restomanagefinal.commons.utils.ResponseBody;
import mg.ankoay.restomanagefinal.commons.utils.Utils;
import mg.ankoay.restomanagefinal.opencashier.attributes.OpenCashierAttr;

public class CloseCashierModel {
	private String URL = "http://localhost:8080/api/back";
	NumberFormat numbFormat = NumberFormat.getInstance(new Locale("en", "US"));

	private final SimpleDoubleProperty realAmnt = new SimpleDoubleProperty(this, "fund");
	private final SimpleDoubleProperty sysAmnt = new SimpleDoubleProperty(this, "sysAmnt");

	private static final CloseCashierModel INSTANCE = new CloseCashierModel();

// Functions
	public void loadData() throws Exception {
		Gson gson = new Gson();
		Type type = new TypeToken<ResponseBody<StatSellingAttr>>() {
		}.getType();
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
		ResponseBody<StatSellingAttr> respStat = gson.fromJson(Utils.getJSON(URL + "/stat/selling-sum?date=" + sdt.format(new Date())), type);
		if(respStat.getData().size() > 0) {
			this.setSysAmnt(respStat.getData().get(0).getSumSellingAmount());
		}
	}
	public boolean close() throws Exception {
		boolean result = true;
// TODO: Send a POST to database
		User connectedUser = User.getInstance();
		
		System.out.println("Montant réel: " + this.getRealAmnt());
		System.out.println("Montant système: " + this.getSysAmnt());

		Gson gson = new Gson();
		Type type = new TypeToken<ResponseBody<Object>>() {
		}.getType();

		OpenCashierAttr attr = new OpenCashierAttr();
		// attr.setFund(numbFormat.parse(this.getFund()).doubleValue());
		attr.setId_user(Integer.valueOf(connectedUser.getId()));

		String entity = gson.toJson(attr);
		// ResponseBody<Object> respOrd = gson.fromJson(Utils.postJSON(URL +
		// "/open-cashier/", entity), type);
		// System.out.println(respOrd.getStatut().getMessage());

		return result;
	}

	public static CloseCashierModel getInstance() {
		return INSTANCE;
	}

// Getters and setters
	public Double getSysAmnt() {
		return this.sysAmnt.get();
	}

	public void setSysAmnt(Double _val) {
		this.sysAmnt.set(_val);
	}

	public Double getRealAmnt() {
		return this.realAmnt.get();
	}

	public void setRealAmnt(Double _val) {
		this.realAmnt.set(_val);
	}

// Properties
	public SimpleDoubleProperty realAmntProperty() {
		return this.realAmnt;
	}

	public SimpleDoubleProperty sysAmntProperty() {
		return this.sysAmnt;
	}
}

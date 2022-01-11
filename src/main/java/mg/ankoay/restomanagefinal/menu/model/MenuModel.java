package mg.ankoay.restomanagefinal.menu.model;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import mg.ankoay.restomanagefinal.commons.utils.ResponseBody;
import mg.ankoay.restomanagefinal.commons.utils.Utils;

public class MenuModel {
	private String URL = "http://localhost:8080/api/back";

	private static final MenuModel INSTANCE = new MenuModel();

	public static MenuModel getInstance() {
		return INSTANCE;
	}

	public Integer getCountLatePay() throws Exception {
		Integer result = 0;

		Gson gson = new Gson();
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
		Type type = new TypeToken<ResponseBody<Integer>>() {
		}.getType();

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);

		ResponseBody<Integer> resp = gson
				.fromJson(Utils.getJSON(URL + "/orders/late-pay/count?date=" + sdt.format(cal.getTime())), type);
		if (resp.getStatut().getCode() != 200) {
			throw new Exception(resp.getStatut().getMessage());
		} else {
			result = resp.getData().get(0);
		}
		
		System.out.println(resp.getData().get(0));

		return result;
	}
}

package mg.ankoay.restomanagefinal.test;

import java.text.NumberFormat;
import java.util.Locale;

public class RestoTest {
	public static void main(String[] args) throws Exception {
		NumberFormat numbFormat = NumberFormat.getInstance(new Locale("en", "US"));
		System.out.println(numbFormat.format(150000));
	}
}

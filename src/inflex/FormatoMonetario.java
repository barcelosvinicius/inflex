package inflex;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FormatoMonetario {
	//Tratando o formato monetário total
	public static String formatarSalario(BigDecimal salarioTotal) {
		DecimalFormat formatoSalario = new DecimalFormat("#,##0.00");
		return formatoSalario.format(salarioTotal);
	}
}

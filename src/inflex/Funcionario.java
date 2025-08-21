package inflex;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa{
	private BigDecimal salario;
	private String funcao;
	
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	//Tratando o formato monetário
	public String formatarSalario() {
		DecimalFormat formatoSalario = new DecimalFormat("#,##0.00");
		return formatoSalario.format(this.getSalario());
	}
	
	//Tratando o formato monetário total
		public static String formatarSalario(BigDecimal salarioTotal) {
			DecimalFormat formatoSalario = new DecimalFormat("#,##0.00");
			return formatoSalario.format(salarioTotal);
	}
	
	//Tratando o formato da data
	public String formatarDN() {
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formatoData.format(getDataNascimento());
	}
	
	//Impressão da lista de funcionários
	public void listarFuncionario() {       
		System.out.println("Nome: " + this.getNome() +
    			", Data de Nascimento: " + this.formatarDN() +
    			", Função: " + this.getFuncao() + 
    			", Salário: " + this.formatarSalario());
	}
}


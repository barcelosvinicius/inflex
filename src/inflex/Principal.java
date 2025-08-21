package inflex;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
	public static void main(String[] args) {
        
		//Criando estrutura de funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        
        //Cadastrando funcionários
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 05, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 05, 02), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 01, 05), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 03, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 07, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 05, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 02), new BigDecimal("2799.93"), "Gerente"));
                
        //Removendo funcionário: João
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));
        
        //Imprimindo lista de funcionários
        System.out.println("Funcionários cadastrados:");
        funcionarios.forEach(Funcionario::listarFuncionario);
        
        //Ajustando salário: aumento de 10%
        for(Funcionario f: funcionarios) {
        	BigDecimal ajusteSalario = f.getSalario().multiply(new BigDecimal("1.10"));
        	f.setSalario(ajusteSalario);
        }
        
        //Agrupando funcionários com MAP
        Map<String, List<Funcionario>> listagemPorFuncao = 
        		funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        //Imprimindo funcionarios por função
        listagemPorFuncao.forEach((funcao,lista) -> {
        	System.out.println("\nFunção: " + funcao);
        	lista.forEach(f -> System.out.println("- " + f.getNome() +
        			", Salário: " + f.formatarSalario() + 
        			", Data de Nascimento: " + f.formatarDN()));
        });
        
        //Imprimindo aniversariantes de Outubro e Dezembro
        System.out.println("\nAniversariantes de Outubro e Dezembro:");
        funcionarios.stream().filter(f -> f.getDataNascimento().getMonthValue() == 10 ||
        		f.getDataNascimento().getMonthValue() == 12).forEach(f -> 
        		System.out.println(f.getNome() + " - " + f.formatarDN()));
        
        //Funcionario com maior idade
        Funcionario maiorIdade = Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        int idade = LocalDate.now().getYear() - maiorIdade.getDataNascimento().getYear();
        System.out.println("\nO funcionário mais velho é: " + maiorIdade.getNome() + " com " + idade +" anos.");
        
        //Funcionarios por ordem alfabética
        System.out.println("\nFuncionários por ordem alfabética: ");
        funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome))
        .forEach(f -> System.out.println(f.getNome()));
        
        //Total de salários por funcionário
        BigDecimal totalSalario = funcionarios.stream().map(Funcionario::getSalario)
        		.reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nO valor total dos salários é de: " + Funcionario.formatarSalario(totalSalario));
        
        //Quantidade de salários mínimos por funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nQuantidade de salários em relação ao minímo de R$1.212,00: ");
        for(Funcionario f: funcionarios) {
        	BigDecimal qtdSalarios = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
        	System.out.println(f.getNome() + " recebe " + qtdSalarios + " salários mínimos.");
        }
	}
}

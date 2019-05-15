package com.williansmartins.manutencaoveiculo.teste;

public class MinhaClasse{

	public static void main(String[] args){
		String nome1 = " Willians Martins de Morais";
		String nome2 = "Edinailton Machado Arruda";
		String nome3 = " Franklyn Chagas de Aguiar";
//		
//		nome1 = nome1.trim();
//		nome2 = nome2.trim();
//		nome3 = nome3.trim();
//		
//		System.out.println(nome1.substring(0, nome1.indexOf(' ')));
//		System.out.println(nome2.substring(0, nome2.indexOf(' ')));
//		System.out.println(nome3.substring(0, nome3.indexOf(' ')));
		
		int[] idades = {1,2,3,4,5};
		String[] nomes = {"1-Willians Martins de Morais-M-30", "2-Edinailton Machado Arruda-M-31", "3-Franklyn Chagas de Aguiar-M-32", "4-Maria-F-33"}; 
		
		for (int i = 0; i < nomes.length; i++) {
			int posicao = i;
			String nome = nomes[i].substring(2);
			int indexSexo = nome.lastIndexOf('-');
			int indexIdade = nome.lastIndexOf('-');
			
			System.out.print(" posicao: " + posicao );
			System.out.print(" nome: " + nome );
			System.out.print(" sexo: " + nome.substring(indexSexo-1, 1));
			System.out.print(" idade: " + nome.substring(indexIdade+1));
			
			System.out.println("-------");
		}
		
	}
}
package com.williansmartins.manutencaoveiculo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.williansmartins.manutencaoveiculo.dao.ManutencaoDAO;
import com.williansmartins.manutencaoveiculo.model.Categoria;
import com.williansmartins.manutencaoveiculo.model.Manutencao;

@Controller
@RequestMapping("/manutencoes")
public class ManutencaoController {
	
	ManutencaoDAO dao = new ManutencaoDAO();

	@RequestMapping(value="", method=RequestMethod.POST)  
	@ResponseBody
	public Manutencao inserir(@RequestBody Manutencao entrada) {
		int id_veiculo = entrada.getId_veiculo();
		int id_usuario = entrada.getId_usuario();
		Date data = entrada.getData();
		Categoria categoria = entrada.getCategoria();
		int kilometragem = entrada.getKilometragem();
		String observacoes = entrada.getObservacoes();
		
		int id = dao.inserir(id_veiculo, id_usuario, data, categoria, kilometragem, observacoes);
		entrada.setId(id);
		return entrada;
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)  
	@ResponseBody
	public List<Manutencao> buscarTudo() {
		return dao.buscarTudo();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)  
	@ResponseBody
	public Manutencao buscarPorId(@PathVariable int id) {
		Manutencao carro = dao.buscarPorId(id);
		return carro;
	}

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public int atualizar(@RequestBody Manutencao carro, @PathVariable String id) {
		carro.setId(Integer.parseInt(id));
		int resultado = dao.atualizar(carro);
		return resultado; 
	}

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public int deletar(@PathVariable String id) {
		System.out.println("Deletando o objeto com id:" + id);
		int deuCerto = dao.excluir(Integer.valueOf(id));
		return deuCerto;
	}
	
	@RequestMapping(value="categorias", method=RequestMethod.GET)  
	@ResponseBody
	public Categoria[] buscarCategorias() {
		return Categoria.values();
		
	}
}

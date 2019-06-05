package com.williansmartins.manutencaoveiculo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ManutencoesController {

	ManutencaoDAO dao = new ManutencaoDAO();
		
	@RequestMapping(value="", method=RequestMethod.POST)  
	@ResponseBody
	public Manutencao inserir(@RequestBody Manutencao entrada) {
		int id = dao.inserir(entrada.getId_veiculo(), entrada.getId_usuario(), entrada.getData(), entrada.getCategoria(), entrada.getKilometragem(), entrada.getObservacoes());
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
	public Manutencao buscar(@PathVariable int id) {
		return dao.buscarPorId(id);
		
	}

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public int atualizar(@RequestBody Manutencao manutencao, @PathVariable String id) {
		manutencao.setId(Integer.parseInt(id));
		int resultado = dao.atualizar(manutencao);
		return resultado; 
	}

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public int deletar(@PathVariable String id) {
		System.out.println("Deletando o objeto com id:" + id);
		int deuCerto = dao.excluir(Integer.valueOf(id));
		return deuCerto;
	}
	
}

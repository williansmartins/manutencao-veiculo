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
	public Manutencao buscar() {
		Manutencao objeto = new Manutencao();
		objeto.setCategoria(Categoria.COMBUSTIVEL);
		objeto.setData(new Date());
		objeto.setId(1);
		objeto.setId_usuario(2);
		objeto.setId_veiculo(3);
		objeto.setKilometragem(12345);
		return objeto;
	}

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Manutencao> atualizar(@RequestBody Manutencao Manutencao, @PathVariable String id) {
		Manutencao.setObservacoes(Manutencao.getObservacoes() + " - nova");
		return new ResponseEntity<Manutencao>(Manutencao, HttpStatus.OK); 
	}

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Manutencao> deletar(@PathVariable String id) {
		System.out.println("Deletando o objeto com id:" + id);
		return new ResponseEntity<Manutencao>(new Manutencao(), HttpStatus.OK) ;
	}
	
}

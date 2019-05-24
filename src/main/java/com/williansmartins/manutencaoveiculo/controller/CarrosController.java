package com.williansmartins.manutencaoveiculo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.williansmartins.manutencaoveiculo.dao.CarroDAO;
import com.williansmartins.manutencaoveiculo.model.Carro;

@Controller
@RequestMapping("/carros")
public class CarrosController {
	
	CarroDAO dao = new CarroDAO();
	
	@RequestMapping(value="", method=RequestMethod.GET) 
	@ResponseBody
	public List<Carro> listagem() {
		List<Carro> carros = dao.listagem();
		return carros ;
	}

	@RequestMapping(value="", method=RequestMethod.POST)  
	@ResponseBody
	public int inserir(@RequestBody Carro entrada) {
		String fabricante = entrada.getFabricante();
		String modelo = entrada.getModelo();
		String ano = "";
		
		return dao.inserir(fabricante, modelo, ano);
		
		
	}
	
//	@RequestMapping(value="", method=RequestMethod.GET)  
//	@ResponseBody
//	public ResponseEntity<List<Carro>> buscarTudo() {
//		List<Carro> listagem = dao.listagem();
//		return new ResponseEntity<List<Carro>>(listagem, HttpStatus.OK) ;
//	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)  
	@ResponseBody
	public Carro buscarPorId(@PathVariable int id) {
		Carro carro = dao.buscarPorId(id);
		return carro;
	}

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Carro> atualizar(@RequestBody Carro Carro, @PathVariable String id) {
		Carro.setObservacoes(Carro.getObservacoes() + " - nova");
		return new ResponseEntity<Carro>(Carro, HttpStatus.OK); 
	}

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Carro> deletar(@PathVariable String id) {
		System.out.println("Deletando o objeto com id:" + id);
		//dao???
		return new ResponseEntity<Carro>(new Carro(), HttpStatus.OK) ;
	}
	
}

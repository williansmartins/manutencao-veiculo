package com.williansmartins.manutencaoveiculo.controller;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
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
		List<Carro> carros = dao.buscarTudo();
		return carros ;
	}

	@RequestMapping(value="", method=RequestMethod.POST)  
	@ResponseBody
	public Carro inserir(@RequestBody Carro entrada) {
		String fabricante = entrada.getFabricante();
		String modelo = entrada.getModelo();
		String ano = entrada.getAno();
		
		int id = dao.inserir(fabricante, modelo, ano);
		entrada.setId(id);
		return entrada;
	}
	
	@Cacheable(value = { "addresses" })
	@RequestMapping(value="/{id}", method=RequestMethod.GET)  
	@ResponseBody
	public Carro buscarPorId(@PathVariable int id) {
		Carro carro = dao.buscarPorId(id);
		return carro;
	}

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public int atualizar(@RequestBody Carro carro, @PathVariable String id) {
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
	
}

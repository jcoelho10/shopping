package com.santana.java.back.end.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santana.java.back.end.dto.UserDTO;
import com.santana.java.back.end.service.UserService;

@RestController
public class UserController {

	
	@Autowired
	private UserService userService;
	
	public static List<UserDTO> usuarios = new ArrayList<UserDTO>();
	
	@GetMapping("/")
	public String getMensagem() {
		return "Spring boot is working!";
	}
	
	@GetMapping("/user/")
	public List<UserDTO> getUsers() {
		List<UserDTO> usuarios = userService.getAll();
		
		return usuarios;
	}
	
	@GetMapping("/user/{id}")
	public UserDTO findById(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@PostMapping("/user")
	public UserDTO newUser(@RequestBody UserDTO userDTO) {
		return userService.save(userDTO);
	}
	
	@GetMapping("/user/cpf/{cpf}")
	public UserDTO findByCpf(@PathVariable String cpf) {
		return userService.findByCpf(cpf);
	}
	
	@DeleteMapping("/user/{id}")
	public UserDTO delete(@PathVariable Long id) {
		return userService.delete(id);
	}
	
	@GetMapping("/user/search")
	public List<UserDTO> queryByName(@RequestParam(name = "nome", required = true) String nome) {
		return userService.queryByName(nome);		
	}
	
	@PostConstruct
	public void initiateList() {
		UserDTO userDTO = new UserDTO();
		userDTO.setNome("Eduardo");
		userDTO.setCpf("123");
		userDTO.setEndereco("Rua a");
		userDTO.setEmail("eduardo@email.com");
		userDTO.setTelefone("1234-3454");
		userDTO.setDataCadastro(new Date());
		
		UserDTO userDTO2 = new UserDTO();
		userDTO2.setNome("Luiz");
		userDTO2.setCpf("456");
		userDTO2.setEndereco("Rua b");
		userDTO2.setEmail("luiz@email.com");
		userDTO2.setTelefone("1234-3454");
		userDTO2.setDataCadastro(new Date());
		
		UserDTO userDTO3 = new UserDTO();
		userDTO3.setNome("Bruna");
		userDTO3.setCpf("678");
		userDTO3.setEndereco("Rua c");
		userDTO3.setEmail("bruna@email.com");
		userDTO3.setTelefone("1234-3454");
		userDTO3.setDataCadastro(new Date());
		
		usuarios.add(userDTO);
		usuarios.add(userDTO2);
		usuarios.add(userDTO3);
	}
	
}

package com.santana.java.back.end.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santana.java.back.end.dto.UserDTO;
import com.santana.java.back.end.model.User;
import com.santana.java.back.end.repository.UserRepository;

@Service
public class UserService {

	//@Autowired - Essa anotação serve para fazer injeção de dependências
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Chama o método findAll, do UserRepository, que retorna uma lista de usuários,
	 * sendo instâncias da entidade User.
		Transforma a lista em um stream e chama o método map para transformar a lista
		de entidades em uma lista de DTOs.
		Retorna a lista de DTOs.
	**/
	
	public List<UserDTO> getAll() {
		List<User> usuarios = userRepository.findAll();
		
		return usuarios.stream().map(UserDTO::convert).collect(Collectors.toList());
	}
	
	//busca um usuário por um id específico
	public UserDTO findById(long userId) {
		Optional<User> usuario = userRepository.findById(userId);
		
		if (usuario.isPresent()) {
			return UserDTO.convert(usuario.get());
		}
		
		return null;
	}
	
	//salva um usuário no banco de dados
	public UserDTO save(UserDTO userDTO) {
		User user = userRepository.save(User.convert(userDTO));
		
		return UserDTO.convert(user);
	}
	
	//exclui um usuário do banco de dados
	public UserDTO delete(long userId) {
		Optional<User> user = userRepository.findById(userId);
		
		if (user.isPresent()) {
			userRepository.delete(user.get());
		}
		
		return null;
	}
	
	//faz a busca de um usuário or seu CPF
	public UserDTO findByCpf(String cpf) {
		User user = userRepository.findByCpf(cpf);
		
		if (user != null) {
			return UserDTO.convert(user);
		}
		
		return null;
	}
	
	//fará uma busca pelo nome do usuário, 
	//mas diferentemente da busca por id ou pelo CPF, a busca não será exata, mas
	//sim pela inicial do nome passada no parâmetro. Por exemplo, se o parâmetro
	//nome tiver valor Mar%, a busca retornará pessoas com o nome Marcela,
	//Marcelo ou Marcos.
	public List<UserDTO> queryByName(String name) {
		List<User> usuarios = userRepository.queryByNomeLike(name);
		
		return usuarios.stream().map(UserDTO::convert).collect(Collectors.toList());
	}
	 
}

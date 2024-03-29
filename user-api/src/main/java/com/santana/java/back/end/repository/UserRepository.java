package com.santana.java.back.end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santana.java.back.end.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByCpf(String cpf);
	
	List<User> queryByNomeLike(String name);
}

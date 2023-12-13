package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailAndPassword(String Email, String password);

	@SuppressWarnings("unchecked")
	User save(User user);

	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);

	User findById(int id);

	User deleteById(int id);

	Object findUserByEmail(String email);

}

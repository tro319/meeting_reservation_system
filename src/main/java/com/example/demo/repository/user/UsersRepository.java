package com.example.demo.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.User;

/*
 * ユーザーテーブルデータ格納用リポジトリ
 * 
 * @author ys
 * 
 */

@Repository

public interface UsersRepository extends JpaRepository<User, Integer> {


	Optional<User> findByEmail(String email);
	
	Boolean existsByEmail(String email);
	
	Boolean existsByName(String name);
	
}

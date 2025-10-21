package com.example.demo.dao.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.users.UserInfo;

/**
 * ユーザー情報テーブル レコード保存庫 DAO
 * 
 * @author ys
 * 
 */

@Repository
public interface UsersRepository extends JpaRepository<UserInfo, Integer> {
	
	/**
	 * emailカラムからDBを検索するメソッド定義
	 * 
	 * @param email 入力されたメールアドレス
	 * @return ユーザー情報エンティティ群
	 */
	
	Optional<UserInfo> findByEmail(String email);

}

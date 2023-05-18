package com.pky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pky.entities.UserAccountEntity;

@Repository
public interface UserAccountsRepository extends JpaRepository<UserAccountEntity,Integer> {
    
	public UserAccountEntity findByUserEmail(String userEmail);
	
	public UserAccountEntity  findByUserPwdAndUserEmail(String userPwd,String userEmail);
}

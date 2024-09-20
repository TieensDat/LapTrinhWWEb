package com.example.dao;

import java.util.List;

import com.example.models.UserModel;

public interface IUserDao {
	List<UserModel> findAll();
	
	UserModel findById(int id);
	
	void insert(UserModel user);
	
	void insertRegister(UserModel user);
	
	UserModel findByUserName(String username);
	
	UserModel resetPassword(String username,String password);
	
}

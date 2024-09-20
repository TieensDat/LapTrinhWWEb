package com.example.services;

import com.example.models.UserModel;

public interface IUserService {
	UserModel findByUserName(String username);
	UserModel login(String username,String password);
	UserModel register(String username,String email,String password,String fullname,String phone);
	void forgotPassword(String username,String email);
	void resetPassword(String username,String password);
}

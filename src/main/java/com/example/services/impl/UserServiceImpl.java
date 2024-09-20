package com.example.services.impl;

import com.example.dao.IUserDao;
import com.example.dao.impl.UserDaoImpl;
import com.example.models.UserModel;
import com.example.services.IUserService;

public class UserServiceImpl implements IUserService {
	// Lấy toàn bộ hàm trong tầng dao của user 
	IUserDao userdao = new UserDaoImpl();

	

	@Override
	public UserModel login(String username, String password) {
		// TODO Auto-generated method stub
		UserModel user = this.findByUserName(username);
		if(user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}
	
	@Override
	public UserModel findByUserName(String username) {
		// TODO Auto-generated method stub
		return userdao.findByUserName(username);	
		}
	@Override
	public UserModel register(String username, String email, String password, String fullname, String phone) {
		// TODO Auto-generated method stub
//		UserModel user = this.findByUserName(username);
		if(this.findByUserName(username) == null) {
			int roleid = 2;
			UserModel newuser = new UserModel(username,email,password,fullname,phone,roleid);
			userdao.insertRegister(newuser);
			return this.findByUserName(username);
		}
		
		return null;
	}
	public static void main(String[] args) {
		try {
			UserServiceImpl userservice = new UserServiceImpl();
			System.out.print(userservice.register("us5", "mail@3", "123", "fname3", "123"));

		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void forgotPassword(String username, String email) {
		// TODO Auto-generated method stub
		UserModel user = userdao.findByUserName(username);
		if(user == null) {
			
		}
		
	}

	@Override
	public void resetPassword(String username, String password) {
		// TODO Auto-generated method stub
		userdao.resetPassword(username,password);
	} 
}

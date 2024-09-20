package com.example.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.configs.DBConnectMySQL;
import com.example.dao.IUserDao;
import com.example.models.UserModel;

public class UserDaoImpl extends DBConnectMySQL implements IUserDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
//	IUserDao userDao = new UserDaoImpl();


	@Override
	public List<UserModel> findAll() {
		String sql = "Select * from user";

		List<UserModel> list = new ArrayList<>();

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getString("password"), rs.getString("fullname"), rs.getString("images"),
						rs.getString("phone"), rs.getInt("roleid"), rs.getDate("createDate")));
			}

			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public UserModel findById(int id) {

		// TODO Auto-generated method stub
		String sql = "SELECT * FROM user WHERE id = ? ";
		try {
			conn = new DBConnectMySQL().getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	};
	


	@Override
	public void insertRegister(UserModel user) {
		// TODO Auto-generated method stub
		String sql = "Insert into user(username,email,password,fullname,phone,roleid) values (?,?,?,?,?,?)" ;
		try {
			conn = super.getDatabaseConnection();
			
			ps = conn.prepareStatement(sql);
			
			
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getFullname());
			ps.setString(5, user.getPhone());
			ps.setInt(6, user.getRoleid());
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO user(id,username,email,password,images,fullname) VALUES (?,?,?,?,?,?)";
		try {
			conn = super.getDatabaseConnection();

			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getImages());
			ps.setString(6, user.getFullname());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Override
	public UserModel findByUserName(String username) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM user WHERE username = ? ";
		try {
			conn = new DBConnectMySQL().getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
//		UserModel newmodel = new UserModel(2,"khang","khang@123","khanglor","vudinhkhang","");
		try {
			UserDaoImpl userdao = new UserDaoImpl();
//			System.out.print(userDao.findByUserName("huy"));
			UserModel newmodel = new UserModel("us2","@123","psw2","fname2","123","002",2);
			userdao.insertRegister(newmodel);
			List<UserModel> list = userdao.findAll();
			for(UserModel user : list) {
				System.out.print(user + "\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		
//		userDao.insert(newmodel);
//		List<UserModel> list = userDao.findAll();
//		for(UserModel user : list) {
//			System.out.print(user + "\n");
//		}
		
	}

	@Override
	public UserModel resetPassword(String username, String password) {
		// TODO Auto-generated method stub
		String sql = "update user set password = ? where username = ?";
		try {
			conn = new DBConnectMySQL().getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, username);
			int row = ps.executeUpdate();
			if(row > 0) {
				UserModel user = this.findByUserName(username);
				return user;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}


	
}

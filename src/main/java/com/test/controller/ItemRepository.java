package com.test.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.test.domain.Item;

@Component
public class ItemRepository {
	
	public ItemRepository(){
		
	}
	public void save(Item item){

		Connection conn = getConn();
		try {
			Statement st = conn.createStatement();
			String insertSQL = "insert into products(p_name, p_desc, phoneNumber, email)"
					+ "values('" + item.getProName() + "','" + item.getProDesc() + "','"
					+ item.getPhoneNum() + "','" + item.getEmail() +"')";
			st.executeUpdate(insertSQL);
			st.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("insert problems");
			e.printStackTrace();
		}

	}
	public void saveChange(Item item){
		int p_id = item.getId();
		Connection conn = getConn();
		try {
			Statement st = conn.createStatement();
			String changeSQL = "update products" + " set"  +
					" p_name = '" + item.getProName() + "'," +
					" p_desc = '" + item.getProDesc() + "'," +
					" phoneNumber = '" + item.getPhoneNum() + "'," +
					" email = '" + item.getEmail() +
					"' where p_id = " + p_id;
			st.executeUpdate(changeSQL);
			st.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("change problems");
			e.printStackTrace();
		}
	}
	
	public HashMap<String, Item> getItemRep(){
		HashMap<String,Item> items = new HashMap<String,Item>();
		Connection conn = getConn();
		try {
			Statement st = conn.createStatement();
			String querySQL = "select * from products";
			ResultSet rs = st.executeQuery(querySQL);
			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getInt(1));
				item.setProName(rs.getString(2));
				item.setPhoneNum(rs.getString(3));
				item.setEmail(rs.getString(4));
				if(rs.getInt(5) == 1)
					item.setDeleted(true);
				item.setProDesc(rs.getString(6));
				items.put(String.valueOf(item.getId()) , item);
			}
			st.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("return repo problems");
			e.printStackTrace();
		}
		return items;
	}
	
	public Item getItem(int id){
		Connection conn = getConn();
		Item item = new Item();
		try {
			Statement st = conn.createStatement();
			String querySQL = "select * from products where p_id = " + id;
			ResultSet rs = st.executeQuery(querySQL);
			while (rs.next()) {
				item.setId(rs.getInt(1));
				item.setProName(rs.getString(2));
				item.setPhoneNum(rs.getString(3));
				item.setEmail(rs.getString(4));
				if(rs.getInt(5) == 1)
					item.setDeleted(true);
				item.setProDesc(rs.getString(6));
			}
			st.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("return item problems");
			e.printStackTrace();
		}
		return item;
	}
	
	public void delete(int id){
		Connection conn = getConn();
		try {
			Statement st = conn.createStatement();
			String deleteSQL = "update products set isDeleted = 1 where p_id = " + id;
			st.executeUpdate(deleteSQL);
			st.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("delete problems");
			e.printStackTrace();
		}
	}
	
	public Connection getConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("can't find the driver of mysql");
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/product_schema";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "root", "123456");
		} catch (SQLException e) {
			System.out.println("fail to connect");
			e.printStackTrace();
		}
		return conn;
	}
}

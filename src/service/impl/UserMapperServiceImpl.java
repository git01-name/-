package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dao.UserMapper;
import entity.Bill;
import entity.Provider;
import entity.User;
import service.UserMapperService;
@Service("userMapperService")
public class UserMapperServiceImpl implements UserMapperService{
	@Autowired
	@Qualifier("userMapper")
	private UserMapper userMapper;
	@Override
	public List<User> query() {
		// TODO Auto-generated method stub
		return userMapper.query();
	}
	@Override
	public int login(String userName, String userPassword) {
		// TODO Auto-generated method stub
		return userMapper.login(userName, userPassword);
	}
	@Override
	public List<Bill> billList(String productName, String isPayment) {
		// TODO Auto-generated method stub
		return userMapper.billList(productName, isPayment);
	}
	@Override
	public Bill billAddName(String billCode) {
		// TODO Auto-generated method stub
		return userMapper.billAddName(billCode);
	}
	@Override
	public Bill view(String id) {
		// TODO Auto-generated method stub
		return userMapper.view(id);
	}
	@Override
	public int billDel(String id) {
		// TODO Auto-generated method stub
		return userMapper.billDel(id);
	}
	@Override
	public int billadd(Bill bill) {
		// TODO Auto-generated method stub
		return userMapper.billadd(bill);
	}
	@Override
	public List<Provider> providerList() {
		// TODO Auto-generated method stub
		return userMapper.providerList();
	}
	@Override
	public int uodate1(Bill bill) {
		// TODO Auto-generated method stub
		return userMapper.uodate1(bill);
	}
	
	
	

}

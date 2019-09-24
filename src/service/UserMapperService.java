package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Bill;
import entity.Provider;
import entity.User;


public interface UserMapperService {
	public List<User> query();
	public int login(@Param("userCode")String userName,@Param("userPassword")String userPassword);
	public List<Bill> billList(@Param("productName")String productName,@Param("isPayment")String isPayment);
	public Bill billAddName(String billCode);
	public Bill view(String id);
	public int billDel(String id);
	public int billadd(Bill bill);
	public List<Provider> providerList();
	public int uodate1(Bill bill);
}

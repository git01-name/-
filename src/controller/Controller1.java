package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import entity.Bill;
import entity.Provider;
import entity.User;
import service.UserMapperService;

@Controller
@RequestMapping("/jsp")
public class Controller1 {
	@Autowired
	private UserMapperService userMapperService;

	@RequestMapping("/user.do")
	public String query(HttpServletRequest request) {
		System.out.println("user.do------------------------");
		List<User> list = userMapperService.query();
		request.setAttribute("userList", list);
		return "userlist";

	}

	@RequestMapping("/login.do")
	public String login(HttpSession session,HttpServletRequest request) {
		System.out.println("login.do------------------------");
		String userName = request.getParameter("userCode");
		String userPassword = request.getParameter("userPassword");
		List<Provider>list=userMapperService.providerList();
		session.setAttribute("providerList",list);
		int a = userMapperService.login(userName, userPassword);
		if (a > 0) {
			return "frame";
		} else {
			return "error";
		}
	}

	@RequestMapping("/update1.do")
	public String update1(Bill bill){
	if (userMapperService.uodate1(bill)>0) {
		return "redirect:/jsp/bill.do";
	}else {
		return "redirect:/jsp/update1.do";
	}	
	}
	
	
	@RequestMapping("/bill.do")
	public String billList(HttpServletRequest request) {
		System.out.println("bill.do------------------------");
		String queryProductName = request.getParameter("queryProductName");
		String queryIsPayment = request.getParameter("queryIsPayment");
		List<Bill> list = userMapperService.billList(queryProductName, queryIsPayment);
		request.setAttribute("billList", list);
		return "billlist";

	}

	@RequestMapping("/billadd.do")
	public String aaa(HttpServletRequest request) {
		System.out.println("billadd.jsp---------------------------");
		return "billadd";

	}
	
	
	
	
	@RequestMapping("/modify.do")
	public String bbb(HttpServletRequest request) {
		//参数传递
		System.out.println("billmodify.jsp---------------------------");
		String id = request.getParameter("billid");
		System.out.println(id);
		Bill bill= userMapperService.view(id);
		request.setAttribute("bill", bill);
		return "billmodify";

	}

	@RequestMapping("billCodeExist")
	@ResponseBody
	public Object userCodeExist(@RequestParam String name) {
		HashMap<String, String> result = new HashMap<String, String>();
		Bill bill = userMapperService.billAddName(name);
		if (null != bill) {
			result.put("billCode", "exist");
		} else {
			result.put("billCode", "noexist");
		}
		return JSONArray.toJSONString(result);
	}

	@RequestMapping(value="/billview",method = RequestMethod.GET)
	@ResponseBody
	public Object billvie(@RequestParam String id){
		System.out.println(id);
		System.out.println("1111111111");
		Bill bill=new Bill();
		try {
			bill= userMapperService.view(id);
			System.out.println("222222222");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONArray.toJSONString(bill);
	}

	@RequestMapping("upload")
	public String upload(@RequestParam MultipartFile[] myfiles, HttpServletRequest request) throws IOException {
		System.out.println("name=" + request.getParameter("name"));

		System.out.println("myfiles.length=" + myfiles.length);

		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		System.out.println("上传的路径->" + realPath);

		for (MultipartFile multipartFile : myfiles) {
			System.out.println("文件长度: " + multipartFile.getSize() + "B");
			System.out.println("文件类型: " + multipartFile.getContentType());
			System.out.println("文件原名: " + multipartFile.getOriginalFilename());
			System.out.println("========================================");

			FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),
					new File(realPath, multipartFile.getOriginalFilename()));
		}

		System.out.println("上传成功~~~~~~~~");

		// return "redirect:/user/main.html";
		return "fileUpload";
	}
	@RequestMapping("billDel")
	@ResponseBody
	public Object billDel(@RequestParam String id){
		System.out.println("billDel-----------------------------");
		int a=0;
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		a= userMapperService.billDel(id);
		if (a>0) {
			result.put("aa",1);
			System.out.println("删除bill-------------------------");
		} else {
			result.put("aa",0);
		}
		return JSONArray.toJSONString(result);
	}
	
	
	@RequestMapping("billa")
	public String billadd1(Bill bill){
		
		//bill参数找不到，不存在
		System.out.println("billa-----------------------------");
		int a=0;
		a= userMapperService.billadd(bill);
		if (a>0) {
			System.out.println("新增bill-------------------------");
			
		} else {
			System.out.println("失败-----------------------------");
		}
		return "redirect:/jsp/bill.do";
	}
	
	
	@RequestMapping("/provider.do")
	public String provider(HttpSession session){
		List<Provider>list=userMapperService.providerList();
		session.setAttribute("providerList",list);
		return "providerlist";
	}
	
	
	

}

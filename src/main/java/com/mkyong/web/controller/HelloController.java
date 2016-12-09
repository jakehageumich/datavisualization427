package com.mkyong.web.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mykong.database.SIPProjectTDAO;
import com.mykong.model.SIPProjectT;
import com.mykong.model.SIPProjectTCurrent;

import com.mykong.database.SIPProjectTDAOCurrent;
import com.mykong.database.SIPProjectTDAOUtils;

@Controller
public class HelloController{

	static final int COLS = 3;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView hello(HttpServletRequest req) {
		
		try{
			//current DB values based on querying progress --- adjust this accordingly
			ArrayList<SIPProjectTCurrent> curList = SIPProjectTDAOCurrent.getAll();

			//distinct tenant names
			String [] tenantNames = SIPProjectTDAOCurrent.getDistinctTenantNames(curList);
			
			ModelAndView model = new ModelAndView();
			model.setViewName("home");
			model.addObject("table", curList);
			model.addObject("tenantNames", tenantNames);
			
			req.setAttribute("table", curList);
		
			return model;
			}catch(Exception e){

				System.out.println("FAILURE");
			}
			System.out.println("EXIT FAILURE");
			ModelAndView failed = new ModelAndView();
			return failed;
	}
	
	@RequestMapping(value = "/viewAll", method = RequestMethod.GET)
	public ModelAndView viewAll(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		
		ModelAndView model = new ModelAndView();
		
		ArrayList<SIPProjectTCurrent> all = SIPProjectTDAOCurrent.getAll();
		String [] tenantNames = SIPProjectTDAOCurrent.getDistinctTenantNames(all);
		
		model.setViewName("viewAll");
		model.addObject("all", all);
		model.addObject("tenantNames", tenantNames);		
		return model;
	}
	
	@RequestMapping(value = "/{name}/viewData", method = RequestMethod.GET)
	public ModelAndView viewData(@PathVariable String name, HttpServletRequest req) throws ClassNotFoundException, SQLException {
		
		ModelAndView model = new ModelAndView();
		
		ArrayList<SIPProjectTCurrent> all = SIPProjectTDAOCurrent.getAll();
		ArrayList<SIPProjectTCurrent> curList = new ArrayList<SIPProjectTCurrent>();

		for(int i = 0; i < all.size(); i++){
			if(all.get(i).getTenant().equals(name)){
				curList.add(all.get(i));
			}
		}
		
		model.addObject("name", name);
		model.addObject("tenantData", curList);
		model.setViewName("viewData");
		
		
		return model;
	}

}
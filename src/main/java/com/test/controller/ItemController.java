package com.test.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.test.domain.Item;
import com.test.domain.User;
import com.test.service.SQLService;

@Controller
public class ItemController {
	private ItemRepository itemRepo;
	private User user = new User();
	
	@Autowired
	private SQLService service;
	
	@Autowired
	public ItemController(ItemRepository itemRepo){
		this.itemRepo = itemRepo;
		this.user.setUserId("chexiang");
		this.user.setPassword("cx123456");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView mv = new ModelAndView("login.ftl");
		return mv;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, User loginUser){
		ModelAndView mv = new ModelAndView("login.ftl");
		
		if(loginUser.getUserId() != null && loginUser.getUserId().equals(user.getUserId()) && loginUser.getPassword().equals(user.getPassword())){
//			mv.setView(new RedirectView("/newIndex", false));
			Cookie user = new Cookie("userId", loginUser.getUserId());
			user.setComment("login records");
			user.setMaxAge(-1);   //this cookie will be deleted when the browser shut down
			mv.addObject("hint","login successfully");
			mv.setViewName("redirect:/newIndex");
			response.addCookie(user);
		}
		else{
			mv.addObject("hint","wrong userId or password");
		}
		return mv;
	}
	
	@RequestMapping(value  = "/newIndex", method = RequestMethod.GET)
	public ModelAndView newIndex(){
		ModelAndView view = new ModelAndView("newIndex.ftl");
		HashMap<String,Item> items = service.getAllItems();
//		HashMap<String,Item> items = new HashMap<String,Item>();
//		items = itemRepo.getItemRep();
		view.addObject("proItem",items);
		return view;
	}
	
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public ModelAndView addItem(@RequestBody String body,
			@Valid Item item, BindingResult bindingResult){
//		System.out.println("body" + body);
		ModelAndView view = new ModelAndView("newIndex.ftl");
		boolean hasError = bindingResult.hasErrors();
		if (hasError) {
			view.addObject("problem", item);
	        return view;  
	    } 
//		itemRepo.save(item);
		service.save(item);
		view.setView(new RedirectView("/newIndex", false));
		return view;
	}
	
	@RequestMapping(value = "/creat", method = RequestMethod.GET)
	public ModelAndView creatItem(@Valid @ModelAttribute("item")Item item, BindingResult bindingResult){
		ModelAndView view;
//		itemRepo.save(item);
		service.save(item);
		if (bindingResult.hasErrors()) {
			view = new ModelAndView("changeItem.ftl");
			view.addObject("problem", item);
			view.setView(new RedirectView("/creat/save", false));
	        return view;  
	    }
		else{
			view = new ModelAndView("newIndex.ftl");
//			itemRepo.save(item);
			service.save(item);
			view.setView(new RedirectView("/newIndex", false));
			return view;
		}
	}
	
	@RequestMapping(value = "/deleteItem/{id}", method = RequestMethod.GET)
	public ModelAndView deleteItem(@PathVariable("id") int id){
		ModelAndView view = new ModelAndView("redirect:/newIndex");
//		itemRepo.delete(id);
//		view.setView(new RedirectView("/newIndex", false));
		service.delete(id);
		return view;
	}
	
	@RequestMapping(value = "/changeItem/{id}", method = RequestMethod.GET)
	public ModelAndView changeItem(@PathVariable("id") int id){
		ModelAndView view = new ModelAndView("changeItem.ftl");
//		Item item_temp = itemRepo.getItem(id);
		Item item_temp = service.getSingleItem(id);
		view.addObject("changeItem",item_temp);
		return view;
	}
	
	@RequestMapping(value = "/changeItem/{id}/save", method = RequestMethod.POST)
	public ModelAndView saveItem(@PathVariable("id") int id, @ModelAttribute("item") Item item){
		ModelAndView view = new ModelAndView("newIndex.ftl");
//		itemRepo.saveChange(item);
		service.saveChange(item);
		view.setView(new RedirectView("/newIndex", false));
		return view;
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public ModelAndView handleFileUpload(HttpServletRequest request,@RequestParam("name") String name,@RequestParam("file") MultipartFile file){
		ModelAndView view = new ModelAndView("newIndex.ftl");
//		String rootPath = request.getSession().getServletContext()
//				.getRealPath("");
		if (!file.isEmpty()) {
			try{
				byte[] bytes = file.getBytes();
				String uuid = UUID.randomUUID().toString().replaceAll("-","");
				String fileName = uuid + file.getOriginalFilename();
				String path ="D:/projects/images/" +fileName; 
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
				stream.write(bytes);
				stream.close();
				view.addObject("imagesName", fileName);
				return view.addObject("DescString","successfully");
			}catch (Exception e){
				return view.addObject("DescString","failed" + e.getMessage());
				}
	        }else{
	        	return view.addObject("DescString","the file was empty.");
	        	}
	}
	

}

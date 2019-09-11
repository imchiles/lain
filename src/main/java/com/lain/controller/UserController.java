package com.lain.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lain.aop.LogAspect;
import com.lain.entity.User;
import com.lain.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController{
	/**
	 * user table DAO
	 */
	@Autowired
	private UserService userService;
	
	
	/**  --查询事件--  */
	/*查询所有user table信息*/
	@LogAspect(operationType="查询操作",operationName="查询指定用户")
	@RequestMapping(value="/findUserById/{uId}",method=RequestMethod.GET)
	@ResponseBody
	public User findUserById(@PathVariable("uId") int uId){
		System.out.println(userService.findUserById(uId).toString());
		return userService.findUserById(uId);
	}
	
	
	/*查询所有user table信息*/
	@LogAspect(operationType="查询操作",operationName="查询所有用户")
	@RequestMapping(value="/findUserAll",method=RequestMethod.GET)
	@ResponseBody
	public List<User> findUserAll(){
		return userService.findUserAll();
	}
	
	/**  --更新事件--  */
	
	/*根据uId更新user table的信息*/
	@LogAspect(operationType="更新操作",operationName="更新用户信息")
	@RequestMapping(value="/updateUserById",method=RequestMethod.PUT)
	@ResponseBody
	public boolean updateUserById(@RequestBody User user){
		return userService.updateUserById(user);
	}
	@LogAspect(operationType="更新操作",operationName="更新用户角色")
	@RequestMapping(value="/updateRIdById",method=RequestMethod.PUT)
	@ResponseBody
	public boolean updateRIdById(@RequestBody User user){
		return userService.updateRIdById(user);
		
	}
	
	/**  --添加事件--  */
	
	/*在user table添加一条数据*/
	@LogAspect(operationType="添加操作",operationName="添加一个用户")
	@RequestMapping(value="/insertUser",method=RequestMethod.POST)
	@ResponseBody
	public boolean insertUser(@RequestBody User user){
		return userService.insertUser(user);
	}
	
	/**  --删除事件--  */
	
	/*根据uId在user table删除一条数据*/
	@LogAspect(operationType="删除操作",operationName="删除一个用户")
	@RequestMapping(value="/deleteUserById/{uId}",method=RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteUserById(@PathVariable("uId") int uId){
		return userService.deleteUserById(uId);
	}
}

package com.lain.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lain.entity.Group;
import com.lain.service.GroupService;

@Controller
@RequestMapping("/group")
public class GroupController {
	@Autowired
	private GroupService groupService;
	
	
	@RequestMapping(value="/findGroupAll",method=RequestMethod.GET)
	@ResponseBody
	public Group findGroupAll(){
		List<Group> list = groupService.findGroupAll();
		return gentree(list, null);
	}
	private static Group gentree(List<Group> list, Group tree){
		if(tree != null){
			int id = tree.getgId();
			List<Group> children = tree.getChildren();
			for(Group t : list){
				int parentId = t.getParentId();
				if(id == parentId){
					Group group = new Group();
					group.setgId(t.getgId());
					group.setParentId(t.getParentId());
					group.setgName(t.getgName());
					group.setChildren(new ArrayList<Group>());
					children.add(group);
					gentree(list, group);
				}
			}
			tree.setChildren(children);
		}else{
			tree = new Group();
			tree.setgId(1);
			tree.setParentId(0);
			tree.setgName("×Ü±í");
			tree.setChildren(new ArrayList<Group>());
			gentree(list, tree);
		}
		return tree;
	}
}

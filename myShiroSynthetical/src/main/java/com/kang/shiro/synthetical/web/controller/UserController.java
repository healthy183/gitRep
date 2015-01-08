package com.kang.shiro.synthetical.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kang.shiro.synthertical.entity.User;
import com.kang.shiro.synthertical.service.OrganizationService;
import com.kang.shiro.synthertical.service.RoleService;
import com.kang.shiro.synthertical.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(method = RequestMethod.GET)
	@RequiresPermissions("user:view")
	public String list(Model model){
		
		model.addAttribute("userList", userService.findAll());
		
		return "user/list";
	}
	
	@RequiresPermissions("user:create")
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String createUser(Model model){
		
		setCommonData(model);
		
		model.addAttribute("user",new User());
		model.addAttribute("op", "new");
        return "user/edit";
	}
	
	@RequiresPermissions("user:create")
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String createUserSuccess(User user, RedirectAttributes redirectAttributes){
		
		userService.createUser(user);
		redirectAttributes.addFlashAttribute("msg", "new successfully!");
		return "redirect:/user";
	}
	
	@RequestMapping("/{id}/update")
	@RequiresPermissions("user:update")
	public ModelAndView  updateUser(Long id){
		return null;
	}
	
	@RequiresPermissions("user:update")
	@RequestMapping(value="{id}/changePassword",method=RequestMethod.GET)
	public String changePassword(@PathVariable("id") Long id, Model model){
		
		model.addAttribute("user",userService.findOne(id));
		model.addAttribute("msg","changePassword");
		return "user/changePassword";
	}
	
	@RequiresPermissions("user:update")
	@RequestMapping(value="{id}/changePassword",method=RequestMethod.POST)
	public String changePasswordSuccess(@PathVariable("id") Long id,String newPassword, Model model, RedirectAttributes redirectAttributes){
	
		userService.changePassword(id, newPassword);
		
		redirectAttributes.addFlashAttribute("msg", "changePasswordSuccess!");
		
		return "redirect:/user";
	}
	
	@RequiresPermissions("user:update")
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		setCommonData(model);
		model.addAttribute("user", userService.findOne(id));
		model.addAttribute("op", "updateUser");
		return "user/edit";
	}
	
	
	 @RequiresPermissions("user:update")
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String updateSuccess(User user, RedirectAttributes redirectAttributes) {
		userService.updateUser(user);
		redirectAttributes.addFlashAttribute("msg", "user updateSuccess!");
		return "redirect:/user";
	}
	
	 
	@RequiresPermissions("user:delete")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String showDeleteForm(@PathVariable("id") Long id, Model model) {
		setCommonData(model);
		model.addAttribute("user", userService.findOne(id));
		model.addAttribute("op", "delete");
		return "user/edit";
	}

	@RequiresPermissions("user:delete")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes) {
		userService.deleteUser(id);
		redirectAttributes.addFlashAttribute("msg", "delete Successfully!");
		return "redirect:/user";
	}
	
	
	private void setCommonData(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        model.addAttribute("roleList", roleService.findAll());
    }
	
}

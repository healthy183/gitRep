package com.kang.shiro.synthetical.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kang.shiro.synthertical.entity.Organization;
import com.kang.shiro.synthertical.service.OrganizationService;





@Controller
@RequestMapping("/organization")
public class OrganizationController {

	@Autowired
    private OrganizationService organizationService;

	
	@RequiresPermissions("organization:view")
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model){
		
		System.out.println("ou!");
		
		
		return "organization/index";
	}
	
	  @RequiresPermissions("organization:view")
      @RequestMapping(value = "/tree", method = RequestMethod.GET)
      public String showTree(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        return "organization/tree";
    }
	  
	  
	  @RequiresPermissions("organization:update")
	  @RequestMapping(value="{id}/maintain",method=RequestMethod.GET)
	  public String showMaintainForm(@PathVariable("id")Long id,Model model){
		  
		  
		  Organization organization =   organizationService.findOne(id);
		  
		  model.addAttribute("organization",organization );
		  
		  return "organization/maintain";
		  
	  }
	  
	  @RequiresPermissions("organization:update")
	  @RequestMapping(value="{id}/update",method =RequestMethod.POST )
	  public String update(Organization organization,RedirectAttributes redirectAttributes){
		  
		  organizationService.updateOrganization(organization);
		  
		  redirectAttributes.addFlashAttribute("msg", "update successfully!");
		  
		  
		  return "organization/success";
	  }
	  
	  @RequiresPermissions("organization:create")
	  @RequestMapping(value="{parentId}/appendChild",method= RequestMethod.GET)
	  public String showAppendChildForm(@PathVariable("parentId")Long parentId,Model model){
		  		
		  Organization parent =  organizationService.findOne(parentId);
		  model.addAttribute("parentOrganization", parent);
		  
		  Organization child = new Organization();
		  child.setParentId(parentId);
		  child.setParentIds(parent.makeSelfAsParentIds());
		  
		  model.addAttribute("childOrganization", child);
		  model.addAttribute("op", "new organization");
		  
		  return "organization/appendChild";
	  }
	  
	  @RequiresPermissions("organization:create")
	  @RequestMapping(value="/{parentId}/appendChild",method=RequestMethod.POST)
	  public String appendChild(Organization organization){
		  
		  organizationService.createOrganization(organization);
		  return "organization/success";
		  
	  }
	  
	  @RequiresPermissions("organization:delete")
	  @RequestMapping(value="{id}/delete",method=RequestMethod.POST)
	  public String deleteOrganization(@PathVariable("id")Long id,Model model){
		  
		  organizationService.deleteOrganization(id);
		  
		  return "organization/success";
		  
	  }
	  
	  @RequiresPermissions("organization:update")
	  @RequestMapping(value="{id}/move",method=RequestMethod.GET)
	  public String moveOrganization(@PathVariable("id")Long id,Model model){
		  
		
		  Organization organization =  organizationService.findOne(id);
		  model.addAttribute("source",organization);
		  model.addAttribute("targetList", organizationService.findAllWithExclude(organization));
		  
		  return "organization/move";
	  }
	  
	  @RequiresPermissions("organization:update")
	  @RequestMapping(value="{id}/move",method=RequestMethod.POST)
	  public String moveOrganizationSuccess(
			  @PathVariable("id")Long id,
			  @RequestParam("targetId") Long targetId){
		  
		  Organization source = organizationService.findOne(id);
	        Organization target = organizationService.findOne(targetId);
	        organizationService.move(source, target);
		  
		  return "organization/success";
	  }
	  
	  
	  
	  
	
}

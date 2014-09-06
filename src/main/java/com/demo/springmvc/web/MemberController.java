package com.demo.springmvc.web;

 import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.springmvc.domain.Member;
import com.demo.springmvc.repo.MemberDao;

@Controller
@RequestMapping(value="/")
public class MemberController {

	private final static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
    @Autowired
    private MemberDao memberDao;
    

    @RequestMapping(method=RequestMethod.GET)
    public String displaySortedMembers(Model model) {

    	model.addAttribute("newMember", new Member());
        model.addAttribute("members", memberDao.findAllOrderedByName());
        return "index";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String registerNewMember(@Valid @ModelAttribute("newMember") Member newMember, 
    		BindingResult result, Model model) {
    	
    	logger.info("Request for registering new member ...");
    	
        if (!result.hasErrors()) {
            memberDao.register(newMember);
            return "redirect:/";
        }
        else {
            model.addAttribute("members", memberDao.findAllOrderedByName());
            return "index";
        }
    }
    
}

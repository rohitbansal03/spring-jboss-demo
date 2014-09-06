package com.demo.springmvc.web;

import java.util.List;

import com.demo.springmvc.domain.Member;
import com.demo.springmvc.repo.MemberDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/members")
public class MemberRestController {
	
	private final static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
    @Autowired
    private MemberDao memberDao;

    
    @RequestMapping(method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<Member> listAllMembers() {
    	
    	logger.info("Request for listing all members ...");
        return memberDao.findAllOrderedByName();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody Member lookupMemberById(@PathVariable("id") Long id) {
        return memberDao.findById(id);
    }
    
}

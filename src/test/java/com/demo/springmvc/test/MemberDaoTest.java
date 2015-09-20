package com.demo.springmvc.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.demo.springmvc.domain.Member;
import com.demo.springmvc.repo.MemberDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MemberDaoTest {
	
	@Autowired
	private MemberDao memberDao;
	
	@Test
	public void testRegister() {
		
		Member member = new Member();
		member.setEmail("jane.doe@mailinator.com");
		member.setName("Jane Doe");
		member.setPhoneNumber("2125552121");

		memberDao.register(member);
		Long id = member.getId();
		Assert.assertNotNull(id);

		Member memberDB = memberDao.findById(id);
		Assert.assertEquals("Jane Doe", memberDB.getName());
		Assert.assertEquals("jane.doe@mailinator.com", memberDB.getEmail());
		Assert.assertEquals("2125552121", memberDB.getPhoneNumber());
	}

}

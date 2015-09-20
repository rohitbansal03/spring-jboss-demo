package com.demo.springmvc.repo;

import java.util.List;

import com.demo.springmvc.domain.Member;

/**
 * @author rohit.bansal
 *
 */
public interface MemberDao {

	public Member findById(Long id);

	public Member findByEmail(String email);

	public List<Member> findAllOrderedByName();

	public void register(Member member);

}

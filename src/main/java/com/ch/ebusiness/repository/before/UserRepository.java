package com.ch.ebusiness.repository.before;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ch.ebusiness.entity.BUser;
@Repository
public interface UserRepository {
	public List<BUser> isUse(BUser bUser);
	public int register(BUser bUser);
	public List<BUser> login(BUser bUser);
}

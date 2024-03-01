package com.ch.ebusiness.repository.admin;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ch.ebusiness.entity.AUser;
@Repository
public interface AdminRepository {
	List<AUser> login(AUser aUser);
}

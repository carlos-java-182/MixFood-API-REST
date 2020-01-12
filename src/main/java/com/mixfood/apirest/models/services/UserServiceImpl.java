package com.mixfood.apirest.models.services;

import java.util.List;
import java.util.stream.Collectors;

import com.mixfood.apirest.projections.UserEmail;
import com.mixfood.apirest.projections.UserInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.mixfood.apirest.entity.User;
import com.mixfood.apirest.models.dao.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService
{

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDAO userDao;
    
	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() 
	{
		return (List<User>) userDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(int id) 
	{
		return userDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public User save(User user) 
	{
		return userDao.save(user);
	}

	@Override
	public void delete(int id) 
	{
		userDao.deleteById(id);
	}

	@Override
	public boolean emailisValid(String email) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean authisValid(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserInformation findInformationById(int id) {
		return userDao.findInformationById(id);
	}

	@Override
	public UserEmail findEmailById(int id)
	{
		return userDao.findEmailById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}


	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
	{
		User user = userDao.findByEmail(s);
		System.out.println("EMAIL: "+ user.getEmail());
		if(user == null)
		{
			logger.error("ERROR IN LOGIN");
			throw new UsernameNotFoundException("ERROR IN LOGIN ");
		}
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getType()))
				.peek(authority -> logger.info("ROLE: " + authority.getAuthority()))
				.collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),user.getEnabled(),true,true,true,authorities);
	}
}

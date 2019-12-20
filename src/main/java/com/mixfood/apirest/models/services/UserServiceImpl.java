package com.mixfood.apirest.models.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mixfood.apirest.entity.User;
import com.mixfood.apirest.models.dao.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserDAO userDao;
    
	@Override
	@Transactional
	public List<User> findAll() 
	{
		return (List<User>) userDao.findAll();
	}

	@Override
	public User findById(int id) 
	{
		return userDao.findById(id).orElse(null);
	}

	@Override
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




}

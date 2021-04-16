package com.example.myapp.controller;
import com.example.myapp.dto.*;
import com.example.myapp.exception.ApiRequestException;
import com.example.myapp.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "User CRUD API")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	
	@Autowired  
	private UserService userService;  
//	@GetMapping
//    public ResponseEntity<UserPageDto> getUsers(@RequestParam(required = false, value = "pageNo") Integer pageNo,
//            @RequestParam(required = false, value = "pageSize") Integer pageSize,
//            @RequestParam(required = false, value = "sortBy") String sortBy,
//            @RequestParam(required = false, defaultValue = "true", value = "asc") boolean asc) {
//        UserPageDto userPage = userPaginationService.getPageDto(pageNo, pageSize, sortBy, asc);
//        return new ResponseEntity<>(Status.SUCCESS, HttpStatus.OK.value(), userPage);
//    }
	
	@ApiOperation(value = "Get list of Users", produces = "application/json")
	@GetMapping  
	private List<UserDto> getAllUsers()   
	{  
		throw new ApiRequestException("cannot get");
	 //return userService.getAllUserDtos();  
	}
	
	@ApiOperation(value = "Get a User by USER_ID", produces = "application/json")
	@GetMapping("/{userid}")  
	private UserDto getUserDtoById(@PathVariable("userid") long userid)   
	{  
	return userService.getUserDtoById(userid);  
	}
	
	@ApiOperation(value = "Create a User", produces = "application/json")
	@PostMapping
	private UserDto createUserDto(@RequestBody UserDto user)   
	{ 
		System.out.print(((Object)user.getId()).getClass().getSimpleName());
	userService.createUser(user);  
	return user;  
	}
	
	@ApiOperation(value = "Update a User", produces = "application/json")
	@PutMapping
	private UserDto updaUserDto(@RequestBody UserDto user)
	{
		userService.update(user);
		return user;
	}
	
	@ApiOperation(value = "Delete a User", produces = "application/json")
	@DeleteMapping("/{userid}")
	private void deleteUser(@PathVariable("userid") long userid) {
		userService.delete(userid);
	}
}

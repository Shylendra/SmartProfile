package com.smartapps.smartprofile.web.service.facade;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.smartapps.smartlib.dto.SmartUserDto;

@FeignClient(name = "SMARTUSER")
public interface SmartUserService {
	
	/* Register user */
	@PostMapping("/smartuser-api/register")
	SmartUserDto register(@RequestBody SmartUserDto obj, @RequestHeader Map<String, Object> headers);
	
	/* Retrieve users */
	@GetMapping("/smartuser-api/api/users")
	List<SmartUserDto> retrieveAll(@RequestHeader Map<String, Object> headers);

	/* Retrieve user by id */
	@GetMapping("/smartuser-api/api/users/{id}")
	SmartUserDto retrieveById(@PathVariable("id") String id, @RequestHeader Map<String, Object> headers);

	/* Retrieve users by userName */
	@GetMapping("/smartuser-api/api/users/name/{userName}")
	SmartUserDto retrieveByUserName(@PathVariable("userName") String userName, @RequestHeader Map<String, Object> headers);

	/* Retrieve users by userName and appId */
	@GetMapping("/smartuser-api/api/users/name/{userName}/appid/{appId}")
	SmartUserDto retrieveByUserNameAndAppId(@PathVariable("userName") String userName, @PathVariable("appId") String appId, @RequestHeader Map<String, Object> headers);
	
	/* Retrieve users by appId */
	@GetMapping("/smartuser-api/api/users/appid/{appId}")
	List<SmartUserDto> retrieveByAppId(@PathVariable("appId") String appId, @RequestHeader Map<String, Object> headers);
	
	/* Update user */
	@PutMapping("/smartuser-api/api/users/{id}")
	SmartUserDto update(@PathVariable("id") String id, @RequestBody SmartUserDto obj, @RequestHeader Map<String, Object> headers);
	
	/* Delete user by id */
	@DeleteMapping("/smartuser-api/api/users/{id}")
	String deleteById(@PathVariable("id") String id, @RequestHeader Map<String, Object> headers);

}

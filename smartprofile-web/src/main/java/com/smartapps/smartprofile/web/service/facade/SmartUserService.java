package com.smartapps.smartprofile.web.service.facade;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.smartapps.smartlib.dto.SmartUserDto;

//@FeignClient(name = "API-GATEWAY")
@FeignClient(url = "http://localhost:8000", name="API-GATEWAY")
public interface SmartUserService {
	
	/* Register user */
	
	/* Retrieve users */
	@GetMapping("/smartuser-api/api/users")
	List<SmartUserDto> retrieveAll();

	/* Retrieve user by id */
	@GetMapping("/smartuser-api/api/users/{id}")
	SmartUserDto retrieveById(@PathVariable("id") Integer id);

	/* Retrieve users by userName */
	@GetMapping("/smartuser-api/api/users/name/{userName}")
	SmartUserDto retrieveByUserName(@PathVariable("userName") String userName);

}

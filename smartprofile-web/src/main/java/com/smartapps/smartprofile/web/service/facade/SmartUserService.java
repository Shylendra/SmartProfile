package com.smartapps.smartprofile.web.service.facade;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.smartapps.smartlib.dto.SmartUserDto;

import io.swagger.v3.oas.annotations.Parameter;

@FeignClient(name = "SMARTUSER")
public interface SmartUserService {
	
	/* Register user */
	@PostMapping(path = "/smartuser-api/register", consumes = MediaType.APPLICATION_JSON)
	SmartUserDto register(@RequestHeader Map<String, Object> headers, @RequestBody SmartUserDto obj);
	
	/* Retrieve users */
	@GetMapping(path = "/smartuser-api/api/users", consumes = MediaType.APPLICATION_JSON)
	List<SmartUserDto> retrieveAll(@RequestHeader Map<String, Object> headers);

	/* Retrieve user by id */
	@GetMapping(path = "/smartuser-api/api/users/{id}", consumes = MediaType.APPLICATION_JSON)
	SmartUserDto retrieveById(@PathVariable("id") String id, @RequestHeader Map<String, Object> headers);

	/* Retrieve users by userName */
	@GetMapping(path = "/smartuser-api/api/users/name/{userName}", consumes = MediaType.APPLICATION_JSON)
	SmartUserDto retrieveByUserName(@PathVariable("userName") String userName, @RequestHeader Map<String, Object> headers);

	/* Retrieve users by userName and appId */
	@GetMapping(path = "/smartuser-api/api/users/name/{userName}/appid/{appId}", consumes = MediaType.APPLICATION_JSON)
	SmartUserDto retrieveByUserNameAndAppId(@PathVariable("userName") String userName, @PathVariable("appId") String appId, @RequestHeader Map<String, Object> headers);

	/* Check if user exists by userName and appId */
	@GetMapping(path = "/smartuser-api/user-exists/name/{userName}/appid/{appId}", consumes = MediaType.APPLICATION_JSON)
	Boolean isUserExistByUserNameAndAppId(@PathVariable("userName") String userName, @PathVariable("appId") String appId, @RequestHeader Map<String, Object> headers);
	
	/* Retrieve users by appId */
	@GetMapping(path = "/smartuser-api/api/users/appid/{appId}", consumes = MediaType.APPLICATION_JSON)
	List<SmartUserDto> retrieveByAppId(@PathVariable("appId") String appId, @RequestHeader Map<String, Object> headers);

	/* Retrieve users by appIds */
	@PostMapping(path = "/smartuser-api/api/users/appids", consumes = MediaType.APPLICATION_JSON)
	List<SmartUserDto> retrieveByAppIds(@Parameter(name = "appIds", required = true) @Valid @RequestBody List<String> appIds, @RequestHeader Map<String, Object> headers);
	
	/* Update user */
	@PutMapping(path = "/smartuser-api/api/users/{id}", consumes = MediaType.APPLICATION_JSON)
	SmartUserDto update(@PathVariable("id") String id, @RequestBody SmartUserDto obj, @RequestHeader Map<String, Object> headers);

	/* Update user status */
	@PutMapping(path = "/smartuser-api/api/users/{id}/status", consumes = MediaType.APPLICATION_JSON)
	void updateStatus(@PathVariable("id") String id, @RequestBody String status, @RequestHeader Map<String, Object> headers);
	
	/* Delete user by id */
	@DeleteMapping(path = "/smartuser-api/api/users/{id}", consumes = MediaType.APPLICATION_JSON)
	String deleteById(@PathVariable("id") String id, @RequestHeader Map<String, Object> headers);
	
	/* Delete users by ids(bulk delete) */
	@PostMapping(path = "/smartuser-api/api/users/bulk-delete")
	String deleteByIdIn(@Parameter(name = "ids", required = true) @RequestBody List<String> ids, @RequestHeader Map<String, Object> headers);

}

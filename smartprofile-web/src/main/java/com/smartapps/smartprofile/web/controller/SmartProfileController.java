package com.smartapps.smartprofile.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.annotations.GlobalApiReponsesGet;
import com.smartapps.smartlib.annotations.GlobalApiReponsesPost;
import com.smartapps.smartlib.dto.SmartUserDto;
import com.smartapps.smartprofile.web.util.SmartProfileWebUtil;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping(path = SmartProfileWebUtil.CONTEXT_ROOT, produces = MediaType.APPLICATION_JSON)
public class SmartProfileController extends CommonController {

	@Operation(summary = SmartProfileWebUtil.REGISTER_USER_OPERATION)
	@GlobalApiReponsesPost
	@PostMapping(SmartProfileWebUtil.REGISTER_USER)
	public ResponseEntity<SmartUserDto> register(
			@RequestBody SmartUserDto user,
			@RequestHeader Map<String, Object> headers) 
			throws JsonProcessingException {
		return ResponseEntity.ok().body(smartUserService.register(user, headers));
	}

	@Operation(summary = SmartProfileWebUtil.RETRIEVE_USERS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartProfileWebUtil.RETRIEVE_USERS)
	public ResponseEntity<List<SmartUserDto>> retrieveAll(@RequestHeader Map<String, Object> headers) 
			throws IOException {
		List<SmartUserDto> profiles = smartUserService.retrieveAll(headers);
		for(SmartUserDto profile: profiles) {
			updateAddressDetails(profile, headers);
		}
		
		return ResponseEntity.ok().body(profiles);
	}
	
	@Operation(summary = SmartProfileWebUtil.RETRIEVE_USER_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartProfileWebUtil.RETRIEVE_USER)
	public ResponseEntity<SmartUserDto> retrieveById(
			@PathVariable("id") @Valid String id,
			@RequestHeader Map<String, Object> headers) {
		SmartUserDto profile = smartUserService.retrieveById(id, headers);
		updateAddressDetails(profile, headers);
		return ResponseEntity.ok().body(profile);
	}

	@Operation(summary = SmartProfileWebUtil.RETRIEVE_NAME_USERS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartProfileWebUtil.RETRIEVE_NAME_USERS)
	public ResponseEntity<SmartUserDto> retrieveByUserName(
			@PathVariable("userName") @Valid String userName,
			@RequestHeader Map<String, Object> headers) throws JsonProcessingException {
		SmartUserDto profile = smartUserService.retrieveByUserName(userName, headers);
		updateAddressDetails(profile, headers);
		return ResponseEntity.ok().body(smartUserService.retrieveByUserName(userName, headers));
	}

	@Operation(summary = SmartProfileWebUtil.RETRIEVE_NAME_APPID_USERS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartProfileWebUtil.RETRIEVE_NAME_APPID_USERS)
	public ResponseEntity<SmartUserDto> retrieveByUserNameAndAppId(
			@PathVariable("userName") @Valid String userName,
			@PathVariable("appId") @Valid String appId,
			@RequestHeader Map<String, Object> headers) throws JsonProcessingException {
		SmartUserDto profile = smartUserService.retrieveByUserNameAndAppId(userName, appId, headers);
		updateAddressDetails(profile, headers);
		return ResponseEntity.ok().body(smartUserService.retrieveByUserNameAndAppId(userName, appId, headers));
	}

	@Operation(summary = SmartProfileWebUtil.RETRIEVE_APPID_USERS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartProfileWebUtil.RETRIEVE_APPID_USERS)
	public ResponseEntity<List<SmartUserDto>> retrieveByAppId(
			@PathVariable("appId") @Valid String appId,
			@RequestHeader Map<String, Object> headers) throws JsonProcessingException {
		List<SmartUserDto> profiles = smartUserService.retrieveByAppId(appId, headers);
		for(SmartUserDto profile: profiles) {
			updateAddressDetails(profile, headers);
		}
		return ResponseEntity.ok().body(profiles);
	}

	@Operation(summary = SmartProfileWebUtil.UPDATE_USER_OPERATION)
	@GlobalApiReponsesPost
	@PutMapping(SmartProfileWebUtil.UPDATE_USER)
	public ResponseEntity<SmartUserDto> update(
			@RequestBody SmartUserDto user,
			@RequestHeader Map<String, Object> headers) 
			throws JsonProcessingException {
		SmartUserDto profile = smartUserService.update(user.getId(), user, headers);
		updateAddressDetails(profile, headers);
		return ResponseEntity.ok().body(profile);
	}

	@Operation(summary = SmartProfileWebUtil.DELETE_USER_OPERATION)
	@GlobalApiReponsesPost
	@DeleteMapping(SmartProfileWebUtil.DELETE_USER)
	public ResponseEntity<String> deleteById(
			@PathVariable("id") @Valid String id,
			@RequestHeader Map<String, Object> headers) 
			throws JsonProcessingException {
		return ResponseEntity.ok().body(smartUserService.deleteById(id, headers));
	}
	
	private void updateAddressDetails(SmartUserDto profile, @RequestHeader Map<String, Object> headers) {
		if(profile != null) {
			profile.setAddresses(smartAddressService.retrieveByCustomerId(profile.getId(), headers));
			profile.setPrimaryAddress(profile.getPrimaryAddress());
		}
	}

}

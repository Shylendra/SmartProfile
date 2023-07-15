package com.smartapps.smartprofile.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
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
import com.smartapps.smartlib.annotations.GlobalApiReponsesDelete;
import com.smartapps.smartlib.annotations.GlobalApiReponsesGet;
import com.smartapps.smartlib.annotations.GlobalApiReponsesPost;
import com.smartapps.smartlib.annotations.GlobalApiReponsesPut;
import com.smartapps.smartlib.dto.SmartUserDto;
import com.smartapps.smartlib.util.SmartHttpUtil;
import com.smartapps.smartprofile.web.util.SmartProfileWebUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
			@RequestHeader Map<String, Object> headers,
			@Valid @RequestBody SmartUserDto user) 
			throws JsonProcessingException {
		return ResponseEntity.ok().body(smartUserService.register(SmartHttpUtil.filterSmartHeader(headers), user));
	}

	@Operation(summary = SmartProfileWebUtil.RETRIEVE_USERS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartProfileWebUtil.RETRIEVE_USERS)
	public ResponseEntity<List<SmartUserDto>> retrieveAll(@RequestHeader Map<String, Object> headers) 
			throws IOException {
		Map<String, Object> headersFiltered = SmartHttpUtil.filterSmartHeader(headers);
		List<SmartUserDto> profiles = smartUserService.retrieveAll(headersFiltered);
		for(SmartUserDto profile: profiles) {
			updateAddressDetails(profile, headersFiltered);
		}
		
		return ResponseEntity.ok().body(profiles);
	}
	
	@Operation(summary = SmartProfileWebUtil.RETRIEVE_USER_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartProfileWebUtil.RETRIEVE_USER)
	public ResponseEntity<SmartUserDto> retrieveById(
			@PathVariable("id") @Valid String id,
			@RequestHeader Map<String, Object> headers) {
		Map<String, Object> headersFiltered = SmartHttpUtil.filterSmartHeader(headers);
		
		SmartUserDto profile = smartUserService.retrieveById(id, headersFiltered);
		updateAddressDetails(profile, headersFiltered);
		return ResponseEntity.ok().body(profile);
	}

	@Operation(summary = SmartProfileWebUtil.RETRIEVE_NAME_USERS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartProfileWebUtil.RETRIEVE_NAME_USERS)
	public ResponseEntity<SmartUserDto> retrieveByUserName(
			@PathVariable("userName") @Valid String userName,
			@RequestHeader Map<String, Object> headers) throws JsonProcessingException {
		Map<String, Object> headersFiltered = SmartHttpUtil.filterSmartHeader(headers);
		
		SmartUserDto profile = smartUserService.retrieveByUserName(userName, headersFiltered);
		updateAddressDetails(profile, headersFiltered);
		return ResponseEntity.ok().body(profile);
		
	}

	@Operation(summary = SmartProfileWebUtil.RETRIEVE_NAME_APPID_USERS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartProfileWebUtil.RETRIEVE_NAME_APPID_USERS)
	public ResponseEntity<SmartUserDto> retrieveByUserNameAndAppId(
			@PathVariable("userName") @Valid String userName,
			@PathVariable("appId") @Valid String appId,
			@RequestHeader Map<String, Object> headers) throws JsonProcessingException {
		Map<String, Object> headersFiltered = SmartHttpUtil.filterSmartHeader(headers);
		
		SmartUserDto profile = smartUserService.retrieveByUserNameAndAppId(userName, appId, headersFiltered);
		updateAddressDetails(profile, headersFiltered);
		return ResponseEntity.ok().body(profile);
	}

	@Operation(summary = SmartProfileWebUtil.RETRIEVE_NAME_APPID_USEREXISTS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartProfileWebUtil.RETRIEVE_NAME_APPID_USEREXISTS)
	public ResponseEntity<Boolean> isUserExistByUserNameAndAppId(
			@PathVariable("userName") @Valid String userName,
			@PathVariable("appId") @Valid String appId,
			@RequestHeader Map<String, Object> headers) throws JsonProcessingException {
		return ResponseEntity.ok().body(smartUserService.isUserExistByUserNameAndAppId(userName, appId, SmartHttpUtil.filterSmartHeader(headers)));
	}

	@Operation(summary = SmartProfileWebUtil.RETRIEVE_APPID_USERS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartProfileWebUtil.RETRIEVE_APPID_USERS)
	public ResponseEntity<List<SmartUserDto>> retrieveByAppId(
			@PathVariable("appId") @Valid String appId,
			@RequestHeader Map<String, Object> headers) throws JsonProcessingException {
		Map<String, Object> headersFiltered = SmartHttpUtil.filterSmartHeader(headers);
		
		List<SmartUserDto> profiles = smartUserService.retrieveByAppId(appId, headersFiltered);
		for(SmartUserDto profile: profiles) {
			updateAddressDetails(profile, headersFiltered);
		}
		return ResponseEntity.ok().body(profiles);
	}

	@Operation(summary = SmartProfileWebUtil.RETRIEVE_APPIDS_USERS_OPERATION)
	@GlobalApiReponsesPost
	@PostMapping(SmartProfileWebUtil.RETRIEVE_APPIDS_USERS)
	public ResponseEntity<List<SmartUserDto>> retrieveByAppIds(
			@Parameter(name = "appIds", required = true) @Valid @RequestBody List<String> appIds,
			@RequestHeader Map<String, Object> headers) throws JsonProcessingException {
		return ResponseEntity.ok().body(smartUserService.retrieveByAppIds(appIds, SmartHttpUtil.filterSmartHeader(headers)));
	}

	@Operation(summary = SmartProfileWebUtil.UPDATE_USER_OPERATION)
	@GlobalApiReponsesPut
	@PutMapping(SmartProfileWebUtil.UPDATE_USER)
	public ResponseEntity<SmartUserDto> update(
			@RequestBody SmartUserDto user,
			@RequestHeader Map<String, Object> headers) 
			throws JsonProcessingException {
		Map<String, Object> headersFiltered = SmartHttpUtil.filterSmartHeader(headers);
		
		SmartUserDto profile = smartUserService.update(user.getId(), user, headersFiltered);
		updateAddressDetails(profile, headersFiltered);
		return ResponseEntity.ok().body(profile);
	}

	@Operation(summary = SmartProfileWebUtil.UPDATE_USER_STATUS_OPERATION)
	@GlobalApiReponsesPut
	@PutMapping(SmartProfileWebUtil.UPDATE_USER_STATUS)
	public void updateStatus(
			@PathVariable("id") @Valid String id,
			@Parameter(name = "status", description = "JSON with request object in and out", required = true) @Valid @RequestBody String status,
			@RequestHeader Map<String, Object> headers) 
			throws JsonProcessingException {
		Map<String, Object> headersFiltered = SmartHttpUtil.filterSmartHeader(headers);
		
		smartUserService.updateStatus(id, status, headersFiltered);
	}

	@Operation(summary = SmartProfileWebUtil.DELETE_USER_OPERATION)
	@GlobalApiReponsesDelete
	@DeleteMapping(SmartProfileWebUtil.DELETE_USER)
	public ResponseEntity<String> deleteById(
			@PathVariable("id") String id,
			@RequestHeader Map<String, Object> headers) 
			throws JsonProcessingException {
		Map<String, Object> headersFiltered = SmartHttpUtil.filterSmartHeader(headers);
		
		smartAddressService.deleteByCustomerId(id, headersFiltered);
		return ResponseEntity.ok().body(smartUserService.deleteById(id, headersFiltered));
	}

	@Operation(summary = SmartProfileWebUtil.DELETE_USER_INBULK_OPERATION)
	@GlobalApiReponsesPost
	@PostMapping(SmartProfileWebUtil.DELETE_USER_INBULK)
	public ResponseEntity<String> deleteByIdIn(
			@Parameter(name = "ids", required = true) @RequestBody List<String> ids,
			@RequestHeader Map<String, Object> headers) 
			throws JsonProcessingException {
		Map<String, Object> headersFiltered = SmartHttpUtil.filterSmartHeader(headers);
		
		smartAddressService.deleteByCustomerIdIn(ids, headersFiltered);
		return ResponseEntity.ok().body(smartUserService.deleteByIdIn(ids, headersFiltered));
	}
	
	private void updateAddressDetails(SmartUserDto profile, @RequestHeader Map<String, Object> headers) {
		if(profile != null) {
			profile.setAddresses(smartAddressService.retrieveByCustomerId(profile.getId(), headers));
			if(StringUtils.isNotEmpty(profile.getPrimaryAddress())) {
				profile.setPrimaryAddress(profile.getPrimaryAddress());
			}
		}
	}

}

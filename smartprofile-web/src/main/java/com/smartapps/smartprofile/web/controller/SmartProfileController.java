package com.smartapps.smartprofile.web.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.annotations.GlobalApiReponsesGet;
import com.smartapps.smartlib.dto.SmartUserDto;
import com.smartapps.smartprofile.web.util.SmartProfileWebUtil;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@Validated
@RequestMapping(path = SmartProfileWebUtil.CONTEXT_ROOT, produces = MediaType.APPLICATION_JSON)
public class SmartProfileController extends CommonController {

	@Operation(summary = SmartProfileWebUtil.RETRIEVE_USERS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartProfileWebUtil.RETRIEVE_USERS)
	public ResponseEntity<List<SmartUserDto>> retrieveAll() 
			throws IOException {
		return ResponseEntity.ok().body(smartUserService.retrieveAll());
	}

	@Operation(summary = SmartProfileWebUtil.RETRIEVE_USER_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartProfileWebUtil.RETRIEVE_USER)
	public ResponseEntity<SmartUserDto> retrieveById(
			@PathVariable("id") @Valid Integer id) {
		return ResponseEntity.ok().body(smartUserService.retrieveById(id));
	}

	@Operation(summary = SmartProfileWebUtil.RETRIEVE_NAME_USERS_OPERATION)
	@GlobalApiReponsesGet
	@GetMapping(SmartProfileWebUtil.RETRIEVE_NAME_USERS)
	public ResponseEntity<SmartUserDto> retrieveByUserName(
			@PathVariable("userName") @Valid String userName) throws JsonProcessingException {
		return ResponseEntity.ok().body(smartUserService.retrieveByUserName(userName));
	}

}

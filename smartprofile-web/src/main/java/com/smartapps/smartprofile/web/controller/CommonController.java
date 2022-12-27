package com.smartapps.smartprofile.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartapps.smartprofile.web.service.facade.SmartAddressService;
import com.smartapps.smartprofile.web.service.facade.SmartUserService;

public class CommonController {

	@Autowired
	protected SmartUserService smartUserService;
	
	@Autowired
	protected SmartAddressService smartAddressService;

}

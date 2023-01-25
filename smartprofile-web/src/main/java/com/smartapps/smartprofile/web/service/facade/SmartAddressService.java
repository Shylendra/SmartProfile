package com.smartapps.smartprofile.web.service.facade;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.smartapps.smartlib.dto.AddressDto;

@FeignClient(name = "SMARTADDRESS")
public interface SmartAddressService {
	
	/* Register address */
	
	/* Retrieve addresses */
	@GetMapping("/smartaddress-api/api/addresses")
	List<AddressDto> retrieveAll(@RequestHeader Map<String, Object> headers);

	/* Retrieve address by id */
	@GetMapping("/smartaddress-api/api/addresses/{id}")
	AddressDto retrieveById(@PathVariable("id") Integer id, @RequestHeader Map<String, Object> headers);

	/* Retrieve addresses by customer id */
	@GetMapping("/smartaddress-api/api/addresses/customer/{custId}")
	List<AddressDto> retrieveByCustomerId(@PathVariable("custId") String custId, @RequestHeader Map<String, Object> headers);

	/* Retrieve addresses by customer id and appId */
	@GetMapping("/smartaddress-api/api/addresses/customer/{custId}/appid/{appId}")
	List<AddressDto> readByCustomerIdAndAppId(@PathVariable("custId") String custId, @PathVariable("appId") String appId, @RequestHeader Map<String, Object> headers);

}

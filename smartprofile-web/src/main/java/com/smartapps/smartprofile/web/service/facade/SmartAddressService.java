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

import com.smartapps.smartlib.dto.AddressDto;

import io.swagger.v3.oas.annotations.Parameter;

@FeignClient(name = "SMARTADDRESS")
public interface SmartAddressService {
	
	/* Register address */
	@PostMapping(path = "/smartaddress-api/api/addresses", consumes = MediaType.APPLICATION_JSON)
	AddressDto register(@RequestHeader Map<String, Object> headers, @RequestBody AddressDto obj);

	/* Retrieve addresses */
	@GetMapping(path = "/smartaddress-api/api/addresses", consumes = MediaType.APPLICATION_JSON)
	List<AddressDto> retrieveAll(@RequestHeader Map<String, Object> headers);

	/* Retrieve address by id */
	@GetMapping(path = "/smartaddress-api/api/addresses/{id}", consumes = MediaType.APPLICATION_JSON)
	AddressDto retrieveById(@PathVariable("id") Integer id, @RequestHeader Map<String, Object> headers);

	/* Retrieve addresses by customer id */
	@GetMapping(path = "/smartaddress-api/api/addresses/customer/{custId}", consumes = MediaType.APPLICATION_JSON)
	List<AddressDto> retrieveByCustomerId(@PathVariable("custId") String custId, @RequestHeader Map<String, Object> headers);

	/* Retrieve addresses by customer id and appId */
	@GetMapping(path = "/smartaddress-api/api/addresses/customer/{custId}/appid/{appId}", consumes = MediaType.APPLICATION_JSON)
	List<AddressDto> readByCustomerIdAndAppId(@PathVariable("custId") String custId, @PathVariable("appId") String appId, @RequestHeader Map<String, Object> headers);
	
	/* Update address */
	@PutMapping(path = "/smartaddress-api/api/addresses/{id}", consumes = MediaType.APPLICATION_JSON)
	AddressDto update(@PathVariable("id") Integer id, @RequestBody AddressDto address, @RequestHeader Map<String, Object> headers);

	/* Delete address by id */
	@DeleteMapping(path = "/smartaddress-api/api/addresses/{id}", consumes = MediaType.APPLICATION_JSON)
	String deleteById(@PathVariable("id") Integer id, @RequestHeader Map<String, Object> headers);
	
	/* Delete address by customer id */
	@DeleteMapping(path = "/smartaddress-api/api/addresses/customer/{custId}", consumes = MediaType.APPLICATION_JSON)
	String deleteByCustomerId(@PathVariable("custId") String custId, @RequestHeader Map<String, Object> headers);
	
	/* Delete address by customer id list */
	@PostMapping(path = "/smartaddress-api/api/addresses/customer/bulk-delete", consumes = MediaType.APPLICATION_JSON)
	String deleteByCustomerIdIn(@Parameter(name = "custIds", required = true) @Valid @RequestBody List<String> custIds, @RequestHeader Map<String, Object> headers);

}

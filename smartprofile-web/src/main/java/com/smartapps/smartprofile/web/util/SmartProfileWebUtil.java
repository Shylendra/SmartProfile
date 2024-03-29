package com.smartapps.smartprofile.web.util;

public class SmartProfileWebUtil {

	/* SmartProfileController Settings */
	public static final String CONTEXT_ROOT = "/smartprofile-api/";

	public static final String REGISTER_USER_OPERATION = "Register user profile";
	public static final String REGISTER_USER = "api/profiles";
	
	public static final String RETRIEVE_USERS_OPERATION = "Retrieve user profiles";
	public static final String RETRIEVE_USERS = "api/profiles";
	
	public static final String RETRIEVE_USER_OPERATION = "Retrieve profile by user id";
	public static final String RETRIEVE_USER = "api/profiles/{id}";
	
	public static final String RETRIEVE_NAME_USERS_OPERATION = "Retrieve user profile by userName";
	public static final String RETRIEVE_NAME_USERS = "api/profiles/name/{userName}";
	
	public static final String RETRIEVE_NAME_APPID_USERS_OPERATION = "Retrieve user profiles by userName and appId";
	public static final String RETRIEVE_NAME_APPID_USERS = "api/profiles/name/{userName}/appid/{appId}";
	
	public static final String RETRIEVE_NAME_APPID_USEREXISTS_OPERATION = "Check if user exists by userName and appId";
	public static final String RETRIEVE_NAME_APPID_USEREXISTS = "profile-exists/name/{userName}/appid/{appId}";

	public static final String RETRIEVE_APPID_USERS_OPERATION = "Retrieve user profiles by appId";
	public static final String RETRIEVE_APPID_USERS = "api/profiles/appid/{appId}";
	
	public static final String RETRIEVE_APPIDS_USERS_OPERATION = "Retrieve users by appIds";
	public static final String RETRIEVE_APPIDS_USERS = "api/profiles/appids";

	public static final String UPDATE_USER_OPERATION = "Update user profile";
	public static final String UPDATE_USER = "api/profiles";
	
	public static final String UPDATE_USER_STATUS_OPERATION = "Update user status";
	public static final String UPDATE_USER_STATUS = "api/profiles/{id}/status";

	public static final String DELETE_USER_OPERATION = "Delete user profile by id";
	public static final String DELETE_USER = "api/profiles/{id}";
	
	public static final String DELETE_USER_INBULK_OPERATION = "Delete users by ids(bulk delete)";
	public static final String DELETE_USER_INBULK = "api/profiles/bulk-delete";

	/* SmartProfileController: Address service Settings */
	public static final String RETRIEVE_ADDRESSES_OPERATION = "Retrieve addresses";
	public static final String RETRIEVE_ADDRESSES = "api/profiles/addresses";

	private SmartProfileWebUtil() {
	}

}

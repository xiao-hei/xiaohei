package com.baizhi.code;

import java.util.Map;

public interface UserService {

	public void sendCode(String phone);
	
	public Map<String,String> checkCode(String phone,String code);
	
}


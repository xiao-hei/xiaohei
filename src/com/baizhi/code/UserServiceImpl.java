package com.baizhi.code;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.baizhi.util.CodeUtil;

@Service
public class UserServiceImpl implements UserService {

	public void sendCode(String phone) {
		// 第一步生成验证码
		String code = CodeUtil.getCode(4);
		String ret = null;
		try {
			// 第二步调用第三方平台发送验证码
			String PostData = "account=baizhizhangsan&password=baizhizhangsan&mobile="
					+ phone
					+ "&content="
					+ URLEncoder.encode("您的验证码是：" + code + "。如需帮助请联系客服。",
							"utf-8");
			// out.println(PostData);
			ret = Send.SMS(PostData, "http://sms.106jiekou.com/utf8/sms.aspx");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 第三步 如果相应状态成功 进行Redis存储
		/*if (ret.equals("100\n")) {
			Jedis jedis = JedisUtil.getJedis();

			jedis.select(1);

			jedis.setex(phone, 90, code);
		}*/
	}

	public Map<String, String> checkCode(String phone, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public Map<String, String> checkCode(String phone, String code) {
		// 第一步从Redis获取存储的验证码
		Jedis jedis = JedisUtil.getJedis();

		jedis.select(1);
		String rCode = jedis.get(phone);

		HashMap<String, String> map = new HashMap<String, String>();
		// 第二步进行校验
		if (rCode == null) {
			map.put("result", "fail");
		} else if (code.equals(rCode)) {
			map.put("result", "success");
		} else {
			map.put("result", "fail");
		}

		return map;

	}*/

}

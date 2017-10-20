package com.sehyeon.trainGood;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {

	@Autowired
	private UserDAO userDao;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(String id, String pw, String username) throws JsonProcessingException {
		User user = new User(id, pw, username);
		Map<String, String> map = new HashMap<>();
		try {
			userDao.insert(user);
			map.put("success", "true");
			map.put("message", "success");
		} catch (Exception e) {
			map.put("success", "false");
			map.put("message", "fail");
		}
		return new ObjectMapper().writeValueAsString(map);
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signin(HttpSession session, String id, String pw) throws JsonProcessingException {
		User user = userDao.select(id, pw);
		Map<String, String> map = new HashMap<>();
		if (user != null && user.getPw().equals(pw)) {
			session.setAttribute("user", user);
			map.put("success", "true");
			map.put("message", "");
			map.put("id", user.get_id());
			return new ObjectMapper().writeValueAsString(map);
		} else {
			map.put("success", "false");
			map.put("message", "");
			map.put("id", "");
			return new ObjectMapper().writeValueAsString(map);
		}
	}

	@RequestMapping(value = "/pointInquery", method = RequestMethod.GET)
	public String pointInquery(@RequestParam(value = "id", required = true) String parameter)
			throws JsonProcessingException {
		Integer point = 0;
		Map<String, String> map = new HashMap<>();
		try {
			point = userDao.inqueryPoint(parameter);
			map.put("success", "true");
			map.put("point", point.toString());
		} catch (Exception e) {
			map.put("success", "false");
			map.put("point", "-1");
		}
		return new ObjectMapper().writeValueAsString(map);
	}

	@RequestMapping(value = "/pointIncrease", method = RequestMethod.PUT)
	public String pointIncrease(@RequestParam(value = "id", required = true) String parameter)
			throws JsonProcessingException {
		Map<String, String> map = new HashMap<>();
		try {
			userDao.increasePoint(parameter);
			map.put("success", "true");
			return new ObjectMapper().writeValueAsString(map);
		} catch (Exception e) {
			map.put("success", "false");
			return new ObjectMapper().writeValueAsString(map);
		}
	}

	@RequestMapping(value = "/pointDecrease", method = RequestMethod.DELETE)
	public String pointDecrease(@RequestParam(value = "id", required = true) String parameter)
			throws JsonProcessingException {
		Map<String, String> map = new HashMap<>();
		try {
			userDao.decreasePoint(parameter);
			map.put("success", "true");
			return new ObjectMapper().writeValueAsString(map);
		} catch (Exception e) {
			map.put("success", "false");
			return new ObjectMapper().writeValueAsString(map);
		}
	}

}

package com.sehyeon.trainGood;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SitController {

	@Autowired
	private SitDAO sitDao;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/occupiedList", method = RequestMethod.GET)
	public String occupiedList(@RequestParam(value = "TrainNum", required = true) String trainnum)
			throws JsonProcessingException {
		JSONObject obj = new JSONObject();
		obj.put("success", "true");
		obj.put("result", sitDao.inqueryOccupiedList(trainnum));
		return obj.toString();
	}

	@RequestMapping(value = "/conquerseat", method = RequestMethod.POST)
	public String conquerseat(HttpSession session, String TrainNum, String ChairNum, String _id, String Dest)
			throws JsonProcessingException {
		int result = sitDao.select(TrainNum, ChairNum, _id, Dest);
		String msg = "";
		Boolean flag = false;
		switch (result) {
		case -1:
			msg = "Occupied";
			break;
		case -2:
			msg = "Reserved";
			break;
		case -3:
			msg = "Full";
			break;
		default:
			msg = "Success";
			flag = true;
			break;
		}
		Map<String, String> map = new HashMap<>();
		map.put("success", flag.toString());
		map.put("message", msg);
		return new ObjectMapper().writeValueAsString(map);
	}

	@RequestMapping(value = "/leaveseat", method = RequestMethod.DELETE)
	public String leaveseat(HttpSession session, @RequestParam(value = "TrainNum", required = true) String TrainNum,
			@RequestParam(value = "ChairNum", required = true) String ChairNum) throws JsonProcessingException {
		int result = sitDao.leave1(TrainNum, ChairNum);
		Map<String, String> map = new HashMap<>();
		if (result == 0) {
			map.put("success", "false");
			map.put("message", "fail");
		} else {
			map.put("success", "true");
			map.put("message", "success");
		}
		return new ObjectMapper().writeValueAsString(map);
	}

	@RequestMapping(value = "/leaveseat", method = RequestMethod.PUT)
	public String leaveseat2(HttpSession session, @RequestParam(value = "TrainNum", required = true) String TrainNum,
			@RequestParam(value = "ChairNum", required = true) String ChairNum) throws JsonProcessingException {
		int result = sitDao.leave2(TrainNum, ChairNum);
		Map<String, String> map = new HashMap<>();
		if (result == 0) {
			map.put("success", "false");
			map.put("message", "fail");
		} else {
			map.put("success", "true");
			map.put("message", "success");
		}
		return new ObjectMapper().writeValueAsString(map);
	}

	@RequestMapping(value = "/resevationseat", method = RequestMethod.PUT)
	public String reservationseat(HttpSession session,
			@RequestParam(value = "TrainNum", required = true) String TrainNum,
			@RequestParam(value = "ChairNum", required = true) String ChairNum,
			@RequestParam(value = "_id", required = true) String _id) throws JsonProcessingException {
		int result = sitDao.reserve(TrainNum, ChairNum, _id);
		Map<String, String> map = new HashMap<>();
		if (result == 0) {
			map.put("success", "false");
			map.put("message", "fail");
		} else {
			map.put("success", "true");
			map.put("message", "success");
		}
		return new ObjectMapper().writeValueAsString(map);
	}

	@RequestMapping(value = "/unresevationseat", method = RequestMethod.DELETE)
	public String unreservationseat(HttpSession session,
			@RequestParam(value = "TrainNum", required = true) String TrainNum,
			@RequestParam(value = "ChairNum", required = true) String ChairNum) throws JsonProcessingException {
		int result = sitDao.unreserve(TrainNum, ChairNum);
		Map<String, String> map = new HashMap<>();
		if (result == 0) {
			map.put("success", "false");
			map.put("message", "fail");
		} else {
			map.put("success", "true");
			map.put("message", "success");
		}
		return new ObjectMapper().writeValueAsString(map);
	}

}

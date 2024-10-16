package com.nt.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.Model_Entity.MatchClass;
import com.nt.Service.IService;

@CrossOrigin(origins = "http://127.0.0.1:5501")
@RestController
@RequestMapping("/cric")
public class MatchController {
	
	@Autowired
	private IService service;
	int count  = 0;
	
	@GetMapping("/live")
	public ResponseEntity<String> getLive(){
		count++;
		String msg = "MSG RECEIVED : "+count;
		System.out.println(msg);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
		@GetMapping("/livescore")
		public ResponseEntity<List<MatchClass>> getLiveMatches(){
			List<MatchClass> list = service.getLiveMatchScore();
			return new ResponseEntity<List<MatchClass>>(list,HttpStatus.OK);
		}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<List<MatchClass>> getAllMatchData(){
		List<MatchClass> list = service.getAllMatchesData();
		return new ResponseEntity<List<MatchClass>>(list,HttpStatus.OK);
	}
	
}

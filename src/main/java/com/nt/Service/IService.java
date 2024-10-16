package com.nt.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nt.Model_Entity.MatchClass;

@Component
public interface IService {

	//Get Live Match Score
	public List<MatchClass> getLiveMatchScore();
	
	//Get All Matches History
	public List<MatchClass> getAllMatchesData();
	
	//Get Points Table
	
}

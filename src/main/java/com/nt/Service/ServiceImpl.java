package com.nt.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.Model_Entity.MatchClass;
import com.nt.Repository.IRepository;

@Service
public class ServiceImpl implements IService {

	@Autowired
	private IRepository iRepo;
	
	@Override
	public List<MatchClass> getLiveMatchScore() {
		
		 List<MatchClass> matches = new ArrayList<>();
	        try {
	            String url = "https://www.cricbuzz.com/cricket-match/live-scores";
	            org.jsoup.nodes.Document document = Jsoup.connect(url).get();
	            org.jsoup.select.Elements liveScoreElements = document.select("div.cb-mtch-lst.cb-tms-itm");
	            for (org.jsoup.nodes.Element match : liveScoreElements) {
	                HashMap<String, String> liveMatchInfo = new LinkedHashMap<>();
	                String teamHeading = match.select("h3.cb-lv-scr-mtch-hdr").select("a").text();
	                String matchNumberVenue = match.select("span").text();
	                org.jsoup.select.Elements matchBatTeamInfo = match.select("div.cb-hmscg-bat-txt");
	                String battingTeam = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
	                String score = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
//	                String score = matchBatTeamInfo.select("div.cb-min-bat-rw").text();
	                                                      // 
	                org.jsoup.select.Elements bowlTeamInfo = match.select("div.cb-hmscg-bwl-txt");
	                String bowlTeam = bowlTeamInfo.select("div.cb-hmscg-tm-nm").text();
	                String bowlTeamScore = bowlTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
	                String textLive = match.select("div.cb-text-live").text();
	                String textComplete = match.select("div.cb-text-complete").text();
	                //getting match link
	                String matchLink = match.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

	                MatchClass match1 = new MatchClass();
	                match1.setTeamHeading(teamHeading);
	                match1.setMatchNoVenue(matchNumberVenue);
	                match1.setBattingTeam(battingTeam);
	                match1.setBattingTeamScore(score);
	                match1.setBowlingTeam(bowlTeam);
	                match1.setBowlingTeamScore(bowlTeamScore);
	                match1.setLiveText(textLive);
	                match1.setMatchLink(matchLink);
	                match1.setTextComplete(textComplete);
	                match1.setMatchStatus(matchLink);
	                match1.setLatestdate(new Date());


	                matches.add(match1);//List mein add kiya

//	                update the match in database
	                System.out.println("Calling Update match");
	                updateMatch(match1);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return matches;
		
	}//end if Livematch class
	
	
	private void updateMatch(MatchClass match){
		MatchClass mc = iRepo.findByTeamHeading(match.getTeamHeading());
		if(mc == null){ //matlab match data nahi hai // fresh save krlo
			iRepo.save(match);
		}
		else{
			  match.setMatchID(mc.getMatchID()); ///matlab match data hai ,sirf usko update kro 
			  iRepo.save(match);//match object uper se he update ho ke aya hai , idhar sif id badal dii
								//taaki nai id generate na ho or naya row form na ho..........
			  
		}
		
		
	}
	
	@Override
	public List<MatchClass> getAllMatchesData() {
		List<MatchClass> list = iRepo.findAll();
		return list;
	}
	
	
	
	

}




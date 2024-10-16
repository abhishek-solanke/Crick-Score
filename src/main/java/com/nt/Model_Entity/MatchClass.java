package com.nt.Model_Entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="CrickInfo")
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Data
@Component
public class MatchClass {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer MatchID;
	
	private String teamHeading;
	
	private String MatchNoVenue;
	
	private String battingTeam;
	
	private String BattingTeamScore;
	
	private String BowlingTeam;
	
	private String BowlingTeamScore;
	
	private String LiveText;
	
//	@Enumerated
	private String textComplete;
	
	private String MatchLink;
	
	private String matchStatus;
	
	private Date Latestdate;
	
}

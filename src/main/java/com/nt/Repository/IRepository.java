package com.nt.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nt.Model_Entity.MatchClass;

@Repository
public interface IRepository extends JpaRepository<MatchClass,Integer>{

	public MatchClass findByTeamHeading(String TeamHeading);

	
}

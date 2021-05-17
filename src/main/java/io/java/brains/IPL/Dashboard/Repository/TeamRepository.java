package io.java.brains.IPL.Dashboard.Repository;

import io.java.brains.IPL.Dashboard.Model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    Team findByTeamName(String teamName);
}

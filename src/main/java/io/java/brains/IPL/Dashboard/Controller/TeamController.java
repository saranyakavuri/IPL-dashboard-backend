package io.java.brains.IPL.Dashboard.Controller;

import io.java.brains.IPL.Dashboard.Model.Match;
import io.java.brains.IPL.Dashboard.Model.Team;
import io.java.brains.IPL.Dashboard.Repository.MatchRepository;
import io.java.brains.IPL.Dashboard.Repository.TeamRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@CrossOrigin
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable  String teamName)
    {
      Team team = this.teamRepository.findByTeamName(teamName);

      team.setMatches(matchRepository.findLatestMatchesByTeam(teamName, 4));

      return team;
    }


    @GetMapping("/team/{teamName}/matches")

    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year)
    {
        LocalDate startDate = LocalDate.of(year, 1,1);
        LocalDate endDate   =LocalDate.of(year+1, 1,1);

       // return this.matchRepository.getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(teamName,startDate,endDate,teamName,startDate,endDate);
        return this.matchRepository.getMatchesByTeamBetweenDates(teamName,startDate,endDate);
    }
}

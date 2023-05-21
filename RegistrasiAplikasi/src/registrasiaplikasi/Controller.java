package registrasiaplikasi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private DatabaseManager databaseManager;

    public Controller() {
        databaseManager = new DatabaseManager();
    }

    public void tambahTim(TeamModel team) {
        Team teamEntity = mapModelToEntity(team);
        databaseManager.insertTeam(teamEntity);
    }

    public void updateTim(TeamModel team) {
        Team teamEntity = mapModelToEntity(team);
        databaseManager.updateTeam(teamEntity);
    }

    public void hapusTim(String namaTim) {
        databaseManager.deleteTeam(namaTim);
    }

    public List<TeamModel> getAllTeams() {
        ResultSet resultSet = databaseManager.getAllTeams();
        List<TeamModel> teams = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Team teamEntity = new Team();
                teamEntity.setNamaTim(resultSet.getString("team_name"));
                teamEntity.setGenre(resultSet.getString("genre"));
                teamEntity.setEmail(resultSet.getString("email"));
                teamEntity.setJumlahMember(resultSet.getInt("num_of_members"));

                TeamModel team = mapEntityToModel(teamEntity);
                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    private Team mapModelToEntity(TeamModel teamModel) {
        Team teamEntity = new Team();
        teamEntity.setNamaTim(teamModel.getNamaTim());
        teamEntity.setGenre(teamModel.getGenre());
        teamEntity.setEmail(teamModel.getEmail());
        teamEntity.setJumlahMember(teamModel.getJumlahMember());
        return teamEntity;
    }

    private TeamModel mapEntityToModel(Team teamEntity) {
        TeamModel teamModel = new TeamModel();
        teamModel.setNamaTim(teamEntity.getNamaTim());
        teamModel.setGenre(teamEntity.getGenre());
        teamModel.setEmail(teamEntity.getEmail());
        teamModel.setJumlahMember(teamEntity.getJumlahMember());
        return teamModel;
    }
}

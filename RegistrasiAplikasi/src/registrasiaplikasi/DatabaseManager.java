package registrasiaplikasi;

import java.sql.*;

public class DatabaseManager {
    private Connection connection;
    private Statement statement;

    public DatabaseManager() {
        try {
            // Menghubungkan ke database (misalnya MySQL)
            String url = "jdbc:mysql://localhost:3306/daftarpesertalomba";
            String username = "root";
            String password = "";
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTeam(Team team) {
        try {
            // Menyisipkan data tim ke dalam tabel
            String query = "INSERT INTO daftarpeserta (team_name, genre, email, num_of_members) " +
                           "VALUES ('" + team.getNamaTim() + "', '" + team.getGenre() + "', '" + team.getEmail() +
                           "', " + team.getJumlahMember() + ")";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTeam(Team team) {
        try {
            // Mengupdate data tim dalam tabel berdasarkan nama tim
            String query = "UPDATE daftarpeserta SET genre = '" + team.getGenre() + "', email = '" + team.getEmail() +
                           "', num_of_members = " + team.getJumlahMember() + " WHERE team_name = '" + team.getNamaTim() + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTeam(String namaTim) {
        try {
            // Menghapus data tim dari tabel berdasarkan nama tim
            String query = "DELETE FROM daftarpeserta WHERE team_name = '" + namaTim + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllTeams() {
        ResultSet resultSet = null;
        try {
            // Mengambil semua data tim dari tabel
            String query = "SELECT * FROM daftarpeserta";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}

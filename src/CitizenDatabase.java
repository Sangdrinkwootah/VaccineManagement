import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CitizenDatabase extends Database {
    private static final String INSERT_CITIZEN_SQL = "INSERT INTO citizen (id, name, date_of_birth, sex) VALUES (?, ?, ?, ?)";
    private static final String SELECT_A_CITIZEN_SQL = "SELECT * FROM citizen WHERE id=?";
    private static final String SELECT_ALL_CITIZEN_VACCINE_SQL = """
            SELECT c.id, c.name, c.date_of_birth, c.sex, array(select id from vaccination_record where vaccination_record.citizen_id= c.id) as vaccine_uuids
            FROM citizen c""";


    public Citizen insertCitizen(String id, String name, Date dateOfBirth, String sex) {
        try (
                Connection conn = this.getConnection();
        ) {
            PreparedStatement statement = conn.prepareStatement(INSERT_CITIZEN_SQL);
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setDate(3, new java.sql.Date(dateOfBirth.getTime()));
            statement.setString(4, sex);
            statement.execute();

            statement = conn.prepareStatement(SELECT_A_CITIZEN_SQL);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String dosesString = resultSet.getString("vaccine_uuids");
                List<String> doses = List.of(dosesString.split(","));
                return new Citizen(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("date_of_birth"),
                        resultSet.getString("sex"),
                        doses
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Citizen getCitizen(String id) {
        try (
                Connection conn = this.getConnection();
        ) {
            PreparedStatement statement = conn.prepareStatement(SELECT_A_CITIZEN_SQL);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String dosesString = resultSet.getString("vaccine_uuids");
                List<String> doses = List.of(dosesString.split(","));
                return new Citizen(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("date_of_birth"),
                        resultSet.getString("sex"),
                        doses);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Citizen> getAllCitizen() {
        List<Citizen> citizens = new ArrayList<>();
        try (
                Connection conn = this.getConnection();
                ) {
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL_CITIZEN_VACCINE_SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String dosesString = resultSet.getString("vaccine_uuids");
                dosesString = dosesString.replace("{", "");
                dosesString = dosesString.replace("}", "");
                List<String> doses = List.of(dosesString.split(","));
                if (doses.get(0).equals("")) {
                    doses = new ArrayList<>();
                }
                citizens.add(new Citizen(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("date_of_birth"),
                        resultSet.getString("sex"),
                        doses));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citizens;
    }

    public static void main(String[] args) {
        CitizenDatabase db = new CitizenDatabase();
        List<Citizen> citizens = db.getAllCitizen();
        for (Citizen citizen : citizens) {
            System.out.println(citizen.name);
        }

    }
}

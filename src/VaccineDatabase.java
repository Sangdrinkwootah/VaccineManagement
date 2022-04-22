import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VaccineDatabase extends Database {
    public static final String INSERT_VACCINES_SQL = "INSERT INTO vaccine " +
            "(batch_id) VALUES (?)";
    public static final String SELECT_A_BATCH_VACCINES_SQL = "SELECT * FROM vaccine WHERE batch_id = ?";
    public static final String UPDATE_INJECTED_VACCINES_SQL = "UPDATE vaccine SET injected = TRUE WHERE id = ?";
    public static final String SELECT_ALL_VACCINES_SQL = "SELECT * FROM vaccine";

    public List<String> insertVaccines(String batch_id, int amount) {
        List<String> vaccines = new ArrayList<>();
        try (
                Connection conn = this.getConnection();
                ) {
            PreparedStatement statement = conn.prepareStatement(INSERT_VACCINES_SQL);

            for (int i = 0; i < amount; i++) {
                statement.setString(1, batch_id);
                statement.addBatch();
            }
            statement.executeBatch();
            statement = conn.prepareStatement(SELECT_A_BATCH_VACCINES_SQL);
            statement.setString(1, batch_id);
            ResultSet resultSet = statement.executeQuery();
            String vaccineID;
            while (resultSet.next()) {
                vaccineID = resultSet.getString("id");
                vaccines.add(vaccineID);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return vaccines;
    }

    public void updateInjectedVaccines(List<String> vaccines) {
        try (
                Connection conn = this.getConnection();
                ) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_INJECTED_VACCINES_SQL);
            for (String vaccine : vaccines) {
                statement.setString(1, vaccine);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Vaccine> selectAllVaccines() {
        List<Vaccine> vaccines = new ArrayList<>();
        try (
                Connection conn = this.getConnection();
                ) {
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL_VACCINES_SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Vaccine vaccine = new Vaccine (
                        resultSet.getString("id"),
                        resultSet.getString("batch_id"),
                        resultSet.getString( "name"),
                        resultSet.getDate("date_input")
                );
                vaccines.add(vaccine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaccines;
    }

    public static void main(String[] args) {
        VaccineDatabase vaccineDatabase = new VaccineDatabase();
//        Generate a UUID
//        String batch_id = java.util.UUID.randomUUID().toString();
//        List<String> vaccines = vaccineDatabase.insertVaccines(batch_id, 10);
//        int count = 0;
//        List<String> injectedVaccines = new ArrayList<>();
//        for (String vaccine : vaccines) {
//            System.out.println(vaccine);
//            count++;
//            if (count % 2 == 0) {
//                injectedVaccines.add(vaccine);
//            }
//        }
//        vaccineDatabase.updateInjectedVaccines(injectedVaccines);
//    }
        List<Vaccine> vaccines = vaccineDatabase.selectAllVaccines();
        for (Vaccine vaccine : vaccines) {
            System.out.println(vaccine.getId());
        }
    }
}

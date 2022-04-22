import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class VaccineBatchDatabase extends Database {
    private static final String INSERT_VACCINE_BATCH = "INSERT INTO vaccine_batch (id, name, production_date, expiry_date)" +
            " VALUES (?, ?, ?, ?, ?)";

    private static final String SELECT_VACCINE_BATCHES = "SELECT * FROM vaccine_batch";

    public void insertVaccineBatch(String id, String name, Date production_date, Date expiry_date, List<String> vaccine_uuids) {

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VACCINE_BATCH);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setDate(3, new java.sql.Date(production_date.getTime()));
            preparedStatement.setDate(4, new java.sql.Date(expiry_date.getTime()));
            preparedStatement.setArray(5, connection.createArrayOf("text", vaccine_uuids.toArray()));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<VaccineBatch> getVaccineBatches() {
        List<VaccineBatch> vaccineBatches = new java.util.ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VACCINE_BATCHES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                vaccineBatches.add(new VaccineBatch(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("production_date"),
                        resultSet.getDate("expiry_date"),
                        resultSet.getInt("interval")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaccineBatches;
    }

    public static void main(String[] args) {
        VaccineBatchDatabase vaccineBatchDatabase = new VaccineBatchDatabase();
        Date production_date = new Date();
        Date expiry_date = new Date(production_date.getTime() + (1000L * 60 * 60 * 24 * 365));
        vaccineBatchDatabase.insertVaccineBatch("93a7d182-71a3-45bd-add3-3d7a39822e95","Vaccine Batch 2", production_date, expiry_date, List.of("668ce9e9-3a6d-431e-b233-216e74c14f0f", "29e93893-cdb8-47d4-acfc-0a1cd2cbc9df", "2a9b069d-f874-4a68-8e1a-9d63f4aaec3b", "75cabe3e-6413-4138-bc9a-a2b5aceb0a7d", "e4a6e175-87dd-4829-86c2-644a38e2e924", "70c410c1-0f0c-481f-98f9-47c5a6a26d9b", "d8326470-6ced-4375-867d-1188a58b06c4", "01ff0166-5e35-4a3b-b4db-3d238458c021", "701ad7d5-37c8-4cd4-b4da-06eccaf8546c", "0adae0ad-406d-491d-93b8-c2201ea5feef"));

        List<VaccineBatch> vaccineBatches = vaccineBatchDatabase.getVaccineBatches();
        for (VaccineBatch vaccineBatch : vaccineBatches)
            System.out.println(vaccineBatch.name);

    }
}

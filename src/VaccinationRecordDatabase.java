import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class VaccinationRecordDatabase extends Database {
    private static final String INSERT_VACCINATION_RECORD_SQL = "INSERT INTO vaccination_record (citizen_id, vaccine_id, date) VALUES (?, ?, ?)";
    private static final String SELECT_VACCINATION_RECORDS_SQL = """
            SELECT vr.id, vr.citizen_id, vr.vaccine_id, vr.date
            FROM vaccination_record vr
            """;

    private static final String SELECT_VACCINATION_RECORD_BY_ID_SQL = SELECT_VACCINATION_RECORDS_SQL + " WHERE vr.id = ?";

    public void insert_vaccination_record(String citizen_id, String vaccine_id, String date) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VACCINATION_RECORD_SQL);
            preparedStatement.setString(1, citizen_id);
            preparedStatement.setString(2, vaccine_id);
            preparedStatement.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public VaccinationRecord get_vaccination_record_by_id(String id) {
        VaccinationRecord vaccinationRecord = null;
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VACCINATION_RECORD_BY_ID_SQL);
            preparedStatement.setString(1, id);
            try (java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    vaccinationRecord = new VaccinationRecord(resultSet.getString("id"), resultSet.getString("citizen_id"), resultSet.getString("vaccine_id"), resultSet.getDate("date"));
                    return vaccinationRecord;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaccinationRecord;
    }

    public List<VaccinationRecord> list_vaccination_record() {
        List<VaccinationRecord> vaccinationRecords = new java.util.ArrayList<>();
        try (Connection connection = getConnection()) {
            try (java.sql.ResultSet resultSet = connection.prepareStatement(SELECT_VACCINATION_RECORDS_SQL).executeQuery()) {
                while (resultSet.next()) {
                    vaccinationRecords.add(new VaccinationRecord(resultSet.getString("id"), resultSet.getString("citizen_id"), resultSet.getString("vaccine_id"), resultSet.getDate("date")));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaccinationRecords;
    }


    public static void main(String[] args) {
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExtendedVaccinationDatabase extends VaccinationRecordDatabase {
    private static final String SELECT_EXTENDED_VACCINATION_RECORDS = """
            SELECT vr.id, vr.citizen_id, vr.vaccine_id, vr.date, c.name as citizen_name, vb.name as vaccine_name, vb.production_date, vb.expiry_date
            FROM vaccination_record vr
            JOIN citizen c ON vr.citizen_id = c.id
            JOIN vaccine v on vr.vaccine_id = v.id
            JOIN vaccine_batch vb on v.batch_id = vb.id;
            """;

    public static final String SELECT_EXTENDED_VACCINATION_RECORD_BY_ID = SELECT_EXTENDED_VACCINATION_RECORDS + """
             WHERE vr.id = ?
            """;

    public static final String SELECT_EXTENDED_VACCINATION_RECORDS_BY_CITIZEN_ID = SELECT_EXTENDED_VACCINATION_RECORDS + """
             WHERE vr.citizen_id = ?
            """;

    public List<ExtendedVaccinationRecord> getExtendedVaccinationRecords() {
        List<ExtendedVaccinationRecord> records = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_EXTENDED_VACCINATION_RECORDS)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    records.add(new ExtendedVaccinationRecord(
                            rs.getString("id"),
                            rs.getString("citizen_id"),
                            rs.getString("vaccine_id"),
                            rs.getDate("date"),
                            rs.getString("citizen_name"),
                            rs.getString("vaccine_name"),
                            rs.getDate("production_date"),
                            rs.getDate("expiry_date")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public ExtendedVaccinationRecord getExtendedVaccinationRecordById(String id) {
        ExtendedVaccinationRecord record = null;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_EXTENDED_VACCINATION_RECORD_BY_ID)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    record = new ExtendedVaccinationRecord(
                            rs.getString("id"),
                            rs.getString("citizen_id"),
                            rs.getString("vaccine_id"),
                            rs.getDate("date"),
                            rs.getString("citizen_name"),
                            rs.getString("vaccine_name"),
                            rs.getDate("production_date"),
                            rs.getDate("expiry_date")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }

    public List<ExtendedVaccinationRecord> getExtendVaccinationRecordsByCitizenId(String citizenID) {
        List<ExtendedVaccinationRecord> records = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_EXTENDED_VACCINATION_RECORDS_BY_CITIZEN_ID)) {
            pstmt.setString(1, citizenID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    records.add(new ExtendedVaccinationRecord(
                            rs.getString("id"),
                            rs.getString("citizen_id"),
                            rs.getString("vaccine_id"),
                            rs.getDate("date"),
                            rs.getString("citizen_name"),
                            rs.getString("vaccine_name"),
                            rs.getDate("production_date"),
                            rs.getDate("expiry_date")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static void main(String[] args) {
        ExtendedVaccinationDatabase dao = new ExtendedVaccinationDatabase();
        List<ExtendedVaccinationRecord> records = dao.getExtendedVaccinationRecords();
        for (ExtendedVaccinationRecord record : records) {
            System.out.println(record);
        }
    }
}


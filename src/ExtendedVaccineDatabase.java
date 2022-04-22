import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ExtendedVaccineDatabase extends Database {
    private static final String SELECT_EXTENDED_VACCINE = """
            SELECT v.id, v.batch_id, v.injected, vb.name, vb.production_date, vb.expiry_date, vb.interval
            FROM vaccine v
            JOIN vaccine_batch vb ON v.batch_id = vb.id;
            """;
    private static final String SELECT_EXTENDED_VACCINE_BY_ID = SELECT_EXTENDED_VACCINE + " WHERE v.id = ?";

    public List<ExtendedVaccine> getExtendedVaccines() {
        List<ExtendedVaccine> extendedVaccines = new java.util.ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EXTENDED_VACCINE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ExtendedVaccine extendedVaccine = new ExtendedVaccine(
                        resultSet.getString("id"),
                        resultSet.getString("batch_id"),
                        resultSet.getString("name"),
                        resultSet.getDate("production_date"),
                        resultSet.getDate("expiry_date"),
                        resultSet.getInt("interval"),
                        resultSet.getBoolean("injected")
                );
                extendedVaccines.add(extendedVaccine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return extendedVaccines;
    }

    public ExtendedVaccine getExtendedVaccineById(String id) {
        ExtendedVaccine extendedVaccine = null;
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EXTENDED_VACCINE_BY_ID);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                extendedVaccine = new ExtendedVaccine(
                        resultSet.getString("id"),
                        resultSet.getString("batch_id"),
                        resultSet.getString("name"),
                        resultSet.getDate("production_date"),
                        resultSet.getDate("expiry_date"),
                        resultSet.getInt("interval"),
                        resultSet.getBoolean("injected")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return extendedVaccine;
    }

    public static void main(String[] args) {
        ExtendedVaccineDatabase extendedVaccineDatabase = new ExtendedVaccineDatabase();
        List<ExtendedVaccine> extendedVaccines = extendedVaccineDatabase.getExtendedVaccines();
        for (ExtendedVaccine extendedVaccine : extendedVaccines) {
            System.out.println(extendedVaccine.getProductionDate());
        }
    }
}

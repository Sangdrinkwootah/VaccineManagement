import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatVaccineDatabase extends Database {
    private static final String GET_AMOUNT_OF_VACCINE_TYPES = """
            SELECT vb.name as vaccine_name, count(vb.id) as amount
            FROM vaccine v
            JOIN vaccine_batch vb ON v.batch_id = vb.id
            WHERE v.injected = false and vb.expiry_date > now()
            GROUP BY vb.name;""";

    public List<StatVaccine> getVaccineStatistic() {
        List<StatVaccine> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_AMOUNT_OF_VACCINE_TYPES)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(new StatVaccine(resultSet.getString("vaccine_name"), resultSet.getInt("amount")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        StatVaccineDatabase statVaccineDatabase = new StatVaccineDatabase();
        List<StatVaccine> stats = statVaccineDatabase.getVaccineStatistic();
        for (StatVaccine stat : stats) {
            System.out.println(stat.getName() + ": " + stat.getAmount());
        }
    }
}

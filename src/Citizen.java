import java.sql.Date;
import java.util.List;

public class Citizen {
    protected String id;
    protected String name;
    protected Date dateOfBirth;
    protected String sex;
    protected List<String> doses;

    public Citizen(String id, String name, Date dateOfBirth, String sex, List<String> doses) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.doses = doses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<String> getDoses() {
        return doses;
    }

    public void setDoses(List<String> doses) {
        this.doses = doses;
    }
}

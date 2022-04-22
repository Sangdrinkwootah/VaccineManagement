import java.util.Date;

public class VaccinationRecord {
    private String id;
    private String citizenId;
    private String vaccineId;
    private Date date;

    public VaccinationRecord(String id, String citizenId, String vaccineId, Date date) {
        this.id = id;
        this.citizenId = citizenId;
        this.vaccineId = vaccineId;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

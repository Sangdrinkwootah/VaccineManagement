import java.util.Date;

public class ExtendedVaccinationRecord extends VaccinationRecord {
    private String citizenName;
    private String vaccineName;
    private Date productionDate;
    private Date expiryDate;

    public ExtendedVaccinationRecord(String id, String citizenId, String vaccineId, Date date,
                                     String citizenName, String vaccineName, Date productionDate, Date expiryDate) {
        super(id, citizenId, vaccineId, date);
        this.citizenName = citizenName;
        this.vaccineName = vaccineName;
        this.productionDate = productionDate;
        this.expiryDate = expiryDate;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}

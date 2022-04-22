import java.sql.Date;

public class ExtendedVaccine extends Vaccine {
    private String vaccineName;
    private Date productionDate;
    private Date expirationDate;
    private int interval;
    private boolean injected;

    public ExtendedVaccine(String id, String batch_id, String vaccineName, Date productionDate,
                           Date expirationDate, int interval, boolean injected) {
        super(id, batch_id, vaccineName, productionDate);
        this.vaccineName = vaccineName;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
        this.interval = interval;
        this.injected = injected;
    }

    public boolean isInjected() {
        return injected;
    }

    public void setInjected(boolean injected) {
        this.injected = injected;
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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

}

import java.util.Date;

public class VaccineBatch {
    protected String batchId;
    protected String name;
    protected Date productionDate;
    protected Date expiryDate;
    protected int interval;

    public VaccineBatch(String batchId, String name, Date productionDate, Date expiryDate, int interval) {
        this.batchId = batchId;
        this.name = name;
        this.productionDate = productionDate;
        this.expiryDate = expiryDate;
        this.interval = interval;
    }

    public String getBatchId() {
        return batchId;
    }

    public String getName() {
        return name;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public int getInterval() {
        return interval;
    }
}



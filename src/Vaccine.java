import java.sql.Date;

public class Vaccine {
    private String id;
    private String batchId;
    private String name;

    private Date productionDate;

    public Vaccine(String id, String batch_id, String name, Date product_date) {
        this.id = id;
        this.batchId = batch_id;
        this.name = name;
        this.productionDate = product_date;
    }

    public String getName() {
        return name;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public String getId() {
        return id;
    }

    public String getBatchId() {
        return batchId;
    }

    public String toString() {
        return "Vaccine{" + "id=" + id + ", batch_id=" + batchId + '}';
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductionDate(Date product_date) {
        this.productionDate = product_date;
    }
}


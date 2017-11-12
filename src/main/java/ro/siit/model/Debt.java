package ro.siit.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "debt")
public class Debt {

    @Id
    @Column(name="id")
    private String id;

    @Column(name = "value")
    private double value;

    @Column(name = "description")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    private TruckCompany truckCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceCompany serviceCompany;

    public Debt() {
    }

    public Debt(double value, String description){
        this.value=value;
        this.description=description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }

    public TruckCompany getTruckCompany() {
        return truckCompany;
    }

    public ServiceCompany getServiceCompany() {
        return serviceCompany;
    }

}

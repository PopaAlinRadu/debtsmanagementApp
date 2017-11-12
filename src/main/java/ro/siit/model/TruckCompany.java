package ro.siit.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "truck_company")
public class TruckCompany {

    @Id
    @Column(name = "t_cui")
    private int CUI;

    @Column(name = "truck_company_name")
    private String tCompanyName;

//    @OneToMany(
//            mappedBy = "owner",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<Debt> owningDebts;

    public TruckCompany() {
    }

    public TruckCompany(int CUI, String tCompanyName){
        this.CUI=CUI;
        this.tCompanyName=tCompanyName;
    }

    public int getCUI() {
        return CUI;
    }

    public String gettCompanyName() {
        return tCompanyName;
    }
}

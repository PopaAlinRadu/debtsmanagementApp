package ro.siit.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "service_company")
public class ServiceCompany {

    @Id
    @Column(name = "s_cui")
    private int CUI;

    @Column(name = "service_company_name")
    private String sCompanyName;

//    @OneToMany(
//            mappedBy = "client",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    List<Debt> debts;

    public ServiceCompany() {
    }

    public ServiceCompany(int CUI, String sCompanyName) {
        this.CUI = CUI;
        this.sCompanyName = sCompanyName;
    }

    public int getCUI() {
        return CUI;
    }

    public String getsCompanyName() {
        return sCompanyName;
    }
}

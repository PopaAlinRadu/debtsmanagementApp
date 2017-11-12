package ro.siit.model;

public class AllDebts {
    private String id;
    private double value;
    private String description;
    private String sCompanyName;
    private int sCUI;
    private int tCUI;

    public AllDebts(String id, double value, String description, String sCompanyName, int sCUI, int tCUI) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.sCompanyName = sCompanyName;
        this.sCUI = sCUI;
        this.tCUI = tCUI;
    }

    public String getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public String getsCompanyName() {
        return sCompanyName;
    }

    public int getsCUI() {
        return sCUI;
    }

    public int gettCUI() {
        return tCUI;
    }
}

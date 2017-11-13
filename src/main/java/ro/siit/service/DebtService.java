package ro.siit.service;

import ro.siit.model.AllDebts;
import ro.siit.model.Debt;
import ro.siit.model.ServiceCompany;
import ro.siit.model.TruckCompany;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DebtService {

    private ConnectionService connectionService;
    private Connection connection;

    //list all truck company debts
    public List<AllDebts> getDebts(int CUI) {
        List<AllDebts> allDebtsList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT debt.id, debt.value, debt.description, service_company.s_cui, service_company.service_company_name, truck_company.t_cui " +
                    "FROM ((debt JOIN service_company ON debt.servicecompany_s_cui = service_company.s_cui) " +
                    "JOIN truck_company ON debt.truckcompany_t_cui = truck_company.t_cui) " +
                    "WHERE truckcompany_t_cui=?");
            statement.setInt(1, CUI);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allDebtsList.add(new AllDebts(rs.getString("id"), rs.getDouble("value"), rs.getString("description"),
                        rs.getString("service_company_name"), rs.getInt("s_cui"), rs.getInt("t_cui")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allDebtsList;
    }

    //add debt and service and update debt
    public void addDebtAndServiceCompany(Debt debt, ServiceCompany serviceCompany, int truckCompanyCUI) {
        if (!serviceCuiExistInDebt(serviceCompany.getCUI())) {
            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO service_company (s_cui,service_company_name) VALUES (?,?)");
                statement.setInt(1, serviceCompany.getCUI());
                statement.setString(2, serviceCompany.getsCompanyName());
                statement.executeUpdate();
                PreparedStatement statement1 = connection.prepareStatement("INSERT INTO debt (id,value,description,servicecompany_s_cui,truckcompany_t_cui) VALUES (?,?,?,?,?)");
                statement1.setString(1, UUID.randomUUID().toString());
                statement1.setDouble(2, debt.getValue());
                statement1.setString(3, debt.getDescription());
                statement1.setInt(4, serviceCompany.getCUI());
                statement1.setInt(5, truckCompanyCUI);
                statement1.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            if (truckCuiAndServiceCuiExistInDebt(serviceCompany.getCUI(), truckCompanyCUI)) {
                try {
                    String debtId = getDebtId(serviceCompany.getCUI(), truckCompanyCUI);
                    PreparedStatement statement = connection.prepareStatement("UPDATE debt SET value=?, description=? WHERE id=?");
                    statement.setDouble(1, debt.getValue());
                    statement.setString(2, debt.getDescription());
                    statement.setString(3, debtId);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PreparedStatement statement = connection.prepareStatement("INSERT  INTO debt (id,value,description,servicecompany_s_cui,truckcompany_t_cui) VALUES(?,?,?,?,?)");
                    statement.setString(1, UUID.randomUUID().toString());
                    statement.setDouble(2, debt.getValue());
                    statement.setString(3, debt.getDescription());
                    statement.setInt(4, serviceCompany.getCUI());
                    statement.setInt(5, truckCompanyCUI);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //find if service_cui exist in service company
    public boolean serviceCuiExistInServiceCompany(int CUI) {
        try {
            int serviceCui;
            PreparedStatement statement = connection.prepareStatement("SELECT service_company.s_cui FROM service_company WHERE s_cui=?");
            statement.setInt(1, CUI);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                serviceCui = rs.getInt("s_cui");
                if (serviceCui == CUI) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //find if cui exist in debt.servicecompany_s_cui
    public boolean serviceCuiExistInDebt(int CUI) {
        try {
            int serviceCui;
            PreparedStatement statement = connection.prepareStatement("SELECT debt.servicecompany_s_cui FROM debt WHERE servicecompany_s_cui=?");
            statement.setInt(1, CUI);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                serviceCui = rs.getInt("servicecompany_s_cui");
                if (serviceCui == CUI) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //find if in debt a truck company have a debt to a service company
    public boolean truckCuiAndServiceCuiExistInDebt(int sCui, int tCui) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT debt.id FROM debt WHERE servicecompany_s_cui=? AND truckcompany_t_cui=?");
            statement.setInt(1, sCui);
            statement.setInt(2, tCui);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String debtId = rs.getString("id");
                if (debtId != null) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //get debt id based on fk of servicecompany_s_cui and truckcompany_t_cui
    public String getDebtId(int sCui, int tCui) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT debt.id FROM debt WHERE servicecompany_s_cui=? AND truckcompany_t_cui=?");
            statement.setInt(1, sCui);
            statement.setInt(2, tCui);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String debt_id = rs.getString("id");
                return debt_id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //delete a debt with service
    public void deleteDebtAndService(String id, int s_cui) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM debt WHERE id= ?");
            statement.setString(1, id);
            statement.executeUpdate();
            PreparedStatement statement1 = connection.prepareStatement("DELETE FROM service_company WHERE s_cui= ?");
            statement1.setInt(1, s_cui);
            statement1.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
            System.out.println("This service still have money to get");
        }
    }
}

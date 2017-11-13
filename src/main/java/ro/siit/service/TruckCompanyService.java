package ro.siit.service;

import ro.siit.model.TruckCompany;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TruckCompanyService {

    private ConnectionService connectionService;
    private Connection connection = connectionService.getInstance().getConnection();

    //list truck companies
    public List<TruckCompany> getCompanies() {
        List<TruckCompany> listOfTruckCompanies = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT  * FROM truck_company");
            while (rs.next()) {
                listOfTruckCompanies.add(new TruckCompany(rs.getInt("t_cui"), rs.getString("truck_company_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfTruckCompanies;
    }

    //add truck companies
    public void addTruckCompany(TruckCompany tCompanyName) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO truck_company VALUES (?,?)");
            statement.setInt(1, tCompanyName.getCUI());
            statement.setString(2, tCompanyName.gettCompanyName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //delete a truck_company
    public void deleteTruckCompany(int CUI) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM truck_company WHERE t_cui = ?");
            statement.setInt(1, CUI);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
            System.out.println("You can`t delete a truck company that have debts");
        }
    }

    //find a company by CUI
    public TruckCompany getTruckCompany(int CUI) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM truck_company WHERE t_cui = ?");
            statement.setInt(1, CUI);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return new TruckCompany(rs.getInt("t_cui"), rs.getString("truck_company_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //edit a truck company
    public void updateTruckCompany(TruckCompany truckCompany, int CUI) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE truck_company SET t_cui=?, truck_company_name = ? WHERE t_cui = ?");
            statement.setInt(3, CUI);
            statement.setString(2, truckCompany.gettCompanyName());
            statement.setInt(1, truckCompany.getCUI());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
            System.out.println("You can`t update a truck company that have debts");
        }
    }

    //find if truck_cui exist in debt.truckcompany_t_cui
    public boolean truckCuiExistInDebt(int CUI) {
        try {
            int truckCui;
            PreparedStatement statement = connection.prepareStatement("SELECT debt.truckcompany_t_cui FROM debt WHERE truckcompany_t_cui=?");
            statement.setInt(1, CUI);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                truckCui = rs.getInt("truckcompany_t_cui");
                if (truckCui == CUI) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

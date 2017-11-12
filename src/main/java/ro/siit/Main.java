package ro.siit;

import ro.siit.model.AllDebts;
import ro.siit.service.DebtService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DebtService debtService = DebtService.getInstance();
//        List<AllDebts> allDebtsList = debtService.getDebts(1234567);
//        System.out.println("-->view debts and services:");
//        for (AllDebts allDebts : allDebtsList) {
//            System.out.println(allDebts.getValue() + " " + allDebts.getsCompanyName() + " " + allDebts.getsCUI() + " " + allDebts.getDescription());
//        }
//
//        System.out.println(debtService.serviceCuiExistInDebt(123131));
//        System.out.println(debtService.serviceCuiExistInServiceCompany(123131));
//        System.out.println(debtService.truckCuiExistInDebt(222222));
//        System.out.println(debtService.getDebtId(222222,111111));
//        System.out.println(debtService.truckCuiAndServiceCuiExistInDebt(222222,111111));
    }
}

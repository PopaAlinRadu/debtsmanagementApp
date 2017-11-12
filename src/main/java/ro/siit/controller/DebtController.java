package ro.siit.controller;

import ro.siit.model.AllDebts;
import ro.siit.model.Debt;
import ro.siit.model.ServiceCompany;
import ro.siit.model.TruckCompany;
import ro.siit.service.DebtService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/debtManagement"})
public class DebtController extends HttpServlet {
    public static final String LIST_ALL_TRUCK_COMPANIES = "listAllTruckCompanies";
    public static final String ACTION = "action";
    public static final String ADD_TRUCK_COMPANY = "addTruckCompany";
    public static final String EDIT_TRUCK_COMPANY = "editTruckCompany";
    public static final String DELETE_DEBT_AND_SERVICE = "deleteDebtAndService";
    public static final String DELETE_TRUCK_COMPANY = "deleteTruckCompany";
    public static final String SHOW_ADD_FORM_FOR_TRUCK_COMPANY = "showAddFormForTruckCompany";
    public static final String SHOW_EDIT_FORM_FOR_TRUCK_COMPANY = "showEditFormForTruckCompany";
    public static final String TRUCK_COMPANY_LIST = "tCompanyList";
    public static final String ALL_DEBTS_LIST = "allDebtsList";
    public static final String VIEW_DEBTS = "viewDebts";
    public static final String SHOW_FORM_TO_ADD_DEBT = "showFormToAddDebt";
    public static final String ADD_DEBT_AND_SERVICE = "addDebtAndService";
    public static final String SHOW_EDIT_FOR_FOR_SERVICE_ = "showEditFormForService";
    public static final String SHOW_FORM_TO_UPDATE_DEBT = "showUpdateFormForDebt";
    public static final String SEARCH = "search";

    private DebtService debtService;

    @Override
    public void init() throws ServletException {
        this.debtService = DebtService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION);
        if (action == null) {
            action = LIST_ALL_TRUCK_COMPANIES;
        }
        switch (action) {
            case LIST_ALL_TRUCK_COMPANIES:
                listTruckCompanies(req, resp);
                break;
            case DELETE_TRUCK_COMPANY:
                debtService.deleteTruckCompany(Integer.parseInt(req.getParameter("truck_id")));
                listTruckCompanies(req, resp);
                break;
            case SHOW_ADD_FORM_FOR_TRUCK_COMPANY:
                req.getRequestDispatcher("jsp/addForm.jsp").forward(req, resp);
                break;
            case SHOW_EDIT_FORM_FOR_TRUCK_COMPANY:
                TruckCompany truckCompany = debtService.getTruckCompany(Integer.parseInt(req.getParameter("truck_id")));
                req.setAttribute("oldCUI", truckCompany.getCUI());
                req.setAttribute("CUI", truckCompany.getCUI());
                req.setAttribute("tCompanyName", truckCompany.gettCompanyName());
                req.getRequestDispatcher("jsp/editForm.jsp").forward(req, resp);
                break;
            case VIEW_DEBTS:
                int CUI = Integer.parseInt(req.getParameter("truck_id"));
                listAllDebtsAndServices(req, resp, CUI);
                break;
            case SHOW_FORM_TO_ADD_DEBT:
                req.setAttribute("debt_id", req.getParameter("debt_id"));//aici se pune pe req dupa care se stocheaza intr-o variabila
                req.setAttribute("old_value", req.getParameter("debt_value"));
                req.setAttribute("oldCUI", req.getParameter("truck_id"));
                req.getRequestDispatcher("jsp/addDebts.jsp").forward(req, resp);
                break;
            case SHOW_FORM_TO_UPDATE_DEBT:
                req.setAttribute("debt_id", req.getParameter("debt_id"));//aici se pune pe req dupa care se stocheaza intr-o variabila
                req.setAttribute("old_value", req.getParameter("debt_value"));
                req.setAttribute("oldCUI", req.getParameter("truck_id"));
                req.getRequestDispatcher("jsp/updateDebts.jsp").forward(req, resp);
                break;
            case DELETE_DEBT_AND_SERVICE:
                String d_id = req.getParameter("debts_id");
                int s_cui = Integer.parseInt(req.getParameter("service_id"));
                debtService.deleteDebtAndService(d_id, s_cui);
                listAllDebtsAndServices(req, resp, Integer.parseInt(req.getParameter("truck_id")));
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION);
        if (action == null) {
            action = LIST_ALL_TRUCK_COMPANIES;
        }

        switch (action) {
            case ADD_DEBT_AND_SERVICE:
                int truckCompanyCUI = Integer.parseInt(req.getParameter("oldCUI"));
                String debt_id = req.getParameter("debt_id");//asa se aduce inapoi
                double value = Double.parseDouble(req.getParameter("value"));
                String description = req.getParameter("description");
                int serviceCompanyCUI = Integer.parseInt(req.getParameter("s_cui"));
                String serviceCompanyName = req.getParameter("sCompanyName");
                Debt objectDebt = new Debt(value, description);
                ServiceCompany objectServiceCompany = new ServiceCompany(serviceCompanyCUI, serviceCompanyName);

                debtService.addDebtAndServiceCompany(objectDebt, objectServiceCompany, truckCompanyCUI);
                listAllDebtsAndServices(req, resp, truckCompanyCUI);
                break;
            case ADD_TRUCK_COMPANY:
                TruckCompany truckCompany = new TruckCompany(Integer.parseInt(req.getParameter("CUI")), req.getParameter("tCompanyName"));
                debtService.addTruckCompany(truckCompany);
                listTruckCompanies(req, resp);
                break;
            case EDIT_TRUCK_COMPANY:
                int oldCUI = Integer.parseInt(req.getParameter("oldCUI"));
                debtService.updateTruckCompany(new TruckCompany(Integer.parseInt(req.getParameter("CUI")), req.getParameter("tCompanyName")), oldCUI);
                listTruckCompanies(req, resp);
                break;
            case SEARCH:
                int cuiForSearch = Integer.parseInt(req.getParameter("search"));
                listAllDebtsAndServices(req, resp, cuiForSearch);
                break;
        }
    }

    private void listAllDebtsAndServices(HttpServletRequest req, HttpServletResponse resp, int CUI) throws ServletException, IOException {
        List<AllDebts> allDebtsList = debtService.getDebts(CUI);
        req.setAttribute(ALL_DEBTS_LIST, allDebtsList);
        req.getRequestDispatcher("jsp/viewDebts.jsp").forward(req, resp);
    }

    private void listTruckCompanies(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TruckCompany> truckCompanies = debtService.getCompanies();
        req.setAttribute(TRUCK_COMPANY_LIST, truckCompanies);
        req.getRequestDispatcher("jsp/list.jsp").forward(req, resp);
    }
}

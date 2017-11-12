<%@ include file = "header.jsp" %>
<body>
    <div class="container">
		<h1 class="heading-1">Debts Management</h1>
        <div class="row">
            <div class="col-4">
                <a href="?action=showAddFormForTruckCompany"  class="btn btn-secondary btt">Add a truck company</a>
            </div>
            <div class="col-4">
                <a href="?action=listAllTruckCompanies"  class="btn btn-secondary btt">List all truck companies</a>
            </div>
            <div class="col-4">
                <form method="post" action="/debtManagement">
                    <div class="input-group">
                        <input type="hidden" name="action" value="search">
                        <input name="search" type="text" class="form-control" placeholder="Enter CUI " aria-label="Enter CUI">
                        <input type="submit"  value="search">
                    </div>
                </form>
            </div>
        </div>
            <%--a table that update for every company that you add--%>
            <table class="table table-striped">
			<thead class="thead-dark">
                <tr>
                    <th class="cell-1">Truck Company Name</th>
                    <th class="cell-2">CUI</th>
                    <th class="cell-3">Add a debt</th>
                    <th class="cell-4">Actions</th>
                </tr> 
			</thead>
                <c:forEach items="${tCompanyList}" var="t_Company">
                    <tr>
                        <td class="cell-1">
                            <a class="btn btn-secondary" href='?action=viewDebts&truck_id=<c:out value="${t_Company.CUI}"/>'>  <c:out value="${t_Company.tCompanyName}"/>  </a>
                        </td>
                        <td class="cell-2">
                            <c:out value="${t_Company.CUI}"/>
                        </td>
                        <td class="cell-3">
                            <a class="btn btn-secondary" href='?action=showFormToAddDebt&truck_id=<c:out value="${t_Company.CUI}"/>'>Add debt</a>
                        </td>
                        <td class="cell-4">
                            <a class="btn btn-danger btnd" href='?action=deleteTruckCompany&truck_id=<c:out value="${t_Company.CUI}"/>'>Delete</a>
                            <a class="btn btn-info btnd" href='?action=showEditFormForTruckCompany&truck_id=<c:out value="${t_Company.CUI}"/>'>Edit</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

    </div>
<%@ include file = "footer.jsp" %>
</body>
</html>

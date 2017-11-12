<%@ include file = "header.jsp" %>
<body>
	<div class="container">
		<h1 class="heading-1">Debts List</h1>

		<div class="row">
			<div class="col-4">
				<a href="?action=showAddFormForTruckCompany" class="btn btn-secondary btt">Add a truck company</a>
			</div>
			<div class="col-4">
				<a href="?action=listAllTruckCompanies" class="btn btn-secondary btt">List all truck companies</a>
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
                    <th class="cell-1-value col-sm-1">Value</th>
                    <th class="cell-2-company col-sm-3">Company</th>
                    <th class="cell-3-cui col-sm-2">CUI</th>
                    <th class="cell-5-description col-sm-2">Description</th>
					<th class="cell-4-actions col-sm-4">Actions</th>
                </tr> 
			</thead>
			<c:forEach items="${allDebtsList}" var="debts">
				<tr>
					<td>
						<c:out value="${debts.value}"/>
					</td>
					<td>
						<c:out value="${debts.sCompanyName}"/>
					</td>
					<td>
						<c:out value="${debts.sCUI}"/>
					</td>
					<td>
						<c:out value="${debts.description}"/>
					</td>
					<td>
						<a class="btn btn-info btndd" href='?action=showFormToAddDebt&truck_id=<c:out value="${debts.tCUI}"/>&sName=<c:out value="${debts.sCompanyName}"/>&sCui=<c:out value="${debts.sCUI}"/>&debt_id=<c:out value="${debts.id}"/>&debt_value=<c:out value="${debts.value}"/>'>
							Add debt
						</a>
						<a class="btn btn-info btndd" href='?action=showUpdateFormForDebt&truck_id=<c:out value="${debts.tCUI}"/>&sName=<c:out value="${debts.sCompanyName}"/>&sCui=<c:out value="${debts.sCUI}"/>&debt_id=<c:out value="${debts.id}"/>&debt_value=<c:out value="${debts.value}"/>'>
							Update
						</a>
						<a class="btn btn-danger btndd"  href='?action=deleteDebtAndService&service_id=<c:out value="${debts.sCUI}"/>&truck_id=<c:out value="${debts.tCUI}"/>&debts_id=<c:out value="${debts.id}"/>'>
							Delete
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>
<%@ include file = "footer.jsp" %>
</body>
</html>

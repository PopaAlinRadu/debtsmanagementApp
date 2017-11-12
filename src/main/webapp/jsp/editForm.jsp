<%@ include file = "header.jsp" %>
<body>
<div class="container">
<h1 class="heading-1">Edit Truck Company</h1>
    <form method="post" action="/debtManagement">		
		<input type="hidden" name="action" value="editTruckCompany">
        <input type="hidden" name="oldCUI" value='<c:out value="${oldCUI}"/>'>

			<div class="form-group row">
				<label for="inputCUI" class="col-sm-2 col-form-label">CUI :</label>
				<div class="form-group col-sm-8">
					<input name="CUI" class="form-control form-control-lg" type="text" class="form-control" id="inputCUI" placeholder="CUI" value='<c:out value="${CUI}"/>' pattern="\d{6,9}">
				</div>
			</div>

			<div class="form-group row">
				<label for="inputTruckCompanyName" class="col-sm-2 col-form-label">Company Name :</label>
				<div class="form-group col-sm-8">
					<input name="tCompanyName" class="form-control form-control-lg" type="text" id="inputTruckCompanyName" placeholder="Truck Company Name" value='<c:out value="${tCompanyName}"/>'>
				</div>
			</div>

			<div class="form-group button addTruck">
				<input type="submit" class="btn btn-primary" value="Edit Truck Company">
			</div>

    </form>
</div>
<%@ include file = "footer.jsp" %>
</body>
</html>
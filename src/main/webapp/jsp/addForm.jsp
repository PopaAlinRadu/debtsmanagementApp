<%@ include file = "header.jsp" %>
<body>
<div class="container">
	<h1 class="heading-1">Add Truck Company</h1>
		<form method="post" action="/debtManagement">
			<input type="hidden" name="action" value="addTruckCompany">
						
			<div class="form-group row">
				<label for="inputCUI" class="col-sm-2 col-form-label">CUI :</label>
				<div class="form-group col-sm-8">
					<input name="CUI" class="form-control form-control-lg" type="text" class="form-control" id="inputCUI" placeholder="cui" pattern="\d{6,9}">
				</div>
			</div>

			<div class="form-group row">
				<label for="inputTruckCompanyName" class="col-sm-2 col-form-label">Company Name :</label>
				<div class="form-group col-sm-8">
					<input name="tCompanyName" class="form-control form-control-lg" type="text" id="inputTruckCompanyName" placeholder="truck company name">
				</div>
			</div>

			<div class="form-group button addTruck">
				<input type="submit" class="btn btn-primary" value="Add Truck Company">
			</div>
				
		</form>
</div>
<%@ include file = "footer.jsp" %>
</body>
</html>



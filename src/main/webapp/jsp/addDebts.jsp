<%@ include file = "header.jsp" %>
    <body>
        <div class="container">
			<h1 class="heading-1">Add Debt</h1>
            <form method="post" class="row" action="/debtManagement">

                <input type="hidden" name="action" value="addDebtAndService">
                <input type="hidden" name="oldCUI" value='<c:out value="${oldCUI}"/>'>
                <input type="hidden" name="debt_id" value='<c:out value="${debt_id}"/>'><%--asa se stocheaza--%>

				<div class="col">
					<div class="form-group">
						<label for="value">Value</label>
						<input name="value" class="form-control form-control-lg" id="value" type="text" placeholder="value of debt">
					</div>
					<div class="form-group">
						<label for="inputCUI">CUI</label>
						<input name="s_cui" type="text" class="form-control form-control-lg" id="inputCUI" placeholder="cui" pattern="\d{6,9}">
					</div>
					<div class="form-group">
						<label for="name">Company Name</label>
						<input name="sCompanyName" type="text" class="form-control form-control-lg" id="name" placeholder="service company name">
					</div>
                </div>

				<div class="col">
					<div class="form-group">
						<label for="description">Description</label>
						<textarea name="description" type="text" class="form-control form-control-lg" id="description" placeholder="description"></textarea>
					</div>			
				</div>	
				<div class="form-group button">
				  <input type="submit" class="btn btn-primary" value="Add Debt">
				</div>					

            </form>
        </div>

<%@ include file = "footer.jsp" %>
    </body>
</html>
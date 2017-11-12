<%@ page import="ro.siit.controller.DebtController" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Debts App</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
	<style>
		body {color: #333;}
		.heading-1 {margin: 50px 0;}
		.btt {background: #5d8fbf; border-color: #5d8fbf;}		
		.table {border: 1px solid #d8dee4; }
		.table .thead-dark th {background-color: #6c757d; color: #ffffff; border: 0;}
		.cell-4 {text-align:center}
		.btnd {text-align:center; width: 35%}
        .btndd {text-align:center; width: 30%}
		.btn {cursor: pointer;}
		form .button {width:100%; display: inline-block; padding-left: 15px;}
		form textarea {min-height: 250px;}
		form .addTruck{padding-left: 0px;}
		label {font-size: 20px;}
        .cell-1, .cell-2,.cell-3, .cell-4, .cell-5{width: 0%}
        .cell-1-value{width: 5%}
        .cell-2-company{width: 30%}
        .cell-3-cui{width: 5%}
        .cell-4-actions{width: 30%; text-align: center}
        .cell-5-description{width: 25%}
	</style>
</head>
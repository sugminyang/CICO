<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, java.sql.*, java.io.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top"
		id="sideNav">

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<a class="navbar-brand js-scroll-trigger" href="http://localhost:8080/spring-mvc-example/#page-top"> <span
				class="d-block d-lg-none"></span> 
				<img src="http://bike.snu.ac.kr/sites/default/files/images/bike-intro-logo-400.jpg"
				class="logo" width="100" height="50">
			</a> 
			<div class="menu" align="center">
				<a class="nav-link js-scroll-trigger" href="http://localhost:8080/spring-mvc-example/#about">About</a> 
				<a class="nav-link js-scroll-trigger" href="http://localhost:8080/spring-mvc-example/#search-point">Search-point</a>
				<a class="nav-link js-scroll-trigger" href="http://localhost:8080/spring-mvc-example/#sparql-endpoint">SPARQL-endpoint</a>
				<a class="nav-link js-scroll-trigger" href="http://localhost:8080/spring-mvc-example/#resources">Resources</a>
				<a class="nav-link js-scroll-trigger" href="http://localhost:8080/spring-mvc-example/#contact">Contact</a>
			</div>
		</div>
	</nav>
	<h2>Search Endpoint - ${searchType}</h2>
	<div align="center">
		<form action="search" method="post">
			<select name="select_multiple">
				<option value="DiseaseName">DiseaseName</option>
				<option value="DOID">DOID</option>
				<option value="chemicalName">chemicalName</option>
				<option value="CAS">CAS</option>
				<option value="tissue">tissue</option>
				<option value="tumorType">tumorType</option>
			</select> <input type="text" name="searchTarget" value="${searchTarget}">
			<input type="submit" value="search">
		</form>
	</div>
	[Search Result] - ${searchTarget}
	<table border=1>
		<tr>
			<td>experiment</td>
			<td>chemical</td>
			<td>dosage</td>
			<td>species</td>
			<td>Disease Ontology Info</td>
			<td>${dynamicColumn}</td>
			<td>reference</td>
		</tr>
		<c:forEach items="${lists}" var="list">
			<tr>
				<td><a href="#">${list.experiment}</a></td>
				<td>${list.chemicalName}</td>
				<td>${list.dosage}</td>
				<td>${list.species}</td>
				<td>${list.doInfo}</td>
				<td>${list.geneSymbol}</td>
				<td>${list.reference}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
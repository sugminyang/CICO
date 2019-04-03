<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Chemical Induced CArcinogenesis TO System
	Organisms(CICATOSO)</title>

<!-- Custom fonts for this template -->
<link
	href="https://fonts.googleapis.com/css?family=Saira+Extra+Condensed:100,200,300,400,500,600,700,800,900"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/resources/css/style.min.css"
	rel="stylesheet" type="text/css">
</head>
<body id="page-top">

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top"
		id="sideNav">
		<a class="navbar-brand js-scroll-trigger" href="#page-top"> <span
			class="d-block d-lg-none"></span> <img
			src="http://bike.snu.ac.kr/sites/default/files/images/bike-intro-logo-400.jpg"
			class="logo" width="200" height="100">
		</a>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#about">About</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#search-point">Search-point</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#sparql-endpoint">SPARQL-endpoint</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#resources">Resources</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#contact">Contact</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid p-0">
		<section class="resume-section p-3 p-lg-5 d-flex d-column" id="about">
			<div class="my-auto">
				<h2 class="mb-2">CICO: Chemical Induced Carcinogenesis Ontology</h2>
				<div class="mb-2">
					<sup>*</sup>This work is partially supported by IITP.
				</div>
				<br />
				<div class="authorName">
					Authors: Sungmin Yang, Hyunwhan Joe, Sungkwon Yang, Hong-Gee Kim<br />
					<b><a href="http://bike.snu.ac.kr/">BIKE Biomedical
							Knowledge Engineering Laboratory</a>, <a href="http://snu.ac.kr/">Seoul
							National University</a></b>
				</div>

				<article class="mb-5">
					<content>
					<h3>Publications</h3>
					<ul>
						<li><strong>Semantic Navigation of Disease-Specific
								Pathways: The Case of Non-small Cell Lung Cancer (NSCLC)</strong>,
							Semantic Technology: 8th Joint International Conference, JIST2018
							<a
							href="https://link.springer.com/chapter/10.1007/978-3-030-04284-4_27"></a></li>
					</ul>
					</content>

				</article>


				<h3>Abstract</h3>
				<p class="mb-5">
				<blockquote>In vivo experiments have had a great impact
					on the development of biomedicine, and as a result, there are a
					variety of biochemical data. Standardization and ontology design
					were carried out for the systematic management and effective
					sharing of these data. The Experimental Factor Ontology(EFO),
					Disease Ontology(DO), Gene Ontology(GO), Chemical Entities of
					Biological Interest(ChEBI) are examples of representative
					biomedical and biochemical ontologies. However, these ontologies
					are not enough to provide practical assistance to researchers
					conducting in vivo studies. Researchers conducting animal
					experiments want the animals to have a disease that fits their
					research interests. In the experimental design, the generation of
					disease causes considerable time and research costs. In this paper,
					we introduce services that support animal experiment design. The
					main idea of this research is to collect experimental data about
					chemical substances that cause cancer, make a new ontology based on
					this data, and link it with the Disease Ontology. The web service
					of this study consists of 6 search categories (Disease Name, DOID,
					Chemical Name, CAS, Tissue, Tumor). The search results include the
					name of the chemical that can cause carcinogenesis, the dose of
					use, mouse genes associated with the cancer, and other additional
					information. Our research has focused largely on two aspects. The
					first is to create a new ontology that interlinks with other
					biomedical ontologies. The second is to provide practical knowledge
					to researchers conducting in vivo experiments. In conclusion, our
					research is provided in the form of a web service, which makes it
					easy to use the SPARQL endpoint.</blockquote>
				</p>
			</div>
		</section>

		<section class="resume-section p-3 p-lg-5 d-flex flex-column"
			id="search-point">
			<div class="search">
				<h2>Search Endpoint - ${searchType}</h2>
				<div align="center">
					<form action="search#search-point" method="post">
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
			</div>
		</section>

		<section class="resume-section p-3 p-lg-5 d-flex flex-column"
			id="sparql-endpoint">
			<div class="snorql">
				<div class="section">
					<h2>SPARQL Endpoint</h2>
					<p>SPARQL clients can query the database at this SPARQL
						endpoint:</p>
					<a href="http://147.47.41.92:2020/snorql/">
						<p style="text-align: center">
							<strong>SPARQL Endpoint</strong>
						</p>
					</a>
					<p>
						The database can also be explored using <a
							href="http://147.47.41.92:2020/snorql/">this AJAX-based
							SPARQL Explorer</a>.
					</p>
				</div>
			</div>
		</section>

		<section class="resume-section p-3 p-lg-5 d-flex flex-column"
			id="resources">
			<div class="my-auto">
				<h2 class="mb-5">Resources</h2>

				<div class="subheading mb-3">Ontologies</div>
				<div class="list-group">
					<a href="http://disease-ontology.org/" class="list-group-item">Disease
						Ontology</a> <a
						href="http://www.informatics.jax.org/humanDisease.shtml"
						class="list-group-item">Human-Mouse Diease Connection</a> <br />
					<br />
				</div>

				<div class="subheading mb-3">Data</div>
				<div class="list-group">
					<a href="https://toxnet.nlm.nih.gov/cpdb/" class="list-group-item">
						The Carcinogenic Potency Database (CPDB)</a> <a
						href="./resources/data/HMDC_mappedWithDOIDs.csv"
						class="list-group-item">Human-Mouse Disease Connection mapped
						DOID</a> <br /> <br />
				</div>

				<div class="subheading mb-3">Source Code</div>
				<div class="list-group">
					<a href="https://github.com/sugminyang/CISCAD/tree/master/CPDB"
						class="list-group-item">GitHub
						project(https://github.com/sugminyang/CISCAD/tree/master/CPDB)</a> <br />
					<br />
				</div>

				<div class="subheading mb-3">tables</div>
				<div class="list-group">
					<strong><a
						href="http://147.47.41.92:2020/directory/chemicals"
						class="list-group-item">chemicals</a></strong> <strong><a
						href="http://147.47.41.92:2020/directory/cpdb_route"
						class="list-group-item">cpdb_route</a></strong> <strong><a
						href="http://147.47.41.92:2020/directory/cpdb_strain"
						class="list-group-item">cpdb_strain</a></strong> <strong><a
						href="http://147.47.41.92:2020/directory/cpdb_tissue"
						class="list-group-item">cpdb_tissue</a></strong> <strong><a
						href="http://147.47.41.92:2020/directory/cpdb_tumor"
						class="list-group-item">cpdb_tumor</a></strong> <strong><a
						href="http://147.47.41.92:2020/directory/effect"
						class="list-group-item">effect</a></strong> <strong><a
						href="http://147.47.41.92:2020/directory/experiment"
						class="list-group-item">experiment</a></strong> <strong><a
						href="http://147.47.41.92:2020/directory/hmdc"
						class="list-group-item">hmdc</a></strong> <strong><a
						href="http://147.47.41.92:2020/directory/models"
						class="list-group-item">models</a></strong>
				</div>
			</div>
		</section>

		<section class="resume-section p-3 p-lg-5 d-flex flex-column"
			id="contact">
			<div class="my-auto">
				<h2 class="mb-5">Contact</h2>
				<p>
					If you have any questions about this work, please contact <a
						href="mailto:syang90@snu.ac.kr">Chemical Induced
						CArcinogenesis TO System Organisms(CICATOSO) Ontology Developers</a>.
					Generated by Sungmin Yang, <a href="http://bike.snu.ac.kr/">BIKE
						lab</a>, Seoul National University
				</p>
			</div>
		</section>
	</div>

</body>
</html>
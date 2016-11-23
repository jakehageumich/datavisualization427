<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Maven + Spring MVC + @JavaConfig</title>

<c:url value="/resources/core/css/hello.css" var="coreCss" />
<c:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
<style>
.bar {
	fill: steelblue;
}

.bar:hover {
	fill: brown;
}

.axis--x path {
	display: none;
}
</style>
<script src="http://d3js.org/d3.v3.min.js"></script>
<script src="http://dimplejs.org/dist/dimple.v2.1.6.min.js"></script>
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="/spring3/home">SIP_Project_T</a>
		</div>
	</div>
</nav>
<body>

	<div class="jumbotron">

		<div class="container">
			<h1>${title}</h1>
			<p>
				<c:if test="${not empty name}">
			${name} Bar Charts
		</c:if>

				<c:if test="${empty name}">
			NAME NOT FOUND Bar Charts
		</c:if>
			</p>
			<div class="container">
				<script type="text/javascript">
    var svg = dimple.newSvg("body", 500, 400);
    var data = [
      { "Word":"test", "Awesomeness":1000 },
      { "Word":"World", "Awesomeness":1500 }
    ];
    var chart = new dimple.chart(svg, data);
    chart.addCategoryAxis("x", "Word");
    chart.addMeasureAxis("y", "Awesomeness");
    chart.addSeries(null, dimple.plot.bar);
    chart.draw();
  </script>

			</div>
			<p>
				<a class="btn btn-primary btn-lg" href="/spring3/home" role="button">Home</a>
			<table cellpadding="10" border="2" cellspacing="5">
				<tr align="center">
					<th>Tenant</th>
					<th>Publish Status</th>
					<th>Count</th>
				</tr>
				<c:if test="${tenantData==null}">
					<tr align="center">
						<td>WIP</td>
						<td>WIP</td>
						<td>WIP</td>
					</tr>
				</c:if>
				<c:if test="${tenantData!=null}">
					<c:forEach items="${tenantData}" var="p">
						<tr align="center">
							<td>${p.tenant}</td>
							<td>${p.status}</td>
							<td>${p.count}</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			</p>
		</div>
	</div>


	<div class="container">
		<hr>
		<footer>
			<p>&copy; jakehage@umich.edu 2016</p>
		</footer>
	</div>

	<c:url value="/resources/core/css/hello.js" var="coreJs" />
	<c:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />

	<script src="${coreJs}"></script>
	<script src="${bootstrapJs}"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>
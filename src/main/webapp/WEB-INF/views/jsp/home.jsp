<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Maven + Spring MVC + @JavaConfig</title>

<c:url value="/resources/core/css/hello.css" var="coreCss" />
<c:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="https://www.icpsr.umich.edu/icpsrweb/"
				target="blank">ICPSR</a>
		</div>
	</div>
</nav>

<div class="jumbotron">
	<div class="container">
		<h1>${title}</h1>
		<p>
			<c:if test="${not empty name}">
			Hello ${name}
		</c:if>

			<c:if test="${empty name}">
			SIP_Project_T
		</c:if>
		</p>
		<p>
			<a class="btn btn-primary btn-lg" href="<c:url value='/viewAll'/>">View
				all</a>
		</p>
	</div>
</div>

<div class="container">
	<table cellpadding="10" border="2" cellspacing="5">
		<tr align="center">
			<th>Tenant</th>
			<th>Publish Status</th>
			<th>Count</th>
		</tr>
		<c:if test="${table==null}">
			<tr align="center">
				<td>NULL</td>
				<td>Not found</td>
				<td>Not found</td>
			</tr>
		</c:if>
		<c:if test="${table!=null}">
			<c:forEach items="${table}" var="p">
				<tr align="center">
					<td>${p.tenant}</td>
					<td>${p.status}</td>
					<td>${p.count}</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</div>

<div class="container">

	<div class="row">

		<c:forEach items="${tenantNames}" var="name">
			<div class="col-md-4">
				<h2>${name}</h2>
				<p>WIP</p>
				<p>
					<a class="btn btn-default"
						href="<c:url value='/${name}/viewData'/>"> View details </a>
				</p>
			</div>
		</c:forEach>

	</div>

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
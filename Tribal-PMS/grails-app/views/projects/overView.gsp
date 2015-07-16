<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="projects"/>
<title>Browse/Create/Edit Projects</title>
</head>
<body>

	<div class="col-md-10">
		<button type="button" class="btn btn-primary addnew">Add new Project</button>
	</div> <!-- /div -->
	<g:if test="${projects}">
		<div class="col-md-12">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Name</th>
							<th>Code</th>
							<th>Tech. Lead</th>
							<th>Manager</th>
							<th>Delivery Date</th>
							<th>Current Phase</th>
							<th>Priority</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${projects}" var="${p}">
							<tr>
								<td>${p?.name}</td>
								<td>${p?.code}</td>
								<td><g:personForProject project="${p}" lead="true" /></td>
								<td><g:personForProject project="${p}" manager="true"/></td>
								<td>${p?.deliveryDate?.format('yyyy-MM-dd HH:mm')}</td>
								<td>${p?.phase}</td>
								<td>${p?.priority}</td>
							</tr>
						</g:each>
					</tbody>
				</table> <!-- /table -->
			</div> <!-- /table-responsive -->
		</div> <!-- /div -->
	</g:if>
	<g:else>
		<p>No projects</p>
	</g:else>
</body>
</html>
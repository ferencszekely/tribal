<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.tribal.Projects" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="projects"/>
<title>Managers</title>
</head>
<body>
	<div class="row">
		<h3>Manager List</h3>
		<div class="col-md-12">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>Username</th>
							<th>Firstname</th>
							<th>Lastname</th>
							<th>Projects Assigned</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${t}" var="${n}">
							<tr>
								<td>${n?.id}</td>
								<td>${n?.account?.username}</td>
								<td>${n?.firstName}</td>
								<td>${n?.lastName}</td>
								<td><g:each in="${Projects.findAllByTechLead(n?.account)}" var="p" status="i">
										<span>
											<g:if test="${i > 0}">
												<span> | </span>
											</g:if>
											<g:link action="edit" id="${p?.id}">${p?.name}</g:link>
										</span>
									</g:each>
								</td>
							</tr>
						</g:each>
					</tbody>
				</table> 
			</div> <!-- /table-responsive -->
		</div> <!-- /col-md-12 -->
	</div> <!-- /row -->
</body>
</html>
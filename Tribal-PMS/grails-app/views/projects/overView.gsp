<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="projects"/>
<title>Browse/Create/Edit Projects</title>
</head>
<body>

	<div class="col-md-10 addnew">
		<button type="button" class="btn btn-primary" id="add">Add new Project</button>
	</div> <!-- /div -->
	<div class="row addNewProject">
		<div class="col-md-6">
			<g:form action="addNew" class="form">
				<div class="form-group">
					<label for="name">Project Name</label>
					<input type="text" class="form-control" id="name" name="name" placeholder="Enter the Alphanumeric project name" required>
				</div> <!-- /form-group -->
				<div class="form-group">
					<label for="code">Project Code</label>
					<input type="text" class="form-control" id="code" name="code" placeholder="Enter the Alphanumeric project code" required>
				</div> <!-- /form-group -->
				<div class="form-group">
					<label for="techlead">Technical Lead</label>
					<g:select from="${t}" optionKey="account" optionValue="${{it.firstName +' '+ it.lastName}}" name="techLead" class="form-control"/>
				</div> <!-- /form-group -->
				<div class="form-group">
					<label for="techlead">Project Manager</label>
					<g:select from="${m}" optionKey="account" optionValue="${{it.firstName +' '+ it.lastName}}" name="manager" class="form-control"/>
				</div> <!-- /form-group -->
				<div class="form-group">
					<label for="date">Delivery Date</label>
					<input type="text" class="form-control" name="date" id="date" required>
				</div> <!-- /form-group -->
				<div class="form-group">
					<label for="phase">Phase</label>
					<g:select from="${phases}" name="phase" class="form-control"/>
				</div> <!-- /form-group -->
				<div class="form-group">
					<label for="prio">Priority</label>
					<input type="number" class="form-control" name="priority" id="priority" placeholder="Please enter a number. See the project list below for priorities." required>
					<p class="inUse" id="inUse">Error: Priority is either used by another project or it is not a valid number!</p>
				</div> <!-- /form-group -->
				<div class="form-group">
					<label for="desc">Description</label>
					<textarea class="form-control" rows="4" name="desc" placeholder="NOT required."></textarea>
				</div> <!-- /form-group -->
				<div class="form-group">
					<button class="btn btn-info" type="submit" id="goProject">Submit</button>
				</div> <!-- /form-group -->
			</g:form>
		</div> <!-- /col-md -->
	</div> <!-- /nested row -->
	<g:if test="${flash.success}">
		<div class="col-md-12 notifyP">
			<div class="alert alert-success" role="alert">
				<p>Project successfully added. See below.</p>
			</div> <!-- /alert -->
		</div> <!-- /col-md-12 -->
	</g:if>
	<g:if test="${flash.error}">
		<div class="col-md-12 notifyP">
			<div class=" row alert alert-danger" role="alert">
		  		<span class="" aria-hidden="true"></span>
		  		Whoops, something went wrong. Please try again! (Make sure to use Alphanumeric for both project name and code - Meaning letters and numbers only and no spaces!)
			</div> <!-- /alert -->
		</div> <!-- /col-md-12 -->
	</g:if>
	<g:if test="${projects}">
		<div class="col-md-12 pInfos">
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
							<th></th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${projects}" var="${p}">
							<tr>
								<td>${p?.name}</td>
								<td>${p?.code}</td>
								<td><g:personForProject project="${p}" lead="true" /></td>
								<td><g:personForProject project="${p}" manager="true"/></td>
								<td>${p?.deliveryDate?.format('yyyy-MM-dd')}</td>
								<td>${p?.phase}</td>
								<td>${p?.priority}</td>
								<td><g:link action="edit" id="${p?.id}"><button type="button" class="btn btn-primary">Edit</button></g:link></td>
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
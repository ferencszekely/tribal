<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="projects"/>
<title>Edit Project</title>
</head>
<body>
 	<div class="row">
 		<g:if test="${flash.error}">
  			<div class="col-md-6">
  				<div class=" row alert alert-danger" role="alert">
  					<span>No Project found!</span>
  				</div>
  			</div> <!-- /col-md -->
  		</g:if>
  		<g:else>
	  		<div class="col-md-6">
	  			<g:form action="submitEdit" class="form">
	  				<div class="form-group">
						<label for="name">Project Name</label>
						<input type="text" class="form-control" id="name" name="name" value="${p?.name}" required>
					</div> <!-- /form-group -->
					<div class="form-group">
						<label for="code">Project Code</label>
						<input type="text" class="form-control" id="code" name="code" value="${p?.code}" required>
					</div> <!-- /form-group -->
					<div class="form-group">
						<label for="techlead">Technical Lead</label>
						<g:select from="${t}" optionKey="account" optionValue="${{it.firstName +' '+ it.lastName}}" name="techLead" class="form-control" value="${lead?.getFullName()}" noSelection="['':'Please Select...']"/>
					</div> <!-- /form-group -->
					<div class="form-group">
						<label for="techlead">Project Manager</label>
						<g:select from="${m}" optionKey="account" optionValue="${{it.firstName +' '+ it.lastName}}" name="manager" class="form-control" value="${manager?.getFullName()}" noSelection="['':'Please Select...']"/>
					</div> <!-- /form-group -->
					<div class="form-group">
						<label for="date">Delivery Date</label>
						<input type="text" class="form-control" name="date" id="date" value="${p?.deliveryDate?.format('yyyy-MM-dd')}" required>
					</div> <!-- /form-group -->
					<div class="form-group">
						<label for="phase">Phase</label>
						<g:select from="${phases}" name="phase" class="form-control"/ value="${p?.phase}"/>
					</div> <!-- /form-group -->
					<div class="form-group">
						<label for="prio">Priority</label>
						<input type="number" class="form-control" name="priority" id="priority" value="${p?.priority}" required>
						<p class="inUse" id="inUse">Error: Priority is either used by another project or it is not a valid number!</p>
					</div> <!-- /form-group -->
					<div class="form-group">
						<label for="desc">Description</label>
						<textarea class="form-control" rows="4" name="desc" placeholder="NOT required.">${p?.description}</textarea>
					</div> <!-- /form-group -->
					<div class="form-group">
						<button class="btn btn-info" type="submit" id="goProject">Submit</button>
					</div> <!-- /form-group -->
	  			</g:form>
	  		</div> <!-- /col-md -->
  		</g:else>
  	</div> <!-- /row -->
</body>
</html>
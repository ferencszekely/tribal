<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="projects"/>
<title>Project Management System</title>
</head>
<body>
  <div class="welcome text-center">
  		<h3>Welcome ${u.getFullName()}!</h3>
  		<p>Currently you have <b>${projects?.size()}</b> project(s) that you can manage. See <g:link action="overView">Projects</g:link> for more details.</p>
  </div>
</body>
</html>
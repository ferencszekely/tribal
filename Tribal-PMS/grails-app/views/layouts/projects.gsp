<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Tribal"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
  		<asset:stylesheet src="bootstrap.min.css"/> 
  		<asset:stylesheet src="simple-sidebar.css"/>
  		<asset:stylesheet src="projects.css"/>
  		<asset:stylesheet src="jquery-ui.min.css"/>
  		<asset:javascript src="jquery.js"/>
		<asset:javascript src="bootstrap.min.js"/>
		<asset:javascript src="projects.js"/>
		<asset:javascript src="jquery-ui.min.js"/>
		
		<g:layoutHead/>
	</head>
	<body>
		
    <div id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <g:link action="index" controller="projects">
                        Project Management
                    </g:link>
                </li>
                <li>
                    <g:link action="overView" controller="projects">Projects</g:link>
                </li>
                <li>
                    <g:link action="managers" controller="projects">Managers</g:link>
                </li>
                <li>
                    <g:link action="techLeads" controller="projects">Technical Leads</g:link>
                </li>
                <li>
                    <a href="/j_spring_security_logout">Logout</a>
                </li>
                
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <g:layoutBody/>
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <!-- <script src="js/jquery.js"></script>  -->

    <!-- Bootstrap Core JavaScript -->
    <!-- <script src="js/bootstrap.min.js"></script>  -->

   	
	</body>
</html>

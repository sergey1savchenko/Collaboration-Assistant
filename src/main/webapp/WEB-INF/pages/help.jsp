<%@include file="header.jsp"%>
<div class="container">
	<div class="wrapper">
		<div class="jumbotron">
		
			<img src="<c:url value="/resources/img/logo.png"/>" class="img-rounded main-logo" />
			<h2 class="welcome-header-help">About Collaboration Assistant</h2>
			
				<span class="text-about">Collaboration Assistant is an application dedicated to streamline 
				the process of organizing, maintaining and keeping track of student training projects, 
				set up by IT companies in order to test their various skills and search for suitable employees.
				 It was developed during the practice of NetCracker company by team Loki in 2016.</span>
				 
				<span class="text-about">Collaboration Assistant first of all allows to establish new projects 
				 for the groups of selected students, arrange student meetings, assess their progress 
				 throughout the project development phase and conclude it with possible job offer.  The system
				  provides easy shared access for numerous users.</span>
				
				<img src="<c:url value="/resources/img/img_team.png"/>" class="img-rounded main-logo" />
				
			<h2 class="welcome-header-help">Questions you may have</h2>	
				
				<div class="panel-group" id="accordion">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">How can I change my email or password? </a>
        </h4>
      </div>
      <div id="collapse1" class="panel-collapse collapse in">
        <div class="panel-body">You need to contact administrator in order to edit your profile. </div>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">On what basis does the evaluation occur?</a>
        </h4>
      </div>
      <div id="collapse2" class="panel-collapse collapse">
        <div class="panel-body">Meeting evaluation criterions are set up individually for each project. Project evaluation is based on
         more general criterions than meeting.</div>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">What should I do if I want to quit the project?</a>
        </h4>
      </div>
      <div id="collapse3" class="panel-collapse collapse">
        <div class="panel-body">Contact administrator to change your project status.</div>
      </div>
    </div>
  </div>
			
		<span class="text-about">Didn't find the answer? Please, contact <a href="mailto:">administrator.</a> </span>	
			
				
		</div>
	</div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
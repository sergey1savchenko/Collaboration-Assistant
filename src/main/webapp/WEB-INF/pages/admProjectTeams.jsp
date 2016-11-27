<%@include file="header.jsp"%>
<div class="container">
	<div class="wrapper">

			<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapse1">How can I change my email or password? </a>
						</h4>
					</div>
					<div id="collapse1" class="panel-collapse collapse in">
						<div class="panel-body">You need to contact administrator in
							order to edit your profile.</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapse2">On what basis does the evaluation occur?</a>
						</h4>
					</div>
					<div id="collapse2" class="panel-collapse collapse">
						<div class="panel-body">Meeting evaluation criterions are
							set up individually for each project. Project evaluation is based
							on more general criterions than meeting.</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapse3">What should I do if I want to quit the
								project?</a>
						</h4>
					</div>
					<div id="collapse3" class="panel-collapse collapse">
						<div class="panel-body">Contact administrator to change your
							project status.</div>
					</div>
				</div>
			</div>
			<span class="text-about">Didn't find the answer? Please,
				contact <a href="mailto:">administrator.</a>
			</span>
		
	</div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
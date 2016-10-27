<%@include file="header.jsp"%>
<div class="container">
	<div class="wrapper">
		<form name='login' action="j_spring_security_check" method='POST'
			class="form-signin">
			<h3 class="form-signin-heading">Sign in to get started</h3>
			<hr class="colorgraph">
			<br> <input type="text" class="form-control" name="username"
				placeholder="username" /> <input type="password"
				class="form-control" name="password" placeholder="password" />
			<button class="btn btn-large btn-success btn-block" name="Submit"
				value="Login" type="Submit">Login</button>
		</form>
	</div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
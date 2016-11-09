<%@include file="header.jsp"%>
<div class="container">
	<div class="wrapper">
	<div class="login-form">
	<form name='login' action="j_spring_security_check" method='POST'>
		 
     <img src="<c:url value="/resources/img/logo.png"/>" class="img-rounded logo-login" />
     <div class="form-group ">
       <input type="text" class="form-control" placeholder="Username " id="UserName">
       <i class="fa fa-user"></i>
     </div>
     <div class="form-group log-status">
       <input type="password" class="form-control" placeholder="Password" id="Passwod">
       <i class="fa fa-lock"></i>
     </div>
      <span class="alert">Invalid Credentials</span>
      <a class="link" href="#">Forgot your password?</a>
     <button type="Submit" class="btn btn-primary btn-block" >Log in</button>
     </form>
    
   </div>
	</div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
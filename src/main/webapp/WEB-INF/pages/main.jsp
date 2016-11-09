<%@include file="header.jsp"%>
<div class="container">
	<div class="wrapper">
	<security:authorize access="isAnonymous()">
		<div class="jumbotron">
		
			<img src="<c:url value="/resources/img/logo.png"/>" class="img-rounded main-logo" />
			<h2 class="welcome-header">Welcome to Collaboration Assistant!</h2>
			
				<p class="text">Please log in to get started</p>
				<div class="text-center">
				<a class="btn btn-large btn-primary btn-log-in" href="<c:url value="/login"/>">Log
					In</a>
					</div>
		</div>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
             
             <div class="jumbotron">
             	<h2 class="welcome-header inner">Welcome,  ${pageContext.request.userPrincipal.name}!</h2>
             	<security:authorize access="hasRole('ROLE_ADMIN')">
					 <div class="admin-buttons">
		                <ul class="list">
		                    <li class="element"><button class="btn btn-primary button-in-list">Create new project</button> </li>
		                    <li class="element"><button class="btn btn-primary button-in-list">Action</button> </li>
		                    <li class="element"><button class="btn btn-primary button-in-list">Action</button> </li>
		                </ul>
           			 </div>		        			 
           			 	<h2 class="inner">All projects</h2>
           			 	<table class="table table-bordered table-hover projects-table">
						  <thead>
						    <tr>
						      <th>#   <i class="fa fa-sort" aria-hidden="true"></i></th>
						      <th>Name   <i class="fa fa-sort" aria-hidden="true"></i></th>
						      <th>Start date   <i class="fa fa-sort" aria-hidden="true"></i></th>
						      <th>End date   <i class="fa fa-sort" aria-hidden="true"></i></th>
						      <th>University   <i class="fa fa-sort" aria-hidden="true"></i></th>
						    </tr>
						  </thead>
						  <tbody>
						    <tr>
						      <th scope="row">1</th>
						      <td><a class="project-name" href="#">Project1</a></td>
						      <td>23.01.2016</td>
						      <td>23.03.2016</td>
						      <td>KMA</td>
						    </tr>
						    <tr>
						      <th scope="row">2</th>
						      <td><a class="project-name" href="#">Project2</a></td>
						      <td>23.01.2017</td>
						      <td>23.03.2017</td>
						      <td>KMA</td>
						    </tr>
						    <tr>
						      <th scope="row">3</th>
						      <td><a class="project-name" href="#">Project3</a></td>
						      <td>23.01.2018</td>
						      <td>23.03.2018</td>
						      <td>KMA</td>
						    </tr>
						    <tr>
						      <th scope="row">4</th>
						      <td><a class="project-name" href="#">Project3</a></td>
						      <td>23.01.2018</td>
						      <td>23.03.2018</td>
						      <td>KMA</td>
						    </tr>
						    <tr>
						      <th scope="row">5</th>
						      <td><a class="project-name" href="#">Project3</a></td>
						      <td>23.01.2018</td>
						      <td>23.03.2018</td>
						      <td>KMA</td>
						    </tr>
						    <tr>
						      <th scope="row">6</th>
						      <td><a class="project-name" href="#">Project3</a></td>
						      <td>23.01.2018</td>
						      <td>23.03.2018</td>
						      <td>KMA</td>
						    </tr>
						    <tr>
						      <th scope="row">7</th>
						      <td><a class="project-name" href="#">Project3</a></td>
						      <td>23.01.2018</td>
						      <td>23.03.2018</td>
						      <td>KMA</td>
						    </tr>
						    <tr>
						      <th scope="row">8</th>
						      <td><a class="project-name" href="#">Project3</a></td>
						      <td>23.01.2018</td>
						      <td>23.03.2018</td>
						      <td>KMA</td>
						    </tr>
						    <tr>
						      <th scope="row">9</th>
						      <td><a class="project-name" href="#">Project3</a></td>
						      <td>23.01.2018</td>
						      <td>23.03.2018</td>
						      <td>KMA</td>
						    </tr>
						    <tr>
						      <th scope="row">10</th>
						      <td><a class="project-name" href="#">Project3</a></td>
						      <td>23.01.2018</td>
						      <td>23.03.2018</td>
						      <td>KMA</td>
						    </tr>
						  </tbody>
						</table>
           			 <div class="next-prev-buttons">
		                <ul class="list">
		                    <li class="element"><button class="btn btn-primary button-in-list" disabled>
		                    <i class="fa fa-long-arrow-left" aria-hidden="true"></i>Previous 10</button> 
		                    </li>
		                    <li class="element"><button class="btn btn-primary button-in-list">
		                    Next 10 <i class="fa fa-long-arrow-right" aria-hidden="true"></i></button> 
		                    </li>
		                </ul>
           			 </div>		
           			 
				</security:authorize>
				<security:authorize access="hasRole('ROLE_STUDENT')">
						<h2 class="inner">My projects</h2>
           			 	<table class="table table-bordered table-hover projects-table">
						  <thead>
						    <tr>
						      <th>#   <i class="fa fa-sort" aria-hidden="true"></i></th>
						      <th>Name   <i class="fa fa-sort" aria-hidden="true"></i></th>
						      <th>Start date   <i class="fa fa-sort" aria-hidden="true"></i></th>
						      <th>End date   <i class="fa fa-sort" aria-hidden="true"></i></th>
						      <th>University   <i class="fa fa-sort" aria-hidden="true"></i></th>
						    </tr>
						  </thead>
						  <tbody>
						    <tr>
						      <th scope="row">1</th>
						      <td><a class="project-name" href="#">Project1</a></td>
						      <td>23.01.2016</td>
						      <td>23.03.2016</td>
						      <td>KMA</td>
						    </tr>
						    <tr>
						      <th scope="row">2</th>
						      <td><a class="project-name" href="#">Project2</a></td>
						      <td>23.01.2017</td>
						      <td>23.03.2017</td>
						      <td>KMA</td>
						    </tr>
						    <tr>
						      <th scope="row">3</th>
						      <td><a class="project-name" href="#">Project3</a></td>
						      <td>23.01.2018</td>
						      <td>23.03.2018</td>
						      <td>KMA</td>
						    </tr>
						  </tbody>
						</table>
           			 <div class="next-prev-buttons">
		                <ul class="list">
		                    <li class="element"><button class="btn btn-primary button-in-list" disabled>
		                    <i class="fa fa-long-arrow-left" aria-hidden="true"></i>Previous 10</button> 
		                    </li>
		                    <li class="element"><button class="btn btn-primary button-in-list" disabled>
		                    Next 10 <i class="fa fa-long-arrow-right" aria-hidden="true"></i></button> 
		                    </li>
		                </ul>
           			 </div>		
				
				</security:authorize>
             </div>           	
                        	
                        	
        </security:authorize>
		
	</div>

</div>
	<%@include file="footer.jsp"%>
</body>
</html>
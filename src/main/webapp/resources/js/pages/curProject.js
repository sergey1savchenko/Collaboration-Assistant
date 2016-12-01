////////////////////////// TABLE WITH STUDENTS ///////////////////////////////////
function showStudents(id){
$(function () {	
	$("#teamStudents").jsGrid({											//!
    	
        height: "350px",
        width: "100%",
        
    	
        filtering: true,
        editing: false,
        sorting: true,
        paging: false,
        autoload: true,

        controller: {
            loadData: function () {
                var deferred = $.Deferred();
                $.ajax({													//GET
                    url: "/CA-Project/curator/api/teamStudents/" + id,				// !
                    dataType: 'json'
                }).done(function (data) {
                    deferred.resolve(data);
                }).fail(function () {
                    WebUtils.show('Error to load data');
                    deferred.reject("loading error");
                });
                return deferred.promise();
            },
            
        },
        
        fields: [															//!!
        			// from DB
            // photo {name: "u_id", type: "text", title: "user id", validate: "required"},
        	//{name: "u_id", type: "text", title: "user id", validate: "required"},
        	{name: "id", type: 'link', url: 'CA-Project/curator/studentPE/{id}', width: 50, title: 'Project evaluation'},
        	{name: "firstName", type: "text", width: 50, title: "First name", validate: "required"},
            {name: "secondName", type: "text", width: 50, title: "Second name", validate: "required"},
            {name: "lastName", type: "text", width: 50, title: "Last name", validate: "required"},
            {name: "email", type: "text", title: "email", validate: "required"},
            {name: "isActive", type: "checkbox", width: 35, title: " Is active", validate: "required"},
            {name: "university", type: "text", width: 50, title: "University", validate: "required"},
            {name: "status", type: "text", width: 50, title: "Status", validate: "required"}
            //{type: "control", editButton: false, deleteButton: true, modeSwitchButton: false, clearFilterButton: false}

        ]

    });
    
});

}

////////////////////////// TABLE WITH CURATORS ///////////////////////////////////
function showCurators(id){
$(function () {	
	$("#teamCurators").jsGrid({											//!
    	
        height: "250px",
        width: "100%",
        
    	
        filtering: true,
        editing: false,
        sorting: true,
        paging: false,
        autoload: true,

        controller: {
            loadData: function () {
                var deferred = $.Deferred();
                $.ajax({													//GET
                    url: "/CA-Project/curator/api/teamCurators/" + id,				// !
                    dataType: 'json'
                }).done(function (data) {
                    deferred.resolve(data);
                }).fail(function () {
                    WebUtils.show('Error to load data');
                    deferred.reject("loading error");
                });
                return deferred.promise();
            },


        },
        
        fields: [															//!!
        			// from DB
            //{name: "u_id", type: "text", title: "user id", validate: "required"},
            {name: "firstName", type: "text", title: "First name", validate: "required"},
            {name: "secondName", type: "text", title: "Second name", validate: "required"},
            {name: "lastName", type: "text", title: "Last name", validate: "required"},
            {name: "email", type: "text", title: "email", validate: "required"}
            //{type: "control", editButton: false, deleteButton: true, modeSwitchButton: false, clearFilterButton: false}

        ]

    });
    
});

}

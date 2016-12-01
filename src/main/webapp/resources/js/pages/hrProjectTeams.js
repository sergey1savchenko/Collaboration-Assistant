function showTables(id){
	var teamID = id;
	var teamCurators = "teamCurators"+teamID;					// identifying div for the table
	var teamStudents = "teamStudents"+teamID;					// identifying div for the table
	//alert(teamID);
////////////////////////// TABLE WITH CURATORS ///////////////////////////////////
$(function () {	
	$('#'+teamCurators).jsGrid({											//!
    	
        height: "200px",
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
                    url: "/CA-Project/hr/api/teamCurators/" + teamID,				// !
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
            {name: "email", type: "text", title: "email", validate: "required"},
            {name: "first_name", type: "text", title: "First name", validate: "required"},
            {name: "second_name", type: "text", title: "Second name", validate: "required"},
            {name: "last_name", type: "text", title: "Last name", validate: "required"}
            //{type: "control", editButton: false, deleteButton: true, modeSwitchButton: false, clearFilterButton: false}

        ]

    });
    
});
////////////////////////// TABLE WITH STUDENTS ///////////////////////////////////
$(function () {	
	$('#'+teamStudents).jsGrid({											//!
    	
        height: "280px",
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
                    url: "/CA-Project/hr/api/teamStudents/" + teamID,				// !
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
        	{name: "u_id", type: 'link', url: 'CA-Project/hr/student/{u_id}', width: 60, title: 'Evaluation'},
            {name: "email", type: "text", title: "email", validate: "required"},
            {name: "is_active", type: "boolean", width: 40, title: " Is active", validate: "required"},
            {name: "un_title", type: "text", title: "University", validate: "required"},
            {name: "first_name", type: "text", title: "First name", validate: "required"},
            {name: "second_name", type: "text", title: "Second name", validate: "required"},
            {name: "last_name", type: "text", title: "Last name", validate: "required"}
            //{type: "control", editButton: false, deleteButton: true, modeSwitchButton: false, clearFilterButton: false}

        ]

    });
    
});

}

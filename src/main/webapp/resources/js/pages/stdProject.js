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
                    url: "/CA-Project/student/api/teamStudents/" + id,				// !
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
        	//{name: "u_id", type: 'link', url: 'CA-Project/curator/studentPE/{u_id}', width: 50, title: 'Proj. evaluation'},
        	{name: "first_name", type: "text", width: 50, title: "First name", validate: "required"},
            {name: "second_name", type: "text", width: 50, title: "Second name", validate: "required"},
            {name: "last_name", type: "text", width: 50, title: "Last name", validate: "required"},
            {name: "email", type: "text", title: "email", validate: "required"},
            {name: "is_active", type: "boolean", width: 35, title: " Is active", validate: "required"},
            {name: "un_title", type: "text", width: 50, title: "University", validate: "required"},
            {name: "status", type: "text", title: "Status", validate: "required"}
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
                    url: "/CA-Project/student/api/teamCurators/" + id,				// !
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
            {name: "first_name", type: "text", title: "First name", validate: "required"},
            {name: "second_name", type: "text", title: "Second name", validate: "required"},
            {name: "last_name", type: "text", title: "Last name", validate: "required"},
            {name: "email", type: "text", title: "email", validate: "required"}
            //{type: "control", editButton: false, deleteButton: true, modeSwitchButton: false, clearFilterButton: false}

        ]

    });
    
});

}

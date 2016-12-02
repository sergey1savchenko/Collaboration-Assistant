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
                    url: "/CA-Project/hr/api/team/" + teamID + "/curators",				// !
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
            {name: "firstName", type: "text", title: "First name", validate: "required"},
            {name: "secondName", type: "text", title: "Second name", validate: "required"},
            {name: "lastName", type: "text", title: "Last name", validate: "required"}
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
                    url: "/CA-Project/hr/api/team/" + teamID + "/students",				// !
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
        	{name: "id", type: 'link', url: 'CA-Project/hr/student/{id}', width: 60, title: 'Evaluation'},
            {name: "email", type: "text", title: "email", validate: "required"},
            {name: "firstName", type: "text", title: "First name", validate: "required"},
            {name: "secondName", type: "text", title: "Second name", validate: "required"},
            {name: "lastName", type: "text", title: "Last name", validate: "required"},
            {name: "isActive", type: "checkbox", width: 40, title: " Is active", validate: "required"},
            {name: "university", type: "text", title: "University", validate: "required"}
            //{type: "control", editButton: false, deleteButton: true, modeSwitchButton: false, clearFilterButton: false}

        ]

    });

});

}

////////////////////////// TABLE WITH STUDENTS ///////////////////////////////////
function showStudents(id){
$(function () {
	$("#teamStudents").jsGrid({											//!

        height: "350px",
        width: "100%",


        filtering: false,
        editing: false,
        sorting: true,
        paging: false,
        autoload: true,
        
        rowClick: function(args) {
        		window.location.href = '/CA-Project/curator/student/' + args.item.id;
        },
        
        controller: {
            loadData: function () {
                var deferred = $.Deferred();
                $.ajax({													//GET
                    url: "/CA-Project/curator/api/students",				// !
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
        	//{name: "id", type: 'link', url: '/CA-Project/curator/student/{id}', width: 30, title: 'Project Evaluation'},
        	{name: "firstName", type: "text", width: 50, title: "First name", validate: "required"},
            {name: "secondName", type: "text", width: 50, title: "Second name", validate: "required"},
            {name: "lastName", type: "text", width: 50, title: "Last name", validate: "required"},
            {name: "email", type: "text", title: "email", validate: "required"},
            {name: "university.title", type: "text", width: 50, title: "University", validate: "required"},
            //{name: "status.title", type: "text", width: 50, title: "Status", validate: "required"}
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


        filtering: false,
        editing: false,
        sorting: true,
        paging: false,
        autoload: true,

        controller: {
            loadData: function () {
                var deferred = $.Deferred();
                $.ajax({													//GET
                    url: "/CA-Project/curator/api/curators/",				// !
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
            {name: "firstName", type: "text", title: "First name", validate: "required"},
            {name: "secondName", type: "text", title: "Second name", validate: "required"},
            {name: "lastName", type: "text", title: "Last name", validate: "required"},
            {name: "email", type: "text", title: "email", validate: "required"}
            //{type: "control", editButton: false, deleteButton: true, modeSwitchButton: false, clearFilterButton: false}

        ]

    });

});

}

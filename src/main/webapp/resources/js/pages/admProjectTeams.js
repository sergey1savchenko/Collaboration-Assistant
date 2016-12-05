function showTables(id){
	var teamID = id;
	var teamCurators = "teamCurators"+teamID;					// identifying div for the table
	var teamStudents = "teamStudents"+teamID;					// identifying div for the table
	//alert(teamID);
////////////////////////// TABLE WITH CURATORS ///////////////////////////////////
$(function () {
	$('#'+teamCurators).jsGrid({											//!

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
                    url: "/CA-Project/admin/api/team/" + teamID + "/curators",				// !
                    dataType: 'json'
                }).done(function (data) {
                    deferred.resolve(data);
                }).fail(function () {
                    WebUtils.show('Error to load data');
                    deferred.reject("loading error");
                });
                return deferred.promise();
            },
            
            deleteItem: function (item) {
                return $.ajax({
                    method: "DELETE",
                    url: '/CA-Project/admin/api/project/'+projectId+'/curator/'+item.id
                }).fail(function () {
                    WebUtils.show('Failed to delete');
                });
            }

        },
        deleteConfirm: "Do you really want to delete curator from this team?",
        fields: [															//!!
        			// from DB
            //{name: "id", type: "text", title: "User id", validate: "required"},
            {name: "email", type: "text", title: "email", validate: "required"},
            {name: "firstName", type: "text", title: "First name", validate: "required"},
            {name: "secondName", type: "text", title: "Second name", validate: "required"},
            {name: "lastName", type: "text", title: "Last name", validate: "required"},
            {type: "control", editButton: false, deleteButton: true, modeSwitchButton: false, clearFilterButton: false}

        ]

    });

});
////////////////////////// TABLE WITH STUDENTS ///////////////////////////////////
$(function () {
	$('#'+teamStudents).jsGrid({											//!

        height: "300px",
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
                    url: "/CA-Project/admin/api/team/" + teamID + "/students",				// !
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
        	{name: "id", type: 'link', url: '/CA-Project/admin/student/{id}', width: 50, title: 'Student page'},
            {name: "email", type: "text", title: "email", validate: "required"},
            {name: "firstName", type: "text", title: "First name", validate: "required"},
            {name: "secondName", type: "text", title: "Second name", validate: "required"},
            {name: "lastName", type: "text", title: "Last name", validate: "required"},
            {name: "university.title", type: "text", title: "University", validate: "required"}
            //{type: "control", editButton: false, deleteButton: true, modeSwitchButton: false, clearFilterButton: false}

        ]

    });

});

}

///

function deleteTeam(id){
	if (confirm("Are you sure to delete this team?")) {
		//alert(id);
		$.ajax({
			method: "DELETE",
			url: "/CA-Project/admin/api/team/" + id									//!
			}).fail(function () {
				WebUtils.show('Failed to delete Team');
			});
		location.reload();
	} else {

	}
}

///

function addTeam(projectId){
	var item = {
    	title: $("#team-title").val()
    }
    $.ajax({
    	url: "/CA-Project/admin/api/project/" + projectId + "/team",
        method: 'POST',
        data: JSON.stringify(item),
        contentType: "application/json; charset=utf-8",
        dataType: 'json'
    }).done(function (data) {
    	location.reload();
    }).fail(function () {
        WebUtils.show("Failed to create data");
    });
}
function addCurators(teamId){
	location.href = '/CA-Project/admin/project/'+projectId+'/addCurators/'+teamId;
}
function addStudents(){
	location.href = '/CA-Project/admin/project/'+projectId+'/addStudents';
}
function projectMeetings(){
	location.href = '/CA-Project/admin/project/'+projectId+'/meetings';
}
function projectFiles(){
	location.href = '/CA-Project/admin/project/'+projectId+'/files';
}
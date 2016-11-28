<<<<<<< HEAD
function showTable(id){
=======
function showTables(id){
>>>>>>> c04b2bf968dadf3ede6b40b4248ef751afd37d49
	var teamID = id;
	var teamCurators = "teamCurators"+teamID;
	var teamStudents = "teamStudents"+teamID;
	//alert(teamID);
////////////////////////// TABLE WITH CURATORS ///////////////////////////////////
<<<<<<< HEAD
$(function () {
	// $("#projectGrid").jsGrid({	
	$('#'+teamCurators).jsGrid({								//!
    	
        height: "220px",
        width: "90%",
=======
$(function () {	
	$('#'+teamCurators).jsGrid({											//!
    	
        height: "220px",
        width: "100%",
>>>>>>> c04b2bf968dadf3ede6b40b4248ef751afd37d49
        
    	
        filtering: false,
        editing: true,
        sorting: true,
        paging: false,
        autoload: true,

        controller: {
            loadData: function () {
                var deferred = $.Deferred();
<<<<<<< HEAD
                $.ajax({									//GET
=======
                $.ajax({													//GET
>>>>>>> c04b2bf968dadf3ede6b40b4248ef751afd37d49
                    url: "/CA-Project/teamCurators" + teamID,				// !
                    dataType: 'json'
                }).done(function (data) {
                    deferred.resolve(data);
                }).fail(function () {
                    WebUtils.show('Error to load data');
                    deferred.reject("loading error");
                });
                return deferred.promise();
            },

<<<<<<< HEAD
            updateItem: function (item) {
                var deferred = $.Deferred();
                return $.ajax({
                    method: "PUT",
                    url: "/CA-Project/team",				//!
                    data: JSON.stringify(item),
                    contentType: "application/json; charset=utf-8"
                }).done(function(){
                    deferred.resolve(item);
                }).fail(function () {
                    WebUtils.show('Failed to update');
=======
            deleteItem: function (item) {
                return $.ajax({
                    method: "DELETE",
                    url: "/CA-Project/team/" + item.id						//!
                }).fail(function () {
                    WebUtils.show('Failed to delete');
                });
            }

        },
        deleteConfirm: "Do you really want to delete the team?",			//!
        fields: [															//!!
        			// from DB
            {name: "u_id", type: "text", title: "user id", validate: "required"},
            {name: "email", type: "text", title: "email", validate: "required"},
            {name: "first_name", type: "text", title: "first_name", validate: "required"},
            {name: "second_name", type: "text", title: "second_name", validate: "required"},
            {name: "last_name", type: "text", title: "last_name", validate: "required"},
            {type: "control", editButton: false, deleteButton: true, modeSwitchButton: false, clearFilterButton: false}

        ]

    });
    
});
////////////////////////// TABLE WITH STUDENTS ///////////////////////////////////
$(function () {	
	$('#'+teamStudents).jsGrid({											//!
    	
        height: "220px",
        width: "100%",
        
    	
        filtering: false,
        editing: true,
        sorting: true,
        paging: false,
        autoload: true,

        controller: {
            loadData: function () {
                var deferred = $.Deferred();
                $.ajax({													//GET
                    url: "/CA-Project/teamStudents" + teamID,				// !
                    dataType: 'json'
                }).done(function (data) {
                    deferred.resolve(data);
                }).fail(function () {
                    WebUtils.show('Error to load data');
>>>>>>> c04b2bf968dadf3ede6b40b4248ef751afd37d49
                    deferred.reject("loading error");
                });
                return deferred.promise();
            },

            deleteItem: function (item) {
                return $.ajax({
                    method: "DELETE",
<<<<<<< HEAD
                    url: "/CA-Project/team/" + item.id	//!
=======
                    url: "/CA-Project/team/" + item.id						//!
>>>>>>> c04b2bf968dadf3ede6b40b4248ef751afd37d49
                }).fail(function () {
                    WebUtils.show('Failed to delete');
                });
            }

        },
<<<<<<< HEAD
        deleteConfirm: "Do you really want to delete the team?",		//!
        fields: [														//!!
        			// from DB
            {name: "u_id", type: "text", title: "user id", validate: "required"},
            {name: "email", type: "text", title: "email", validate: "required"},
=======
        deleteConfirm: "Do you really want to delete the team?",			//!
        fields: [															//!!
        			// from DB
            // photo {name: "u_id", type: "text", title: "user id", validate: "required"},
        	{name: "u_id", type: "text", title: "user id", validate: "required"},
            {name: "email", type: "text", title: "email", validate: "required"},
            {name: "is_active", type: "boolean", title: "is_active", validate: "required"},
            {name: "un_title", type: "text", title: "un_title", validate: "required"},
>>>>>>> c04b2bf968dadf3ede6b40b4248ef751afd37d49
            {name: "first_name", type: "text", title: "first_name", validate: "required"},
            {name: "second_name", type: "text", title: "second_name", validate: "required"},
            {name: "last_name", type: "text", title: "last_name", validate: "required"},
            {type: "control", editButton: false, deleteButton: true, modeSwitchButton: false, clearFilterButton: false}

        ]

    });
    
});
<<<<<<< HEAD
}
=======

}

///

function deleteTeam(id){
	if (confirm("Are you shure to delete this team?")) {
		//alert(id);
		$.ajax({
			method: "DELETE",
			url: "/CA-Project/teamDelete" + id									//!
			}).fail(function () {
				WebUtils.show('Failed to delete Team');
			});
	} else {
		
	}
}

function addTeam(projectId){
	
}
>>>>>>> c04b2bf968dadf3ede6b40b4248ef751afd37d49

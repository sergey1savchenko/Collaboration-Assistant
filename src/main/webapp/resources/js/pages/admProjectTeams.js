function showTable(id){
	var teamID = id;
	var teamCurators = "teamCurators"+teamID;
	var teamStudents = "teamStudents"+teamID;
	//alert(teamID);
////////////////////////// TABLE WITH CURATORS ///////////////////////////////////
$(function () {
	// $("#projectGrid").jsGrid({	
	$('#'+teamCurators).jsGrid({								//!
    	
        height: "220px",
        width: "90%",
        
    	
        filtering: false,
        editing: true,
        sorting: true,
        paging: false,
        autoload: true,

        controller: {
            loadData: function () {
                var deferred = $.Deferred();
                $.ajax({									//GET
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
                    deferred.reject("loading error");
                });
                return deferred.promise();
            },

            deleteItem: function (item) {
                return $.ajax({
                    method: "DELETE",
                    url: "/CA-Project/team/" + item.id	//!
                }).fail(function () {
                    WebUtils.show('Failed to delete');
                });
            }

        },
        deleteConfirm: "Do you really want to delete the team?",		//!
        fields: [														//!!
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
}
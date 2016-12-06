$(function () {
	$("#teamStudents").jsGrid({

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
                    url: "/CA-Project/admin/api/team/" + teamId + "/students",				// !
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
                    url: '/CA-Project/admin/api/project/'+projectId+'/student/'+item.id
                }).done(function (data) {
                	location.reload();
            	}).fail(function () {
                    WebUtils.show('Failed to delete');
                });
            }

        },

        deleteConfirm: "Do you really want to delete student from this team?",
        fields: [															//!!
        			// from DB
            //photo {name: "u_id", type: "text", title: "user id", validate: "required"},
        	//{name: "id", type: 'link', url: '/CA-Project/admin/student/{id}', width: 50, title: 'Student page'},
            {name: "email", type: "text", title: "email", validate: "required"},
            {name: "firstName", type: "text", title: "First name", validate: "required"},
            {name: "secondName", type: "text", title: "Second name", validate: "required"},
            {name: "lastName", type: "text", title: "Last name", validate: "required"},
            {name: "university.title", type: "text", title: "University", validate: "required"},
            {type: "control", editButton: false, deleteButton: true, modeSwitchButton: false, clearFilterButton: false}

        ]

    });

});



function add(){
	students = [];
	var chek = false;
	var elements = $('[id^="cur"]');
	$('[id^="cur"]').each(function(item){
		 if(elements[item].checked){
			 students.push( parseInt(elements[item].id.slice(3), 10) );
			 chek = true;
		 }
	})
	if(chek){
		students.forEach(function(item, i, students) {
			$.post( '/CA-Project/admin/api/student/'+item+'/project/'+projectId+'/team/'+teamId );
		});
		location.reload();
	}else{
	}
}

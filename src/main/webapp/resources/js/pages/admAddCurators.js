$(function () {
	$("#teamCurators").jsGrid({

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
                $.ajax({
                    url: "/CA-Project/admin/api/team/" + teamId + "/curators",
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
                }).done(function (data) {
                	location.reload();
            	}).fail(function () {
                    WebUtils.show('Failed to delete');
                });
            }

        },
        deleteConfirm: "Do you really want to delete curator from this team?",
        fields: [
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



function add(){
	curators = [];
	var chek = false;
	var elements = $('[id^="cur"]');
	$('[id^="cur"]').each(function(item){
		 if(elements[item].checked){
			 curators.push( parseInt(elements[item].id.slice(3), 10) );
			 chek = true;
		 }
	})
	if(chek){
		curators.forEach(function(item, i, curators) {
			$.post( '/CA-Project/admin/api/curator/'+item+'/project/'+projectId+'/team/'+teamId );
		});
		location.reload();
	}else{
	}
}

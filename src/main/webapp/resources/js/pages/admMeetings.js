$(function () {
    $("#admMeetingsGrid").jsGrid({								//!
    	
        height: "90%",
        width: "100%",
        
    	
        filtering: true,
        editing: true,
        sorting: true,
        paging: false,
        autoload: true,
        rowClick: function(args) {
		  
		},

        controller: {
            loadData: function () {
                var deferred = $.Deferred();
                
                $.ajax({									//GET
                    url: '/CA-Project/admin/api/project/'+document.getElementById("projectId").value+'/meetings',				// !
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
                    url: "/CA-Project/admin/api/meeting",				//!
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
                    url: "/CA-Project/admin/api/meeting/" + item.id	//!
                }).fail(function () {
                    WebUtils.show('Failed to delete');
                });
            }

        },
        deleteConfirm: "Do you really want to delete the meeting?",		//!
        fields: [														//!!
        			// from DB
            {name: "title", type: "text", title: "Title", validate: "required"},
            {name: "address", type: "text",  title: "Address"},
            {name: "datetime", type: "jsDate", width: 150, title: "Date", validate: "required"},
            {type: "control", editButton: true, deleteButton: true, modeSwitchButton: false, clearFilterButton: true}

        ]

    });
   
});

function onCreateVerify() {
    $('#new-meeting').validate({
        rules: {
            'meeting-title': 'required',
            'meeting-address': 'required',
            'meeting-date': 'required',
            
        },
        messages: {
            'meeting-title': 'Please enter the title',
            'meeting-address': 'Please enter the address',
            'meeting-date': 'Please enter the end date',
            
        },
        submitHandler: function(form) {
            onCreateAction();
        }
    });
    $('#new-meeting').submit();
}
//this is called in case of creating a new item
function onCreateAction() {
    var item = {
        id: 0,
        title: $("#meeting-title").val(),
        address: $("#meeting-address").val(),
        datetime: $("#meeting-date").val(),
       
    };
    $.ajax({
        url: "/CA-Project/admin/api/project/"+document.getElementById("projectId").value+"/meeting",
        method: 'POST',
        data: JSON.stringify(item),
        contentType: "application/json; charset=utf-8",
        dataType: 'json'
    }).done(function (data) {
        $("#admMeetingsGrid").jsGrid("insertItem", data);
        $("#addDialog").modal("hide");
    }).fail(function (data) {
    	console.log(data);
        WebUtils.show("Failed to create meeting");
    });
}

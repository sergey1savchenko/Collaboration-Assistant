$(function () {
    $("#admFilesGrid").jsGrid({								//!
    	
        height: "65%",
        width: "100%",
        
    	
        filtering: false,
        editing: false,
        sorting: true,
        paging: false,
        autoload: true,

        controller: {
        	loadData: function () {
                var deferred = $.Deferred();
                $.ajax({									//GET
                	url: '/CA-Project/admin/api/project/'+$("#project_id").val()+'/files',				// !
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
                    url: '/CA-Project/admin/api/file/' + item.id	//!
                }).fail(function () {
                    WebUtils.show('Failed to delete');
                });
            }

        },
        deleteConfirm: "Do you really want to delete the file?",		//!
        fields: [														//!!
        			// from DB
        	{name: "text", type: "text", title: "File title", validate: "required", width: "80%"},
        	{name: "id", type: "link", url: '/CA-Project/admin/api/file/{id}', title: "", width: "10%", align:"center"},
        	{type: "control", editButton: false, deleteButton: true, modeSwitchButton: false, clearFilterButton: false}

        ]

    });
});

function onCreateVerify() {
    $('#new-file').validate({
        rules: {
            'file': {
            	required: true,
            	depends: isFilePresent
            },
            'text': 'required'
        },
        messages: {
            'file': 'Please choose the file',
            'text': 'Please set a file title'
        },
        submitHandler: function(form) {
        	if($(document.getElementById("link")).is(':disabled')){        		
        		onCreateAction();
        	}else{
        		onCreateExternalAction()
        	}
        }
    });
}
// this is called in case of creating a new item
function onCreateAction() {
	
	var formData = new FormData( $('#new-file')[0]);
    $.ajax({
    	url: '/CA-Project/admin/api/project/' + $('#project_id').val()
    	+ '/file',
    	type: 'POST',
    	data: formData,
    	cache: false,
    	contentType: false,
    	processData: false})
    	.done(function(data) {
    		$("#admFilesGrid").jsGrid("insertItem", data);
    		$("#addDialog").modal("hide");
    	}).fail(function() {
    		WebUtils.show("Failed to create data. Maybe you forgot to choose a file?");
    	});
}

function onCreateExternalAction() {
	
	var item = {
	        link: $("#link").val(),
	        text: $("#text").val()
	    };
	$.ajax({
        url: "/CA-Project/admin/api/project/" + $('#project_id').val()
    	+ '/file-ext',
        method: 'POST',
        data: JSON.stringify(item),
        contentType: "application/json; charset=utf-8",
        dataType: 'json'
	}).done(function(data) {
    		$("#admFilesGrid").jsGrid("insertItem", data);
    		$("#addDialog").modal("hide");
    	}).fail(function() {
    		WebUtils.show("Failed to create data. Maybe you forget to choose a file?");
    	});
}
	


function isFilePresent() {
    return document.getElementById("file").file.length != 0
}

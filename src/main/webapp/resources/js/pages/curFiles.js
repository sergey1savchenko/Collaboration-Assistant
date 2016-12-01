$(function () {
    $("#curFilesGrid").jsGrid({								//!
    	
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
                	url: '/CA-Project/curator/api/team/'+$("#team_id").val()+'/files',				// !
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
                    url: '/CA-Project/curator/api/file/' + item.id	//!
                }).fail(function () {
                    WebUtils.show('Failed to delete');
                });
            }

        },
        deleteConfirm: "Do you really want to delete the file?",		//!
        fields: [														//!!
        			// from DB
        	{name: "text", type: "text", title: "File title", validate: "required", width: "75%"},
        	{name: "id", type: "link", url: '/CA-Project/curator/api/file/{id}', title: "download", validate: "required", width: "15%"},
            {type: "control", editButton: false, deleteButton: true, modeSwitchButton: false, clearFilterButton: false}

        ]

    });
});

function onCreateVerify() {
    $('#new-file').validate({
        rules: {
            'file': 'required'
        },
        messages: {
            'file': 'Please choose the file'
        },
        submitHandler: function(form) {
            
        }
    });
    $('#new-file').submit();
}
//this is called in case of creating a new item
function onCreateAction() {
	var $file = document.getElementById('file'),
    $formData = new FormData();

	if ($file.files.length > 0) {
		for (var i = 0; i < $file.files.length; i++) {
			$formData.append('file-' + i, $file.files[i]);
		}
	}
	
    var item = {        
        //text: $("#file-name").val(),
    	file: $formData,
    	text: "test",
    	
    };
    $.ajax({
        url: '/CA-Project/admin/api/project/'+$("#project_id").val()+'/file',
        method: 'POST',
        data: JSON.stringify(item),
        contentType: "application/json/multypart; charset=utf-8",
        dataType: 'json'
    }).done(function (data) {
        $("#admFilesGrid").jsGrid("insertItem", data);
        $("#addDialog").modal("hide");
    }).fail(function () {
        WebUtils.show("Failed to create data");
    });
}

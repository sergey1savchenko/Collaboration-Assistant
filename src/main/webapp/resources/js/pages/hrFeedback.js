$(function () {
    $("#hrFeedbackGrid").jsGrid({								//!
    	
        height: "65%",
        width: "100%",
        
    	
        filtering: false,
        editing: true,
        sorting: false,
        paging: false,
        autoload: true,

        controller: {
            loadData: function () {
                var deferred = $.Deferred();
                $.ajax({									//GET
                    type: 'GET',
                	url: '/CA-Project/hr/api/feedback/' + $("#studentId").val(),				// !
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
                    url: '/CA-Project/hr/api/feedback',				//!
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
                    url: "/CA-Project/hr/api/feedback/" + item.id	//!
                }).fail(function () {
                    WebUtils.show('Failed to delete');
                });
            }

        },
        deleteConfirm: "Do you really want to delete the project?",		//!
        fields: [														//!!
        			// from DB
            {name: "generalReport", type: "textarea", title: "General Report", validate: "required", width: "60%"},
            {name: "techReport", type: "textarea", title: "Tech Report", validate: "required", width: "20%"},
            {name: "interviewer", type: "textarea", title: "Interviewer", validate: "required", width: "10%"},
            {type: "control", editButton: true, deleteButton: true, modeSwitchButton: false, clearFilterButton: false}

        ]

    });
});

function onCreateVerify() {
    $('#new-feedback').validate({
        rules: {
            'general_report': 'required',
            'tech_report': 'required',
            'interviewer': 'required'
        },
        messages: {
            'general_report': 'Please enter the title',
            'tech_report': 'Please enter the start date',
            'interviewer' : 'Please enter interviewer'
        },
        submitHandler: function(form) {
            onCreateAction();
        }
    });
    $('#new-feedback').submit();
}
//this is called in case of creating a new item
function onCreateAction() {
    var item = {
        id: 0,
        generalReport: $("#general_report").val(),
        techReport: $("#tech_report").val(),
        interviewer: $("#interviewer").val()
        
    };
    $.ajax({
        url: "/CA-Project/hr/api/feedback/" + $("#studentId").val(),
        method: 'POST',
        data: JSON.stringify(item),
        contentType: "application/json; charset=utf-8",
        dataType: 'json'
    }).done(function (data) {
        $("#hrFeedbackGrid").jsGrid("insertItem", data);
        $("#addDialog").modal("hide");
    }).fail(function () {
        WebUtils.show("Failed to create data");
    });
}

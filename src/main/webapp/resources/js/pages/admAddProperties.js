$(function () {
    $("#propertiesGrid").jsGrid({
        height: "90%",
        width: "100%",

        filtering: false,
        editing: true,
        sorting: true,
        paging: false,
        autoload: true,
        
        rowClick: function(args) {
		  
		},

        controller: {
            loadData: function () {
                var deferred = $.Deferred();
                $.ajax({
                    url: '/CA-Project/admin/api/properties',
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
                    url: "/CA-Project/admin/api/property",
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
                    url: "/CA-Project/admin/api/property/" + item.id
                }).fail(function () {
                    WebUtils.show('Failed to delete');
                });
            }

        },
        deleteConfirm: "Do you really want to delete the property?",
        fields: [
            {name: "title", type: "text", title: "Title", validate: "required"},
            {name: "hasInt", type: "checkbox",  title: "Has int"},
            {name: "hasText", type: "checkbox", title: "Has text"},
            {type: "control", editButton: true, deleteButton: true, modeSwitchButton: false, clearFilterButton: false}

        ]

    });
});

function onCreateVerify() {
    $('#new-property').validate({
        rules: {
            'property-title': 'required'
        },
        messages: {
            'property-title': 'Please enter the title'
            
        },
        submitHandler: function(form) {
            onCreateAction();
        }
    });
    $('#new-property').submit();
}
//this is called in case of creating a new item
function onCreateAction() {
	var hasInt = false;
	var hasText = false;
	if($("#property-int").is(':checked')){
		hasInt = true;
	}
	if($("#property-text").is(':checked')){
		hasText = true;
	}
    var item = {
        id: 0,
        title: $("#property-title").val(),
        hasInt: hasInt,
        hasText: hasText
             };
    $.ajax({
        url: "/CA-Project/admin/api/property",
        method: 'POST',
        data: JSON.stringify(item),
        contentType: "application/json; charset=utf-8",
        dataType: 'json'
    }).done(function (data) {
        $("#propertiesGrid").jsGrid("insertItem", data);
        $("#addDialog").modal("hide");
    }).fail(function () {
        WebUtils.show("Failed to create data");
    });
}

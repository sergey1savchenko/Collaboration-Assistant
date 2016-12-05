$(function () {
    $("#projectGrid").jsGrid({
        height: "90%",
        width: "100%",

        filtering: true,
        editing: true,
        sorting: true,
        paging: false,
        autoload: true,
        
        rowClick: function(args) {
		    window.location.href = '/CA-Project/admin/project/' + args.item.id;
		},

        controller: {
            loadData: function () {
                var deferred = $.Deferred();
                $.ajax({
                    url: '/CA-Project/admin/api/projects',
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
                    url: "/CA-Project/admin/api/project",
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
                    url: "/CA-Project/admin/api/project/" + item.id
                }).fail(function () {
                    WebUtils.show('Failed to delete');
                });
            }

        },
        deleteConfirm: "Do you really want to delete the project?",
        fields: [
            {name: "title", type: "text", title: "Title", validate: "required"},
            {name: "description", type: "text",  title: "Description"},
            {name: "startDate", type: "jsDate", width: 150, title: "Start date", validate: "required"},
            {name: "endDate", type: "jsDate", width: 150, title: "End date", validate: "required"},
            {
                name: "university", source: 'project-university', title: "University", type: 'dictionary'
            },
            {type: "control", editButton: true, deleteButton: true, modeSwitchButton: false, clearFilterButton: true}

        ]

    });
    $.validator.addMethod(
        "endLaterThenStart",
        function (value, element) {
            var startValue = $('#project-start').val();
            if (startValue && startValue >= value) return false;
            return true;
        },
        "End date must be later than the start date"
    );
});

function onCreateVerify() {
    $('#new-project').validate({
        rules: {
            'project-title': 'required',
            'project-start': 'required',
            'project-end': {
                required: true,
                endLaterThenStart: true
            }
        },
        messages: {
            'project-title': 'Please enter the title',
            'project-start': 'Please enter the start date',
            'project-end': {
                required: 'Please enter the end date',
                endLaterThenStart: 'The end date must be later than start'
            }
        },
        submitHandler: function(form) {
            onCreateAction();
        }
    });
    $('#new-project').submit();
}
//this is called in case of creating a new item
function onCreateAction() {
    var item = {
        id: 0,
        title: $("#project-title").val(),
        description: $("#project-description").val(),
        startDate: $("#project-start").val(),
        endDate: $("#project-end").val(),
        university: WebUtils.getItemByDomainAndId('project-university', $("#project-university").val())
    };
    $.ajax({
        url: "/CA-Project/admin/api/project",
        method: 'POST',
        data: JSON.stringify(item),
        contentType: "application/json; charset=utf-8",
        dataType: 'json'
    }).done(function (data) {
        $("#projectGrid").jsGrid("insertItem", data);
        $("#addDialog").modal("hide");
    }).fail(function () {
        WebUtils.show("Failed to create data");
    });
}

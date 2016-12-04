$(function () {
    $("#projectGrid").jsGrid({
        height: "90%",
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
                    url: '/CA-Project/hr/api/projects',
                    dataType: 'json'
                }).done(function (data) {
                    deferred.resolve(data);
                }).fail(function () {
                    WebUtils.show('Error to load data');
                    deferred.reject("loading error");
                });
                return deferred.promise();
            },
        },  
        fields: [
            {name: "title", type: "text", title: "Title", validate: "required"},
            {name: "id", type: 'link', url: '/CA-Project/hr/project/{id}', width: 80, title: 'Project Teams'},
            {name: "description", type: "text",  title: "Description"},
            {name: "startDate", type: "jsDate", width: 150, title: "Start date", validate: "required"},
            {name: "endDate", type: "jsDate", width: 150, title: "End date", validate: "required"},
            {
                name: "university", source: 'project-university', title: "University", type: 'dictionary'
            }
            ]

    });

});



$(function () {
    $("#stdFilesGrid").jsGrid({								//!

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
                	url: '/CA-Project/student/api/files-team',				// !
                    dataType: 'json'
                }).done(function (data) {
                    deferred.resolve(data);
                }).fail(function () {
                    WebUtils.show('Error to load data');
                    deferred.reject("loading error");
                });
                return deferred.promise();
            }

        },
        fields: [														//!!
        			// from DB
        	{name: "text", type: "text", title: "File title", validate: "required", width: "90%"},
        	{name: "id", type: "link", url: '/CA-Project/student/api/file/{id}', title: "", width: "10%", align: "center"}

        ]

    });
});

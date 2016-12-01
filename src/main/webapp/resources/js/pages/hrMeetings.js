$(function () {
    $("#hrMeetingsGrid").jsGrid({								//!
    	
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
                $.ajax({									//GET
                    url: '/CA-Project/hr/api/project/{projectId}/meetings',				// !
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
        fields: [														//!!
        			// from DB

            {name: "id", type: 'link', url: 'curator/meetingEvaluation/={id}', width: 70, title: 'Settings'},
            {name: "title", type: "text", title: "Title", validate: "required"},
            {name: "address", type: "text",  title: "address"},
            {name: "datetime", type: "jsDate", width: 150, title: "Date", validate: "required"},
            {type: "control", editButton: false, deleteButton: false, modeSwitchButton: false, clearFilterButton: true}

        ]

    });
   
});

$(function () {
    $("#MeetingPropertiesGrid").jsGrid({								//!
    	
        height: "40%",
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
                    url: '/CA-Project/admin/api/project/'+document.getElementById("projectId").value+'/properties/meetings',				// !
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
        	{name: "title", type: "text", title: "Title", validate: "required"},
            {name: "hasInt", type: "checkbox",  title: "Numerical"},
            {name: "hasText", type: "checkbox", title: "Text"},
            {type: "control", editButton: false, deleteButton: false, modeSwitchButton: false, clearFilterButton: false}

        ]

    });
   
});
$(function () {
    $("#ProjectPropertiesGrid").jsGrid({								//!
    	
        height: "40%",
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
                    url: '/CA-Project/admin/api/project/'+document.getElementById("projectId").value+'/properties/projects',				// !
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
        	{name: "title", type: "text", title: "Title", validate: "required"},
            {name: "hasInt", type: "checkbox",  title: "Numerical"},
            {name: "hasText", type: "checkbox", title: "Text"},
            {type: "control", editButton: false, deleteButton: false, modeSwitchButton: false, clearFilterButton: false}

        ]

    });
   
});
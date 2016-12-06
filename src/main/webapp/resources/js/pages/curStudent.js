//studentId
$(function () {
    $("#studentProjectEvaluation").jsGrid({
        height: "220px",
        width: "90%",

        filtering: false,
        editing: true,
        sorting: false,
        paging: false,
        autoload: true,
        
        rowClick: function(args) {
        	
        },
        
        controller: {
            loadData: function () {
                var deferred = $.Deferred();
                $.ajax({
                    url: '/CA-Project/curator/api/student/'+studentId+'/proj-eval',
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
            	if(item.marktype.hasText && item.marktype.hasInt){					// text AND int
            		var markType = {
                    		id: item.marktype.id,
                    		title: item.marktype.title,
                    		hasText: item.marktype.hasText,
                    		hasInt: item.marktype.hasInt
                    	}
                    	var pe = {
                    		id: item.id,
                    		intValue: item.intValue,
                    		textValue: item.textValue,
                    		marktype: markType
                    	}
            	} else if (item.marktype.hasText && !item.marktype.hasInt){			// text
            		var markType = {
                    		id: item.marktype.id,
                    		title: item.marktype.title,
                    		hasText: item.marktype.hasText,
                    		hasInt: item.marktype.hasInt
                    	}
                    	var pe = {
                    		id: item.id,
                    		intValue: null,
                    		textValue: item.textValue,
                    		marktype: markType
                    	}
            	} else if (!item.marktype.hasText && item.marktype.hasInt){			// int
            		var markType = {
                    		id: item.marktype.id,
                    		title: item.marktype.title,
                    		hasText: item.marktype.hasText,
                    		hasInt: item.marktype.hasInt
                    	}
                    	var pe = {
                    		id: item.id,
                    		intValue: item.intValue,
                    		textValue: null,
                    		marktype: markType
                    	}
            	}
            	
            	
                var deferred = $.Deferred();
                return $.ajax({
                    method: "PUT",
                    url: '/CA-Project/curator/api/student/'+studentId+'/proj-eval',
                    data: JSON.stringify(pe),
                    contentType: "application/json; charset=utf-8"
                }).done(function(){
                    deferred.resolve(pe);
                    location.reload();
                }).fail(function () {
                    WebUtils.show('Failed to update');
                    deferred.reject("loading error");
                });
                return deferred.promise();
            },
            
        },
        
        fields: [
            //{name: "id", type: "text", title: "id", width: "0px"},
        	{name: "marktype.title", type: "text",  title: "Marktype", editing: false},
        	{name: "marktype.hasInt", type: "checkbox",  title: "Has int", width: "30px", editing: false},
            {name: "intValue", type: "text",  title: "Integer value", width: "75px"},
            {name: "marktype.hasText", type: "checkbox",  title: "Has text", width: "30px", editing: false},
            {name: "textValue", type: "text",  title: "Text value"},
            {type: "control", editButton: true, deleteButton: false, modeSwitchButton: false, clearFilterButton: true, width: "30px"}
        ]

    });
});

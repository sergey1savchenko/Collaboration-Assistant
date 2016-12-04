//////////////////////////////////////////// copyAndCreate //////////////////////////////////////////////////////////
function copyAndCreate() {
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
        	copyAndCreateInsert();
        }
    });
    $('#new-project').submit();
}
function copyAndCreateInsert() {
	var pId = $("#project-evals").val();
	var item = {
            id: 0,
            title: $("#project-title").val(),
            description: $("#project-description").val(),
            startDate: $("#project-start").val(),
            endDate: $("#project-end").val(),
            university: WebUtils.getItemByDomainAndId('project-university', $("#project-university").val())
        };
    $.ajax({
        url: '/CA-Project/admin/api/projectCopy/' + pId,													// !!!
        method: 'POST',
        data: JSON.stringify(item),
        contentType: "application/json; charset=utf-8",
        dataType: 'json'
    }).done(function (data) {
    	if (confirm("New project is created. Go to projects page?")) {
    		 window.location.href = "/CA-Project/admin";
    	} else {
    		location.reload();
    	}

    }).fail(function () {
        WebUtils.show("Failed to create data");
    });
}
//////////////////////////////////////// chooseAndCreate ///////////////////////////////////////////////////////
var pe = [];
var me = [];
var peChek = false;
var meChek = false;
function addEvaluations(){
	
	pe = [];
	peChek = false;
	var elements = $('[id^="pem"]');
	$('[id^="pem"]').each(function(item){
		 if(elements[item].checked){
			 //alert(elements[item]);
			 pe.push( parseInt(elements[item].id.slice(3), 10) );
			 peChek = true;
		 }
	})
	
	me = [];
	meChek = false;
	elements = $('[id^="mem"]');
	$('[id^="mem"]').each(function(item){
		 if(elements[item].checked){
			 //alert(elements[item]);
			 me.push( parseInt(elements[item].id.slice(3), 10) );
			 meChek = true;
		 }
	})
	
	//alert("Cheked Evaluations added");
	//alert(pe);
	//alert(me);
}

function chooseAndCreate() {
	///
	addEvaluations();
	///
	if (!peChek||!meChek){
		WebUtils.show("First choose marktypes");
	} else{
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
	        	chooseAndCreateInsert();
	        }
	    });
	    $('#new-project').submit();
	}
}
function chooseAndCreateInsert() {
    var project = {
        id: 0,
        title: $("#project-title").val(),
        description: $("#project-description").val(),
        startDate: $("#project-start").val(),
        endDate: $("#project-end").val(),
        university: WebUtils.getItemByDomainAndId('project-university', $("#project-university").val())
    };
    
    var item = {
    	//	"projectMarkTypeIds", "project", "meetingMarkTypeIds"
    	project: project,
    	meetingMarkTypeIds: me,
    	projectMarkTypeIds: pe
    }
    
    $.ajax({
        url: "/CA-Project/admin/api/project",											// !!!
        method: 'POST',
        data: JSON.stringify(item),
        contentType: "application/json; charset=utf-8",
        dataType: 'json'
    }).done(function (data) {
    	if (confirm("New project is created. Go to projects page?")) {
    		 window.location.href = "/CA-Project/admin";
    	} else {
    		location.reload();
    	}

    }).fail(function () {
        WebUtils.show("Failed to create data");
    });
}
//////////////////////////////////////// all projects table //////////////////////////////////////////////////
$(function () {
    $("#projectGrid").jsGrid({															//!
    	
        height: "90%",
        width: "100%",
        
    	
        filtering: false,
        editing: true,
        sorting: true,
        paging: false,
        autoload: true,

        controller: {
            loadData: function () {
                var deferred = $.Deferred();
                $.ajax({																//GET
                    url: '/CA-Project/admin/api/projects',											// !
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
                    url: "/CA-Project/admin/api/projects",											//!
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
                    url: "/CA-Project/admin/api/projects/" + item.id								//!
                }).fail(function () {
                    WebUtils.show('Failed to delete');
                });
            }

        },
        deleteConfirm: "Do you really want to delete the project?",						//!
        fields: [																		//!!
        			// from DB
            {name: "title", type: "text", title: "Title", validate: "required"},
            {name: "id", type: 'link', url: '/teams?prj={id}', width: 70, title: 'Settings'},
            {name: "description", type: "text",  title: "Description"},
            {name: "startDate", type: "jsDate", width: 150, title: "Start date", validate: "required"},
            {name: "endDate", type: "jsDate", width: 150, title: "End date", validate: "required"},
            {
                name: "university", source: 'project-university', title: "University", type: 'dictionary'
                							// select id from jsp page
            },
            {type: "control", editButton: true, deleteButton: true, modeSwitchButton: false, clearFilterButton: true}

        ]

    });
    
    
    
    //-
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
//studentId meetingId
$(function () {
    $("#studentMeetingEvaluation").jsGrid({
        height: "220px",
        width: "90%",

        filtering: false,
        editing: true,
        sorting: false,
        paging: false,
        autoload: true,

        controller: {
            loadData: function () {
                var deferred = $.Deferred();
                $.ajax({
                    url: '/CA-Project/curator/api/meeting/'+meetingId+'/student/'+studentId+'/meet-eval',
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
                    url: '/CA-Project/curator/api/meeting/'+meetingId+'/student/'+studentId+'/meet-eval',
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
            
        },
        
        fields: [
            //{name: "id", type: "text", title: "id", width: "50px"},
        	{name: "marktype.title", type: "text",  title: "Marktype", editing: false},
        	{name: "marktype.hasInt", type: "checkbox",  title: "Has int", width: "30px", editing: false},
            {name: "intValue", type: "text",  title: "Integer value", width: "75px"},
            {name: "marktype.hasText", type: "checkbox",  title: "Has text", width: "30px", editing: false},
            {name: "textValue", type: "text",  title: "Text value"},
            {type: "control", editButton: true, deleteButton: false, modeSwitchButton: false, clearFilterButton: true, width: "30px"}

        ]

    });
});

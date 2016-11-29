function sendReport() {
    var report = {
        title: $("#report-title").val(),
        description: $("#report-description").val(),
   };
    $.ajax({
        url: "/CA-Project/admin/api/report-bug",
        method: 'POST',
        data: JSON.stringify(item),
        contentType: "application/json; charset=utf-8",
        dataType: 'json'
    }).done(function (data) {
        $("#areportBugDialog").modal("hide");
    }).fail(function () {
        WebUtils.show("Failed to create report");
    });
}
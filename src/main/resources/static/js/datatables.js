$(function () {
    $("#tablesdata").DataTable({
        "responsive": true,
        "lengthChange": false,
        "autoWidth": false,
        "scrollX": true,
        "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
        "lengthMenu": [
        [10, 25, 50, -1],
        [10, 25, 50, 'All']
    ]
        
    }).buttons().container().appendTo('#tablesdata_wrapper .col-md-6:eq(0)');

});


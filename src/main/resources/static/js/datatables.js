$(function () {
    $("#tablesdata").DataTable({
        "ordering": true,
        "responsive": true,
        "lengthChange": true,
        "autoWidth": false,
        "scroller": true,
        "scrollX": true,
        "buttons": ["copy", "csv", "excel", "pdf", "print"],
        "lengthMenu": [
            [10, 25, 50, -1],
            [10, 25, 50, 'All']
        ],
        "columnDefs": [
            {targets: '_all', visible: true} // Ensure all columns are visible
        ]
    }).buttons().container().appendTo('#tablesdata_wrapper .col-md-6:eq(1)');

});


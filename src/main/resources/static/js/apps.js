

// District Thana Cascade
//Home visit
$(document).ready(function () {
//    alert("Hello");
    $('#district').change(function () {
        var district = $('#district').val();
        //alert(district);

        $.ajax({
            type: "GET",
            url: "/thana/thana/" + district,
            dataType: 'json',
            success: function (data) {
                var slctSubcat = $('#thana'), option = "Select One";
                slctSubcat.empty();
                for (var i = 0; i < data.length; i++) {
                    option = option + "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                }
                slctSubcat.append(option);
            }, //success

            error: function (e) {
                alert("Error!");
            } // end error

        });// end ajax

    });// end change function

});// jquery

// District Thana Cascade End

$(document).ready(function () {
    // Initialize Select2 on your select element
    // alert("hello select 2");
    $('#select2').select2(
            {
                // placeholder: "Select an option",
               allowClear: true,
                width: '100%'
            }

    );
});

//new code 

$(document).ready(function () {
    let url = window.location.href;
    
    $(".nav-link").each(function () {
        if (this.href === url) {
            $(this).addClass("active");
            $(this).closest(".has-treeview").addClass("menu-open");
        }
    });
});

$(document).ready(function(){
    $("#toggle-sales").click(function(){
        $("#sales-submenu").slideToggle();
        if ($("#toggle-sales-icon").css('transform') == 'none') {
            $("#toggle-sales-icon").css({'transform': 'rotate(90deg)'});
        } else {
            $("#toggle-sales-icon").css({'transform': ''});
        };
    });
    $("#toggle-users").click(function(){
        $("#users-submenu").slideToggle();
        if ($("#toggle-users-icon").css('transform') == 'none') {
            $("#toggle-users-icon").css({'transform': 'rotate(90deg)'});
        } else {
            $("#toggle-users-icon").css({'transform': ''});
        };
    });
    $("#toggle-vendors").click(function(){
        $("#vendors-submenu").slideToggle();
        if ($("#toggle-vendors-icon").css('transform') == 'none') {
            $("#toggle-vendors-icon").css({'transform': 'rotate(90deg)'});
        } else {
            $("#toggle-vendors-icon").css({'transform': ''});
        };
    });
    $("#toggle-products").click(function(){
        $("#products-submenu").slideToggle();
        if ($("#toggle-products-icon").css('transform') == 'none') {
            $("#toggle-products-icon").css({'transform': 'rotate(90deg)'});
        } else {
            $("#toggle-products-icon").css({'transform': ''});
        };
    });
    $('#example').DataTable();
});
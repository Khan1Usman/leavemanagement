function hide_show_longmenu()
{
	 var $menu = $('#large_menu');
            if ($menu.is(':visible')) {
                // Slide away
                $menu.animate({left: -($menu.outerWidth() + 10)}, function() {
                    $menu.hide();
//                 $("#main_div").css('margin-left','8.3333%');
//                    alert();
                    $("#main_div").removeClass("col-lg-8 col-md-9 col-sm-9 col-lg-offset-1 col-md-offset-0 col-sm-offset-0");
                    $("#main_div").addClass("col-lg-10 col-md-10 col-sm-10 col-lg-offset-1 col-md-offset-1 col-sm-offset-1");
                    
                });
            }
            else {
                // Slide in
                $menu.show().animate({left: 0});
//                $("#main_div").css('margin-left','4.3333%');
                $("#main_div").removeClass("col-lg-10 col-md-10 col-sm-10 col-lg-offset-1 col-md-offset-1 col-sm-offset-1");
                $("#main_div").addClass("col-lg-8 col-md-9 col-sm-9 col-lg-offset-1 col-md-offset-0 col-sm-offset-0");
            }
	
}
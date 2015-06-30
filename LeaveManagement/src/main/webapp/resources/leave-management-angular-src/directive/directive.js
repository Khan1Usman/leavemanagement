//ccacApp.directive('datepicker', function() {
//    return {
//        restrict: 'A',
//        require : 'ngModel',
//        link : function (scope, element, attrs, ngModelCtrl) {
//            $(function(){
//                $(element).datepicker({
//                    dateFormat:'mm/dd/yy',
//                    onSelect:function (date) {
//                        scope.$apply(function () {
//                            ngModelCtrl.$setViewValue(date);
//                        });
//                    }
//                });
//            });
//        }
//    }
//});

leavemanagement.directive('datePickerLeavemanagement', function() {
    return {
        restrict: 'A',
        require : 'ngModel',
        link : function (scope, element, attrs, ngModelCtrl) {
            $(function(){
                $(element).datepicker({
                    dateFormat:'yy-mm-dd',
                    onSelect:function (date) {
                        scope.$apply(function () {
                            ngModelCtrl.$setViewValue(date);
                        });
                    }
                });
            });
        }
    }
});
leavemanagement.directive('datepickerForEdu', function() {
    return {
        restrict: 'A',
        require : 'ngModel',
        link : function (scope, element, attrs, ngModelCtrl) {
            $(function(){
                $(element).datepicker({
                	dateFormat: "d M yy",
                    changeMonth: true,
                    changeYear: true,
                    yearRange: '1990:2008',
                    onSelect:function (date) {
                        scope.$apply(function () {
                            ngModelCtrl.$setViewValue(date);
                        });
                    }
                });
            });
        }
    }
});
leavemanagement.directive('validPasswordC', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModelCtrl) {
        	ngModelCtrl.$parsers.unshift(function (viewValue, $scope) {
                var noMatch = viewValue != scope.empBasicInfoForm.password.$viewValue
                ngModelCtrl.$setValidity('noMatch', !noMatch)
            })
        }
    }
});
leavemanagement.directive('gnMenu', function() {
    return {
        // Restrict it to be an attribute in this case
        restrict: 'A',
        // responsible for registering DOM listeners as well as updating the DOM
        link: function(scope, element, attrs) {
        	new gnMenu( document.getElementById( 'gn-menu' ) );
        	$('.gn-menu li a').bind('click', function(event) {
    			var $anchor = $(this);
    			$('html, body').stop().animate({
    				scrollTop: $($anchor.attr('href')).offset().top
    			}, 1500, 'easeInOutExpo');
    			event.preventDefault();
    		});
        	$('a.scroll').bind('click', function(event) {
    			var $anchor = $(this);
    			$('html, body').stop().animate({
    				scrollTop: $($anchor.attr('href')).offset().top
    			}, 1500, 'easeInOutExpo');
    			event.preventDefault();
    		});
        }
    };
});
leavemanagement.directive('dynamicName', function($compile, $parse) {
	  return {
		    restrict: 'A',
		    terminal: true,
		    priority: 100000,
		    link: function(scope, elem,attr,ctrl) {
		    var prefix = attr['dynamicNamePrefix'];
		    if(prefix == undefined)
		    {
		    	prefix = "";
		    }	
			
		    var name = $parse(elem.attr('dynamic-name'))(scope);
		    // $interpolate() will support things like 'skill'+skill.id where parse will not
		    elem.removeAttr('dynamic-name');
		    elem.attr('name', prefix+name);
		    $compile(elem)(scope);
		    }
    };
});


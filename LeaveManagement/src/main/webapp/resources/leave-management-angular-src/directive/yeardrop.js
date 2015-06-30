leavemanagement.directive('yearDrop',function(){
    var currentYear = new Date().getFullYear();
    var year=1970;
   
    return {
        link: function(scope,element,attrs){
            scope.years = [];
            for (var i = currentYear; i >year; i--){
                scope.years.push(i);
            }
            scope.year = currentYear;
            
        },
    
    }
});
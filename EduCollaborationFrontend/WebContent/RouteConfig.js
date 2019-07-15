var myApp=angular.module("myApp",['ngRoute','ngCookies']);

myApp.config(function($routeProvider)
{	
	alert("I am in Route Provider");
	
	$routeProvider.when("/login",{templateUrl:"c_user/Login.html"})
				  .when("/register",{templateUrl:"c_user/Register.html"})
				  .when("/aboutUs",{templateUrl:"c_user/AboutUs.html"})
	              .when("/updateProfile",{templateUrl:"c_user/UpdateProfile.html"});
});

myApp.run(function($rootScope,$cookieStore)
	{
	  console.log('I am in run function.Page refreshed');
	  
	  if($rootScope.currentUser==undefined)
		  {
		  		$rootScope.currentUser=$cookieStore.get('userDetail');
		  }
	
	});	

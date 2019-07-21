var myApp=angular.module("myApp",['ngRoute','ngCookies']);

myApp.config(function($routeProvider)
{	
	alert("I am in Route Provider");
	
	$routeProvider.when("/login",{templateUrl:"c_user/Login.html"})
				  .when("/register",{templateUrl:"c_user/Register.html"})
				  .when("/aboutUs",{templateUrl:"c_user/AboutUs.html"})
	              .when("/updateProfile",{templateUrl:"c_user/UpdateProfile.html"})
	              .when("/addBlog",{templateUrl:"c_blog/Blog.html"})
	              .when("/showBlog",{templateUrl:"c_blog/ShowBlog.html"})
	              .when("/editBlog",{templateUrl:"c_blog/UpdateBlog.html"})
	              .when("/manageBlog",{templateUrl:"c_blog/ManageBlog.html"})
	              .when("/blogComments",{templateUrl:"c_blog/BlogComment.html"})
	              .when("/updatePic",{templateUrl:"c_user/UpdateProfilePicture.html"})
	              .when("/friend",{templateUrl:"c_friend/Friend.html"});
});

myApp.run(function($rootScope,$cookieStore)
	{
	  console.log('I am in run function.Page refreshed');
	  
	  if($rootScope.currentUser==undefined)
		  {
		  		$rootScope.currentUser=$cookieStore.get('userDetail');
		  }
	
	});	

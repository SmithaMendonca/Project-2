myApp.controller("UserController",function($scope,$http,$location,$rootScope,$cookieStore)
{
	$scope.userDetail={"username":"","password":"","name":"","emailId":"","mobileNo":"","address":"","role":"ROLE_USER"};
	$rootScope.userDetail1={"username":"","password":"","name":"","emailId":"","mobileNo":"","address":"","role":"ROLE_USER"};
	
	$rootScope.currentUser;
	
	$scope.register=function()
	{
		console.log('I am in Register Function');
		console.log($scope.userDetail);
		
		$http.post('http://localhost:8081/EduCollaborationMiddleware/addUser',$scope.userDetail)
		.then(function(response){
			alert("User Added");
		},function(errresponse){
			alert("Problem Occurred");
		});
	}
	
	$scope.checkUser=function()
	{
		console.log('I am in check User Function');
		console.log($rootScope.userDetail1);
		
		$http.post('http://localhost:8081/EduCollaborationMiddleware/checkUser',$rootScope.userDetail1)
		.then(function(response)
		{
			$rootScope.currentUser=response.data;
			console.log($rootScope.currentUser);
			$cookieStore.put('userDetail',response.data);
			$location.path("/userHome");
		},function(errresponse)
		{
			alert("User Name and Password does not match");
		});
	}
	
	$scope.editUser=function()
	{
		console.log('I am in edit user function');
		
		$http.get('http://localhost:8081/EduCollaborationMiddleware/getUser/'+$rootScope.currentUser.username)
		.then(function(response)
		{
			$rootScope.userDetail1=response.data;
			console.log($rootScope.userDetail1);
			$location.path("/updateProfile");			
		},function(errorresponse){
			alert("Problem occured");
		});
	}
	
	$scope.updateProfile=function()
	{
		console.log('I am in update profile function');
		
		$http.post('http://localhost:8081/EduCollaborationMiddleware/updateUser',$rootScope.userDetail1)
		.then(function(response)
		{
			alert(response.data);						
		},function(errorresponse)
		{
			alert(errorresponse.data);
			alert(errorresponse.statusText);
		});
	}
	
	$scope.logout=function()
	{
		console.log('I am in logout Function');
		
		$cookieStore.remove('userDetail');
		delete $rootScope.currentUser;
		
		alert("User has logged out");
		$location.path("/login");
	}
});
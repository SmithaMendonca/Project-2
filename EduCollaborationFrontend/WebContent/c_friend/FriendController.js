myApp.controller("FriendController",function($scope,$http,$location,$rootScope)
{
	$scope.friend={"friendId":0,"username":"","friendusername":"","status":""};
	
	$scope.friendList;
	
	$scope.pendingFriendList;
	
	$scope.suggestedFriendList;
	
	function showFriendList()
	{
		console.log('I am in Friend list');
		
		$http.get('http://localhost:8081/EduCollaborationMiddleware/showFriendList/'+$rootScope.currentUser.username)
		.then(function(response){
			$scope.friendList=response.data;
		},function(errorresponse){
			alert("Error Occurred");
		});	
	}
	
	function showPendingFriendList()
	{
        console.log('I am in Pending Friend list');
		
		$http.get('http://localhost:8081/EduCollaborationMiddleware/showPendingFriendRequest/'+$rootScope.currentUser.username)
		.then(function(response){
			$scope.pendingFriendList=response.data;
		},function(errorresponse){
			alert("Error Occurred");
		});	
	}
	
	function showSuggestedFriendList()
	{
		 console.log('I am in Friend list');
			
		$http.get('http://localhost:8081/EduCollaborationMiddleware/showSuggestedFriendList/'+$rootScope.currentUser.username)
		 .then(function(response){
			$scope.suggestedFriendList=response.data;
		 },function(errorresponse){
				alert("Error Occurred");
		});			
	}
	
	$scope.sendFriendRequest=function(friendname)
	{
		console.log('I am in sending Friend request');
		
		$scope.friend.username=$rootScope.currentUser.username;
		$scope.friend.friendusername=friendname;
		
		$http.post('http://localhost:8081/EduCollaborationMiddleware/sendFriendRequest',$scope.friend)
		.then(function(response){
			alert("Friend request sent");
			showFriendList();
			showPendingFriendList();
			showSuggestedFriendList();
		},function(errresponse){
			alert("Problem Occurred");
		});		
	}
	
	$scope.accept=function(friendId)
	{
		console.log('I am in accepting Friend request');
		
		$http.get('http://localhost:8081/EduCollaborationMiddleware/acceptFriendRequest/'+friendId)
		 .then(function(response){
			 alert("Friend request accepted");
			 showFriendList();
			 showPendingFriendList();
			 showSuggestedFriendList();
		 },function(errorresponse){
			alert("Error Occurred");
		});		
	}
	
	$scope.unfriend=function(friendId)
	{
		console.log('I am in Unfriend request');
		
		$http.get('http://localhost:8081/EduCollaborationMiddleware/deleteFriendRequest/'+friendId)
		 .then(function(response){
			 alert("Friend request deleted");
			 showFriendList();
			 showPendingFriendList();
			 showSuggestedFriendList();
		 },function(errorresponse){
			alert("Error Occurred");
		});		
	}
	
	showFriendList();
	showPendingFriendList();
	showSuggestedFriendList();
});
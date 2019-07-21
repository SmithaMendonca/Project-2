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
		
	}
	
	showFriendList();
	showPendingFriendList();
	showSuggestedFriendList();
});
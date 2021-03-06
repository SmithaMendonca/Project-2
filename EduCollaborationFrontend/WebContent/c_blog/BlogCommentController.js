myApp.controller("BlogCommentController",function($scope,$http,$location,$rootScope)
{
	$scope.blog={"blogId":0,"blogName":"","blogContent":"","username":"","createDate":"","status":"","likes":0,"dislikes":0};
	
	$scope.blogComments;
	
	$scope.blogComment={"commentId":0,"commentText":"","username":"","commentDate":"","blogId":0};
	
	function loadAllComments()
	{
		console.log('I am in Comments');
		$http.get('http://localhost:8081/EduCollaborationMiddleware/getAllBlogComments/'+$rootScope.blogId)
		.then(function(response)
		{
			$scope.blogComments=response.data;
		});		
	}
	
	function blogInfo()
	{
		console.log('I am in blogInfo');
		$http.get('http://localhost:8081/EduCollaborationMiddleware/getBlog/'+$rootScope.blogId)
		.then(function(response)
		{
			$scope.blog=response.data;
		});
	}
	
	$scope.addComment=function()
	{
		console.log('I am in adding comment');
		$scope.blogComment.blogId=$rootScope.blogId;
		$scope.blogComment.username=$rootScope.currentUser.username;
		
		$http.post('http://localhost:8081/EduCollaborationMiddleware/addComment',$scope.blogComment)
		.then(function(response)
		{
			alert("Blog Comment Added");
			loadAllComments();
			blogInfo();
		});		
	}
	
	$scope.deleteComment=function(commentId)
	{
		console.log('I am in deleting comment');
		$http.get('http://localhost:8081/EduCollaborationMiddleware/deleteComment/'+commentId)
		.then(function(response)
		{
			alert("Comment Deleted");
			loadAllComments();
			blogInfo();
		},function(errresponse){
			alert("Error occurred");
		});	
	}
	loadAllComments();
	blogInfo();
});
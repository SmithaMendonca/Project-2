myApp.controller("BlogController",function($scope,$http,$location,$rootScope)
{
	$scope.blog={"blogId":0,"blogName":"","blogContent":"","username":"","createDate":"","status":"","likes":0,"dislikes":0};
	
	$scope.blogDetail;
	
	$rootScope.blog1;
	
	$rootScope.blogId;
	
	$scope.addBlog=function()
	{
		console.log('I am in add blog');
		
		$scope.blog.username=$rootScope.currentUser.username;
		
		$http.post('http://localhost:8081/EduCollaborationMiddleware/addBlog',$scope.blog)
		.then(function(response){
			alert("Blog Added");
		},function(errorresponse){
			alert("Problem Occurred while adding blog");
		});
	}
	
	$scope.updateBlog=function()
	{
		console.log('I am in update blog');
		
		$rootScope.blog1.username=$rootScope.currentUser.username;
		
		$http.post('http://localhost:8081/EduCollaborationMiddleware/updateBlog',$rootScope.blog1)
		.then(function(response){
			alert("Blog Updated");
			$location.path("/showBlog");
		},function(errorresponse){
			alert("Error Occurred while updating blog");
		});
	}
	
	$scope.incrementLikes=function(blogId)
	{
		console.log('I am in increment likes blog');
		
		$http.get('http://localhost:8081/EduCollaborationMiddleware/incrementLikes/'+blogId)
		.then(function(response){
			alert("Thank You for liking the blog");
		},function(errorresponse){
			alert("Error Occurred while liking the blog");
		});		
	}
	
	$scope.incrementDisLikes=function(blogId)
	{
		console.log('I am in increment dislikes blog');
		
		$http.get('http://localhost:8081/EduCollaborationMiddleware/incrementDisLikes/'+blogId)
		.then(function(response){
			alert("Oops!! Thank you for the feedback");
		},function(errorresponse){
			alert("Error Occurred while disliking the blog");
		});		
	}
	
	$scope.deleteBlog=function(blogId)
	{
		console.log('I am in deleting blog');
		
		$http.get('http://localhost:8081/EduCollaborationMiddleware/deleteBlog/'+blogId)
		.then(function(response){
			alert("Blog is succesfully deleted!!");
			showAllBlogs();
		},function(errorresponse){
			alert("Error Occurred while deleting the blog");
		});		
	}
	
	$scope.editBlog=function(blogId)
	{
		console.log('I am in edit blog');
		
		$http.get('http://localhost:8081/EduCollaborationMiddleware/getBlog/'+blogId)
		.then(function(response){
			$rootScope.blog1=response.data;
			console.log(response.data);
			//console.log('---------');
			console.log($rootScope.blog1);
			$location.path("/editBlog");
		},function(errorresponse){
			alert("Error Occurred while editing the blog");
			showAllBlogs();
		});		
	}
	
	$scope.accept=function(blogId)
	{
		console.log('I am in accept method');
		$http.get('http://localhost:8081/EduCollaborationMiddleware/approveBlog/'+blogId)
		.then(function(response)
		{
			alert("Blog Approved");	
			showAllBlogs();
		});		
	}
	
	$scope.reject=function(blogId)
	{
		console.log('I am in reject method');
		$http.get('http://localhost:8081/EduCollaborationMiddleware/rejectBlog/'+blogId)
		.then(function(response)
		{
			alert("Blog Rejected");	
			showAllBlogs();
		});		
	}
	
	$scope.showComments=function(blogId)
	{
		console.log('I am in reject method');
		
		$rootScope.blogId=blogId;
		$location.path("/blogComments");
	}
	
	function showAllBlogs()
	{
		console.log('I am in show blog');
		
		$http.get('http://localhost:8081/EduCollaborationMiddleware/showAllBlogs')
		.then(function(response){
			$scope.blogDetail=response.data;
		},function(errorresponse){
			alert("Problem Occurred while retrieving blogs");
		});
	}	
	showAllBlogs();
});
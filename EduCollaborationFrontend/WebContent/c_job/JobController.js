myApp.controller("JobController",function($scope,$http,$location,$rootScope)
{
	$scope.job={"jobId":0,"designation":"","description":"","companyName":"","location":"","ctc":0,"lastdateforApply":"","skills":""};
	
	$scope.jobs;
	
	$scope.publishJob=function()
	{
		console.log('I am in publish job function');
		console.log($scope.job);
		
		$http.post('http://localhost:8081/EduCollaborationMiddleware/addJob',$scope.job)
		.then(function(response){
			alert("Job Posted");
		},function(errresponse){
			alert("Error Occurred while posting jobs");
		});
	}
		
	function showAllJobs()
	{
		console.log('I am in ShowJobs');
		
		$http.get('http://localhost:8081/EduCollaborationMiddleware/showAllJobs')
		.then(function(response)
		{
			$scope.jobs=response.data;
		},function(errorresponse){
			alert("Error Occurred while displaying jobs ");
		});	
	}
	showAllJobs();	
});
		
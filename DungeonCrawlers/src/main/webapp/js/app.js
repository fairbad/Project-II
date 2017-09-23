var dndApp = angular.module("dndApp", ["ui.router"]);

dndApp.config(function($stateProvider, $urlRouterProvider){
	console.log("init app");
	
	$stateProvider
	.state("login",{
		url:"/login",
		templateUrl:"templates/login.html",
		controller: "LoginCtrl as login"
	})
	.state("register",{
		url:"/register",
		templateUrl:"templates/register.html",
		controller: "RegisterCtrl as register"
	});
});

dndApp.controller("LoginCtrl", function(UserService, $state){
	console.log("in loginCtrl");
});

dndApp.controller("RegisterCtrl", function(UserService, $state){
	console.log("in registerCtrl");
});

dndApp.controller("NavCtrl", function($state){
	console.log("in navctrl");
})
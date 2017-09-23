/**
 * 
 */
var DungeonCrawlers = angular.module("DungeonCrawlers",["ui.router"]);

DungeonCrawlers.config(function($stateProvider, $urlRouterProvider){
	console.log("init bank app..");

	$stateProvider
	.state("login",{
		url:"/login",
		templateUrl: "templates/login.html",// html 
		controller: "LoginCtrl as login"// ng controller			
	})
	.state("register",{
		url: "/register",
		templateUrl: "templates/register.html",
		controller: "RegisterCtrl as register"
	})
	.state("home",{
		url:"/home",
		templateUrl: "templates/home.html",
		controller: "HomeCtrl as home"
	});	
});

DungeonCrawlers.service("ServiceInterface",function($http,$q){
	console.log("In Service Inside the javaScript");
	var service = this;
	service.user={
			email :"",
			password :"",
			authenticated : false
	};
	service.getUser = function(){
		return service.user;
	};
	service.setUser = function(data){
		service.user.email = data.email;
		service.user.password = data.password;
		service.user.authenticated = data.authenticated;
	};
	service.authenticateUser = function(){
		var promise = $http.post(
				'rest/user/auth', service.user)
				.then(
						function(response){
							console.log(response);
							return response;
						},
						function(error){
							console.log('login promise fail');
						}
				);
		return promise;
	};
	service.registerUser = function(){

		var promise;

		promise = $http.post(
				'rest/user/register',service.user
		).then(// can pass in and register up to three callback functions [success, error, notified(which is sort of like a finally)]
				function(response){
					console.log(response);
					return response;
				},
				function(error){
					console.log('register user promise failed');
					return $q.reject(error);
				}
		);

		return promise;
	};
	
});
DungeonCrawlers.controller("LoginCtrl", function(UserService, $state){
	console.log("in loginctrl");

	var login = this;
	login.user = UserService.getUser();

	login.doLogin = function(){
		console.log("about to authenticate user");
		var promise = UserService.authenticateUser();
	
		promise.then(
				function(response){
					if(response && login.user){
						login.user.authenticated = true;
						ServiceInterface.setUser(response.data);
						console.log("setting user in login ctrl")
						console.log(ServiceInterface.getUser());
						$state.go("home");
					} else{
						alert("Invalid login!");
					}
				},function(error){
					console.log(error);
				});
	
	};
});


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
	})
	.state("home",{
		url:"/home",
		templateUrl:"templates/home.html",
		controller:"HomeCtrl as home"
	});
});

dndApp.service("UserService", function($http, $q){
	console.log("in userService");

	var service = this;

	service.user={
			username : "",
			password : "",
			email : "",
			authenticated : false
	};

	service.getUser= function(){
		return service.user;
	};

	service.setUser = function(data){
		service.user.username = data.username;
		service.user.password = data.password;
		service.user.email = data.email;
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

dndApp.controller("LoginCtrl", function(UserService, $state){
	console.log("in loginCtrl");
	
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

dndApp.controller("RegisterCtrl", function(UserService, $state){
	console.log("in registerctrl");
	var register = this;

	register.user = UserService.getUser();
	register.doRegister = function(){

		var promise = UserService.registerUser();

		promise.then(
				function(response){
					console.log("setting data");
					console.log(response.data);
					UserService.setUser(response.data);
					$state.go('login');
				}, function(error){
					console.log(error);
				}	
		)
	}
});

dndApp.controller("NavCtrl", function($state){
	console.log("in navctrl");
})

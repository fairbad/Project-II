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
	.state("create",{
		url:"/create",
		templateUrl: "templates/create.html"
	})
	.state("create.character",{
		url:"/character",
		templateUrl: "templates/character.html",
		controller: "CharacterCtrl as character"
	})
	.state("create.NPC",{
		url:"/NPC",
		templateUrl: "templates/NPC.html",
		controller: "NPCCtrl as NPC"
	})
	.state("create.enemy",{
		url:"/enemy",
		templateUrl: "templates/enemy.html",
		controller: "EnemyCtrl as enemy"
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

dndApp.controller("CharacterCtrl", function(UserService, $state){
	console.log("in CharacterCtrl");
});

dndApp.controller("NPCCtrl", function(UserService, $state){
	console.log("in NPCCtrl");
});

dndApp.controller("EnemyCtrl", function(UserService, $state){
	console.log("in EnemyCtrl");
});

dndApp.controller("NavCtrl", function($state){
	console.log("in navctrl");
})

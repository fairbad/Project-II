var dndApp = angular.module("dndApp", [ "ui.router" ]);

dndApp.config(function($stateProvider, $urlRouterProvider) {
	console.log("init app");
	
	$stateProvider
	.state("login",{
		url:"/login",
		templateUrl:"templates/login.html",
		controller: "LoginCtrl as login"
	})
	.state("logout",{
		url:"/logout",
		templateUrl:"templates/login.html",
//		controller:"LogoutCtrl as logout"
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
	})
	.state("editUser",{
        url:"/editUser",
        templateUrl:"templates/editUser.html",
        controller:"EditCtrl as editUser"
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
	})
	.state("campaign",{
		url:"/campaign",
		templateUrl: "templates/campaign.html"
	})
	.state("campaign.details",{
		url:"/details",
		templateUrl: "templates/details.html",
		controller: "DetailsCtrl as details"
	})
	.state("campaign.map",{
		url:"/map",
		templateUrl: "templates/map.html",
		controller: "MapCtrl as map"
	})
	.state("campaign.chapter",{
		url:"/chapter",
		templateUrl: "templates/chapter.html",
		controller: "ChapterCtrl as chapter"
	});

});

dndApp.service("UserService", function($http, $q) {
	console.log("in userService");

	var service = this;

	service.user = {
		username : "",
		password : "",
		email : "",
		authenticated : false
	};

	service.getUser = function() {
		return service.user;
	};

	service.setUser = function(data) {
		service.user.username = data.username;
		service.user.password = data.password;
		service.user.email = data.email;
		service.user.authenticated = data.authenticated;
	};

	service.authenticateUser = function() {
		var promise = $http.post('rest/user/auth', service.user).then(
				function(response) {
					console.log("The response in service.authenticateUser");
					console.log(response);
					return response;
				}, function(error) {
					console.log('login promise fail');
				});
		return promise;
	};
	service.logoutUser = function(){
		var promise = $http.post('rest/user/auth', service.user).then(
				function(response) {
					console.log("The response in service.authenticateUser");
					console.log(response);
					return response;
				}, function(error) {
					console.log('logout promise fail');
				});
		return promise;
	};

	service.registerUser = function() {
		var promise;

		promise = $http.post('rest/user/register', service.user).then(
				function(response) {
					console.log(response);
					return response;
				}, function(error) {
					console.log('register user promise failed');
					return $q.reject(error);
				});
		return promise;
	};
	service.homeView = function() {
		var promise;
		promise = $http.get('rest/user/home').then(function(response) {
			console.log(response);
			return response;

		}, function(error) {
			console.log('Show User promise failed');
			return $q.reject(error);
		});
		return promise;
	};

});


dndApp.service("CreatorService", function($http, $q){
	console.log("in creatorService");

	var service = this;

	service.character={
		name : "",
		image : ""
	};

	service.NPC={
		name : "",
		image : ""
	};

	service.enemy={
		name : "",
		image : ""
	};

	service.getCharacter = function(){
		return service.character;
	};

	service.getNPC = function(){
		return service.NPC;
	};

	service.getEnemy = function(){
		return service.Enemy;
	};

	service.setCharacter = function(data){
		service.character.name = data.name;
		service.character.image = data.image;
	};

	service.setNPC = function(data){
		service.NPC.name = data.name;
		service.NPC.image = data.image;
	};

	service.setEnemy = function(data){
		service.enemy.name = data.name;
		service.enemy.image = data.image;
	};

	service.createCharacter = function(){
		var promise = $http.post('rest/creator/createCharacter',
			service.character).then(
				function(response){
					console.log("response service.createCharacter")
					console.log(response);
					return response;
				},
				function(error){
					console.log('createCharacter promise fail');
				}
			);
			return promise;
	};

	service.createNPC = function(){
		var promise = $http.post('rest/creator/createNPC',
			service.NPC).then(
				function(response){
					console.log("response service.createNPC")
					console.log(response);
					return response;
				},
				function(error){
					console.log('createNPC promise fail');
				}
			);
			return promise;
	};

	service.createEnemy = function(){
		console.log(service.character);
		var promise = $http.post('rest/creator/createEnemy',
			service.enemy).then(
				function(response){
					console.log("response service.createEnemy")
					console.log(response);
					return response;
				},
				function(error){
					console.log('createEnemy promise fail');
				}
			);
			return promise;
	};
});

dndApp.controller("LoginCtrl", function(UserService, $state) {
	console.log("in loginctrl");

	var login = this;
	login.user = UserService.getUser();
	console.log("Logged in user: ")
	console.log(login.user);

	login.doLogin = function() {
		console.log("about to authenticate user");
		var promise = UserService.authenticateUser();

		promise.then(function(response) {
			console.log(response);
			if (login.user && response.data) {
				login.user.authenticated = true;
				console.log(response.data);
				UserService.setUser(response.data);
				console.log("setting user in login ctrl")
				console.log(UserService.getUser());
				$state.go("home");
			} else {
				alert("Invalid login!");
			}
		}, function(error) {
			console.log(error);
		});

	};
});
dndApp.controller("LogoutCtrl", function(UserService, $state) {
	console.log("in logoutctrl");

	var logout = this;
	login.user = UserService.getUser();
	console.log("Logging out user: ")
	console.log(login.user);

	login.doLogin = function() {
		console.log("about to de-authenticate user");
		var promise = UserService.authenticateUser(false);

		promise.then(function(response) {
			if (logout.user && response.data) {
				logout.user.authenticated = false;
				console.log(response.data);
				UserService.setUser(null);
				console.log("setting user in login ctrl")
				console.log(UserService.getUser());
				$state.go("login");
			} else {
				alert("Invalid login!");
			}
		}, function(error) {
			console.log(error);
		});

	};
});

dndApp.controller("RegisterCtrl", function(UserService, $state) {
	console.log("in registerctrl");
	var register = this;

	register.user = UserService.getUser();
	register.doRegister = function() {

		var promise = UserService.registerUser();
		// if()

		promise.then(
		/*
		 * if(register.user && response.data){ alert("User Already Exist"); }
		 */
		function(response) {
			console.log("setting data");
			console.log(response.data);
			UserService.setUser(response.data);
			$state.go("home");
		}, function(error) {
			console.log(error);
		})
	}
});
dndApp.controller("HomeCtrl", function(UserService, $state) {
	console.log("in the Home Control")
	var home = this;
	home.user = UserService.getUser();
});

dndApp.controller("CharacterCtrl", function(CreatorService, $state){
	console.log("in CharacterCtrl");

	var character = this;
	character.character = CreatorService.getCharacter();
	character.createCharacter = function(){

		var promise = CreatorService.createCharacter();
		console.log(promise);

		promise.then(
			function(response){
				console.log("setting character data");
				console.log(response.data);
				CreatorService.setCharacter(response.data);
			}, function(error){
				console.log(error);
			}
		)
	}
});


dndApp.controller("NPCCtrl", function(CreatorService, $state){
	console.log("in NPCCtrl");

	var NPC = this;

	NPC.NPC = CreatorService.getNPC();
	NPC.createNPC = function(){

		var promise = CreatorService.createNPC();

		promise.then(
			function(response){
				console.log("setting NPC data");
				console.log(response.data);
				CreatorService.setNPC(response.data);
			}, function(error){
				console.log(error);
			}
		)
	}
});


dndApp.controller("EnemyCtrl", function(CreatorService, $state){
	console.log("in EnemyCtrl");

	var enemy = this;

	enemy.enemy = CreatorService.getEnemy();
	enemy.createEnemy = function(){

		var promise = CreatorService.createEnemy();

		promise.then(
			function(response){
				console.log("setting Enemy data");
				console.log(response.data);
				CreatorService.setEnemy(response.data);
			}, function(error){
				console.log(error);
			}
		)
	}
});

dndApp.controller("NavCtrl", function($state) {
	console.log("in navctrl");
});

dndApp.controller("DetailsCtrl", function(UserService, $state){
	console.log("in CampaignCtrl");
});

dndApp.controller("MapCtrl", function(UserService, $state){
	console.log("in MapCtrl");
});

dndApp.controller("ChapterCtrl", function(UserService, $state){
	console.log("in ChapterCtrl");
});


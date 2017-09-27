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
		//templateUrl:"templates/login.html",
		controller:"LogoutCtrl as logout"
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
        controller:"EditCtrl as edit"
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
	.state("viewCreations",{
		url:"/viewCreation",
		templateUrl: "templates/viewCreations.html"
	})
	.state("viewCreations.characters",{
		url:"/characters",
		templateUrl: "templates/viewCharacters.html",
		controller: "ViewCharactersCtrl as viewCharacters"
	})
	.state("viewCreations.NPCs",{
		url:"/NPCs",
		templateUrl: "templates/viewNPCs.html",
		controller: "ViewNPCsCtrl as viewNPCs"
	})
	.state("viewCreations.enemies",{
		url:"/enemies",
		templateUrl: "templates/viewEnemies.html",
		controller: "ViewEnemiesCtrl as viewEnemies"
	})
	.state("deleteCreation",{
		url:"/deleteCreation",
		templateUrl: "templates/deleteCreation.html"
	})
	.state("deleteCreation.character",{
		url:"/character",
		templateUrl: "templates/deleteCharacter.html",
		controller: "DeleteCharacterCtrl as deleteCharacter"
	})
	.state("deleteCreation.NPC",{
		url:"/NPC",
		templateUrl: "templates/deleteNPC.html",
		controller: "DeleteNPCCtrl as deleteNPC"
	})
	.state("deleteCreation.enemy",{
		url:"/enemy",
		templateUrl: "templates/deleteEnemy.html",
		controller: "DeleteEnemyCtrl as deleteEnemy"
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
		id : "",
		username : "",
		password : "",
		email : "",
		authenticated : false
	};

	service.getUser = function() {
		return service.user;
	};

	service.setUser = function(data) {
		service.user.id = data.id;
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
		var promise = $http.post('rest/user/logout', service.user).then(
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
	service.editUser = function(){
		var promise;
		console.log(service.user);
		promise = $http.post('rest/user/editUser', service.user).then(
				function(response){
					console.log(response);
					return response;
				}, function(error){
					console.log('editUser promise failed');
					//console.log(edit.user);
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

	service.characters = [];
	service.NPCs = [];
	service.enemies = [];

	service.getCharacter = function(){
		return service.character;
	};

	service.getNPC = function(){
		return service.NPC;
	};

	service.getEnemy = function(){
		return service.enemy;
	};

	service.createCharacter = function(){
		var promise = $http.post('rest/creator/createCharacter',
			service.character).then(
				function(response){
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
					return response;
				},
				function(error){
					console.log('createNPC promise fail');
				}
			);
		return promise;
	};

	service.createEnemy = function(){
		var promise = $http.post('rest/creator/createEnemy',
			service.enemy).then(
				function(response){
					return response;
				},
				function(error){
					console.log('createEnemy promise fail');
				}
			);
		return promise;
	};

	service.getCharacters = function(){
		var promise = $http.get('rest/creator/getCharacters',
			service.characters).then(
				function(response){
					return response;
				},
				function(error){
					console.log("get all characters failed")
				}
			);
		return promise;
	};

	service.getNPCs = function(){
		var promise = $http.get('rest/creator/getNPCs',
			service.NPCs).then(
				function(response){
					return response;
				},
				function(error){
					console.log("get all NPC failed")
				}
			);
		return promise;
	}

	service.getEnemies = function(){
		var promise = $http.get('rest/creator/getEnemies',
			service.enemies).then(
				function(response){
					return response;
				},
				function(error){
					console.log("get all enemies failed")
				}
			);
		return promise;
	}

	service.deleteCharacter = function(character){
		service.character = character;
		var promise = $http.post('rest/creator/deleteCharacter',
			service.character).then(
				function(response){
					return response;
				},
				function(error){
					console.log("failed to delete")
				}
			);	
		return promise;
	}

	service.deleteNPC = function(){
		var promise = $http.post('rest/creator/deleteNPC',
			service.NPC).then(
				function(response){
					return response;
				},
				function(error){
					console.log("failed to delete")
				}
			);	
		return promise;
	}

	service.deleteEnemy = function(){
		var promise = $http.post('rest/creator/deleteEnemy',
			service.enemy).then(
				function(response){
					return response;
				},
				function(error){
					console.log("failed to delete")
				}
			);	
		return promise;
	}
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
				console.log("Response data passed in")
				console.log(response.data);
				console.log("This is where my data is being lost")
				UserService.setUser(response.data);
				console.log(UserService.getUser());
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
	logout.user = UserService.getUser();
	console.log("Logging out user: ")
	console.log(logout.user);

	logout.doLogout = function() {
		console.log("about to de-authenticate user");
		var promise = UserService.authenticateUser(false);

		promise.then(function(response) {
			if (logout.user && response.data) {
				console.log("In the function")
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
dndApp.controller("EditCtrl", function(UserService, $state){
	var edit = this;
	edit.user = UserService.getUser();
	console.log("Inside the Edit user Contontroller")
	console.log(edit.user)
	edit.doEdit = function(){
		var promise = UserService.editUser();
		promise.then(function(response){
			console.log("editing Data");
			console.log(response.data);
			UserService.setUser(response.data);
			$state.go("home");
		}, function(error){
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
	var character = this;
	character.character = CreatorService.getCharacter();
	character.createCharacter = function(){
		var promise = CreatorService.createCharacter();
	}
});

dndApp.controller("NPCCtrl", function(CreatorService, $state){
	var NPC = this;
	NPC.NPC = CreatorService.getNPC();
	NPC.createNPC = function(){
		var promise = CreatorService.createNPC();
	}
});

dndApp.controller("EnemyCtrl", function(CreatorService, $state){
	var enemy = this;
	enemy.enemy = CreatorService.getEnemy();
	enemy.createEnemy = function(){
		var promise = CreatorService.createEnemy();
	}
});

dndApp.controller("ViewCharactersCtrl",function(CreatorService, $state, $scope){
	var promise = CreatorService.getCharacters();
	promise.then(
		function(response){
			$scope.characters = response.data;
	})
});

dndApp.controller("ViewNPCsCtrl",function(CreatorService, $state, $scope){
	var promise = CreatorService.getNPCs();
	promise.then(
		function(response){
			$scope.NPCs = response.data;
	})
});

dndApp.controller("ViewEnemiesCtrl",function(CreatorService, $state, $scope){
	var promise = CreatorService.getEnemies();
	promise.then(
		function(response){
			$scope.enemies = response.data;
	})
});

dndApp.controller("DeleteCharacterCtrl",function(CreatorService, $state, $scope){
	var promise = CreatorService.getCharacters();
	promise.then(
		function(response){
			$scope.characters = response.data;
			console.log($scope.characters);
	})
	$scope.change = function(){
		console.log($scope.character.id);
	}
//	$scope.delete = function(){
//		CreatorService.deleteCharacter($scope.character);
//		
//	}
});

dndApp.controller("DeleteNPCCtrl",function(CreatorService, $state, $scope){
	var promise = CreatorService.getNPCs();
	promise.then(
		function(response){
			$scope.NPCs = response.data;
	})
});

dndApp.controller("DeleteEnemyCtrl",function(CreatorService, $state, $scope){
	var promise = CreatorService.getEnemies();
	promise.then(
		function(response){
			$scope.enemies = response.data;
	})
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

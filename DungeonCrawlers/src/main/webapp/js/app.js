var dndApp = angular.module("dndApp", [ "ui.router", "ngTable" ]);

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
	.state("viewCreations",{
		url:"/viewCreations",
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
	.state("editCreation",{
		url:"/editCreation",
		templateUrl: "templates/editCreation.html"
	})
	.state("editCreation.character",{
		url:"/character",
		templateUrl: "templates/editCharacter.html",
		controller: "EditCharacterCtrl as editCharacter"
	})
	.state("editCreation.NPC",{
		url:"/NPC",
		templateUrl: "templates/editNPC.html",
		controller: "EditNPCCtrl as editNPC"
	})
	.state("editCreation.enemy",{
		url:"/enemy",
		templateUrl: "templates/editEnemy.html",
		controller: "EditEnemyCtrl as editEnemy"
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
	})
	.state("campaign.location",{
		url:"/location",
		templateUrl: "templates/location.html",
		controller: "LocationCtrl as location"
	})
	.state("campaign.event",{
		url:"/event",
		templateUrl: "templates/event.html",
		controller: "EventCtrl as event"
	})
	.state("viewPublicCampaigns",{
		url:"/viewPublicCampaigns",
		templateUrl: "templates/viewPublicCampaigns.html",
		controller: "ViewPublicCampaignsCtrl as viewPublicCampaigns"
	})
	.state("viewCampaigns",{
		url:"/campaigns",
		templateUrl: "templates/viewCampaigns.html",
		controller: "ViewCampaignsCtrl as viewCampaigns"
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

	service.editCharacter = function(character){
		service.character = character;
		var promise = $http.post('rest/creator/editCharacter',
			service.character).then(
				function(response){
					return response;
				},
				function(error){
					console.log('editCharacter promise fail');
				}
			);
		return promise;
	};

	service.editNPC = function(NPC){
		service.NPC = NPC;
		var promise = $http.post('rest/creator/editNPC',
			service.NPC).then(
				function(response){
					return response;
				},
				function(error){
					console.log('editNPC promise fail');
				}
			);
		return promise;
	};

	service.editEnemy = function(enemy){
		service.enemy = enemy;
		var promise = $http.post('rest/creator/editEnemy',
			service.enemy).then(
				function(response){
					return response;
				},
				function(error){
					console.log('editEnemy promise fail');
				}
			);
		return promise;
	};

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

	service.deleteNPC = function(NPC){
		service.NPC = NPC;
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

	service.deleteEnemy = function(enemy){
		service.enemy = enemy;
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

dndApp.service("CampaignService", function($http, $q){
	console.log("in campaignService");

	var service = this;

	service.campaign={
		name : "",
		desc : "",
		image : ""
	};

	service.map={
		name : "",
		desc : "",
		image : ""
	};

	service.chapter={
		name : "",
		desc : "",
		image : ""
	};
	
	service.location={
			name : "",
			desc : "",
			image : ""
	};
	
	service.event={
			name : "",
			desc : "",
			image : ""
	};

	service.getCampaign = function(){
		return service.campaign;
	};

	service.getMap = function(){
		return service.map;
	};

	service.getChapter = function(){
		return service.chapter;
	};
	
	service.getLocation = function(){
		return service.location;
	};
	
	service.getEvent = function(){
		return service.event;
	};
	
	service.getCampaigns = function(){
		var promise = $http.get('rest/campaign/getCampaigns',
			service.campaigns).then(
				function(response){
					return response;
				},
				function(error){
					console.log("get all campaigns failed")
				}
			);
		return promise;
	};

	service.setCampaign = function(data){
		service.campaign.name = data.name;
		service.campaign.desc = data.description;
		service.campaign.image = data.image;
	};

	service.setMap = function(data){
		service.map.name = data.name;
		service.map.desc = data.description;
		service.map.image = data.image;
	};

	service.setChapter = function(data){
		service.chapter.name = data.name;
		service.chapter.desc = data.description;
		service.chapter.image = data.image;
	};
	
	service.setLocation = function(data){
		service.location.name = data.name;
		service.location.desc = data.description;
		service.location.image = data.image;
	};
	
	service.setEvent = function(data){
		service.event.name = data.name;
		service.event.desc = data.description;
		service.event.image = data.image;
	};

	service.createCampaign = function(){
		var promise = $http.post('rest/campaign/details',
			service.campaign).then(
				function(response){
					console.log("response service.createCampaign")
					console.log(response);
					return response;
				},
				function(error){
					console.log('createCampaign promise fail');
				}
			);
			return promise;
	};

	service.createMap = function(){
		var promise = $http.post('rest/campaign/map',
			service.map).then(
				function(response){
					console.log("response service.createMap")
					console.log(response);
					return response;
				},
				function(error){
					console.log('createMap promise fail');
				}
			);
			return promise;
	};

	service.createChapter = function(){
		console.log(service.chapter);
		var promise = $http.post('rest/campaign/chapter',
			service.chapter).then(
				function(response){
					console.log("response service.createChapter")
					console.log(response);
					return response;
				},
				function(error){
					console.log('createChapter promise fail');
				}
			);
			return promise;
	};
	
	service.createLocation = function(){
		console.log(service.location);
		var promise = $http.post('rest/campaign/location',
			service.location).then(
				function(response){
					console.log("response service.createLocation")
					console.log(response);
					return response;
				},
				function(error){
					console.log('createLocation promise fail');
				}
			);
			return promise;
	};
	
	service.createEvent = function(){
		console.log(service.event);
		var promise = $http.post('rest/campaign/event',
			service.event).then(
				function(response){
					console.log("response service.createEvent")
					console.log(response);
					return response;
				},
				function(error){
					console.log('createEvent promise fail');
				}
			);
			return promise;
	};
});


dndApp.service("CommunityService", function($http, $q){
	console.log("in CommunityService");

	var service = this;

	service.campaigns = [];

	service.getPublicCampaigns = function(){
		var promise = $http.get('rest/community/getPublicCampaigns',
			service.campaigns).then(
				function(response){
					return response;
				},
				function(error){
					console.log("get all public campaigns failed")
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

dndApp.controller("EditCharacterCtrl",function(CreatorService, $state, $scope){
	var promise = CreatorService.getCharacters();
	promise.then(
		function(response){
			$scope.characters = response.data;
	})
	$scope.update = function(){
		CreatorService.editCharacter($scope.character);
	}
});

dndApp.controller("EditNPCCtrl",function(CreatorService, $state, $scope){
	var promise = CreatorService.getNPCs();
	promise.then(
		function(response){
			$scope.NPCs = response.data;
	})
	$scope.update = function(){
		CreatorService.editNPC($scope.NPC);
	}
});

dndApp.controller("EditEnemyCtrl",function(CreatorService, $state, $scope){
	var promise = CreatorService.getEnemies();
	promise.then(
		function(response){
			$scope.enemies = response.data;
	})
	$scope.update = function(){
		CreatorService.editEnemy($scope.enemy);
	}
});

dndApp.controller("DeleteCharacterCtrl",function(CreatorService, $state, $scope){
	var promise = CreatorService.getCharacters();
	promise.then(
		function(response){
			$scope.characters = response.data;
	})
	$scope.delete = function(){
		CreatorService.deleteCharacter($scope.character);
		var index = $scope.characters.indexOf($scope.character);
		$scope.characters.splice(index, 1);
	}
});

dndApp.controller("DeleteNPCCtrl",function(CreatorService, $state, $scope){
	var promise = CreatorService.getNPCs();
	promise.then(
		function(response){
			$scope.NPCs = response.data;
	})
	$scope.delete = function(){
		CreatorService.deleteNPC($scope.NPC);
		var index = $scope.NPCs.indexOf($scope.NPC);
		$scope.NPCs.splice(index, 1);
	}
});

dndApp.controller("DeleteEnemyCtrl",function(CreatorService, $state, $scope){
	var promise = CreatorService.getEnemies();
	promise.then(
		function(response){
			$scope.enemies = response.data;
	})
	$scope.delete = function(){
		CreatorService.deleteEnemy($scope.enemy);
		var index = $scope.enemies.indexOf($scope.enemy);
		$scope.enemies.splice(index, 1);
	}
});

dndApp.controller("DetailsCtrl", function(CampaignService, $state){
	console.log("in DetailsCtrl");

	var campaign = this;
	campaign.campaign = CampaignService.getCampaign();
	campaign.createCampaign = function(){
		
		var promise = CampaignService.createCampaign();
		console.log(promise);

		promise.then(
			function(response){
				console.log("setting campaign data");
				console.log(response.data);
				CampaignService.setCampaign(response.data);
			}, function(error){
				console.log(error);
			});
	};
});

dndApp.controller("MapCtrl", function(CampaignService, $state){
	console.log("in MapCtrl");
	
	var map = this;
	map.map = CampaignService.getMap();
	map.createMap = function(){
		
		var promise = CampaignService.createMap();
		console.log(promise);

		promise.then(
			function(response){
				console.log("setting map data");
				console.log(response.data);
				CampaignService.setMap(response.data);
			}, function(error){
				console.log(error);
			});
	};
});

dndApp.controller("ChapterCtrl", function(CampaignService, $state){
	console.log("in ChapterCtrl");
	
	var chapter = this;
	chapter.chapter = CampaignService.getChapter();
	chapter.createChapter = function(){
		
		var promise = CampaignService.createChapter();
		console.log(promise);

		promise.then(
			function(response){
				console.log("setting chapter data");
				console.log(response.data);
				CampaignService.setChapter(response.data);
			}, function(error){
				console.log(error);
			});
	};
});

dndApp.controller("LocationCtrl", function(CampaignService, $state){
	console.log("in LocationCtrl");
	
	var location = this;
	location.location = CampaignService.getLocation();
	location.createLocation = function(){
		
		var promise = CampaignService.createLocation();
		console.log(promise);

		promise.then(
			function(response){
				console.log("setting location data");
				console.log(response.data);
				CampaignService.setLocation(response.data);
			}, function(error){
				console.log(error);
			});
	};
});

dndApp.controller("EventCtrl", function(CampaignService, $state){
	console.log("in EventCtrl");
	
	var event = this;
	event.event = CampaignService.getEvent();
	event.createEvent = function(){
		
		var promise = CampaignService.createEvent();
		console.log(promise);

		promise.then(
			function(response){
				console.log("setting event data");
				console.log(response.data);
				CampaignService.setEvent(response.data);
			}, function(error){
				console.log(error);
			});
	};
});

dndApp.controller("ViewPublicCampaignsCtrl", function(NgTableParams, CommunityService, $state, $scope){
	var promise = CommunityService.getPublicCampaigns();
	promise.then(
		function(response){
			console.log(response.data);
			$scope.campaigns = response.data;
			//$scope.publicCampaigns = new NgTableParams({sorting: {name: "asc"}}, {dataset: $scope.campaigns});
			$scope.publicCampaigns = createUsingFullOptions();
			function createUsingFullOptions(){
				"use strict";
				 var initialParams = {
					sorting: {name: "asc"},
        			count: 10
      			};
				var initialSettings = {
					//counts: [],
					dataset: $scope.campaigns
				};
				return new NgTableParams(initialParams, initialSettings);
			}
	})
	$scope.getCampaign = function(campaign){
		console.log(campaign);
	}
});

dndApp.controller("ViewCampaignsCtrl",function(CampaignService, $state, $scope){
	var promise = CampaignService.getCampaigns();
	promise.then(
		function(response){
			$scope.campaigns = response.data;
	})
});

dndApp.controller("NavCtrl", function($state) {
	console.log("in navctrl");
});
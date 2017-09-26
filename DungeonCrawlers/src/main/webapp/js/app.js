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
		'rest/user/auth', service.user
		).then(
			function(response){
				console.log("The response in service.authenticateUser");
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
		).then(
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
	
	service.homeView = function(){
        var promise;
        promise = $http.get('rest/user/home').then(function(response){
            console.log(response);
            return response;
            
        }, function(error){
            console.log('Show User promise failed');
            return $q.reject(error);
        });
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
				$state.go("home");
			}, function(error){
				console.log(error);
			}	
		)
	}
});

dndApp.controller("HomeCtrl",function(UserService,$state){
    console.log("in the Home Control")
    var home = this;
    home.user = UserService.getUser();
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


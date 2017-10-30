(function() {

	var ChatApp = angular.module("ChatApp", []);


	var ChatCtrl = function($scope) {
		var chatCtrl = this;

		var socket = null;

		chatCtrl.message = "";
		chatCtrl.messages = [];
		chatCtrl.send = function() {
			socket.send(chatCtrl.message);
			chatCtrl.message = "";
		}

		socket = new WebSocket("ws://localhost:8080/week4/chat");
		socket.onopen = function() {
			$scope.$apply(function() {
				chatCtrl.messages.unshift("Connected to server");
			});
		}
		socket.onclose = function() {
			$scope.$apply(function() {
				chatCtrl.messages.unshift("Terminating connection");
			});
		}
		socket.onmessage = function(event) {
			$scope.$apply(function() {
				chatCtrl.messages.unshift(event.data);
			});
		}
	}

	ChatApp.controller("ChatCtrl", ["$scope", ChatCtrl]);

})();


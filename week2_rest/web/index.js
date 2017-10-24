//IIFE
(function() {

	var GiphyApp = angular.module("GiphyApp", []);

	var GiphyCtrl = function($http) {
		var giphyCtrl = this;

		giphyCtrl.searchText = "";

		giphyCtrl.images = [];

		giphyCtrl.search = function() {
			giphyCtrl.images = [];
			$http.get("giphy", { params: { q: giphyCtrl.searchText } })
				.then(function(result) {
					giphyCtrl.images = result.data;
				})
		}
	}

	GiphyApp.controller("GiphyCtrl", ["$http", GiphyCtrl]);

})();
var source = undefined;
getEventSource(USERNAME);

function getEventSource(username){
	
	source = new EventSource("/users/submit?name=" + username);
	userName = username;
	
	source.addEventListener("message", function(event){
		var response = JSON.parse(event.data);				
		append(receivedMessage(response.userName, response.message, formatData(response.instant)));
	});
	
	source.addEventListener("login", function(event){
		var response = event.data;				
		append(loggedIn(response));
	});
	
	source.addEventListener("logout", function(event){
		var response = event.data.userName;				
		append(loggedIn(response));
	});
	
	source.onerror = function (){
		source.close();
		getEventSource(getUsername());
	};

}

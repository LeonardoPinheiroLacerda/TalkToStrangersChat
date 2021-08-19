function sentMessage(name, message, instant){
	
	var container = document.createElement("div");
	container.id = "sentMessage";
	
	var nameF = document.createElement("label");
	nameF.id = "name";
	nameF.innerHTML = name;
	
	var messageF = document.createElement("label");
	messageF.id = "message";
	messageF.innerHTML = message;
	
	var time = document.createElement("label");
	time.id = "time";
	time.innerHTML = instant;
	
	container.appendChild(nameF);
	container.appendChild(messageF);
	container.appendChild(time);
	
	return container;
}

function receivedMessage(name, message, instant){
	var container = document.createElement("div");
	container.id = "recievedMessage";
	
	var nameF = document.createElement("label");
	nameF.id = "name";
	nameF.innerHTML = name;
	
	var messageF = document.createElement("label");
	messageF.id = "message";
	messageF.innerHTML = message;
	
	var time = document.createElement("label");
	time.id = "time";
	time.innerHTML = instant;
	
	container.appendChild(nameF);
	container.appendChild(messageF);
	container.appendChild(time);
	
	return container;
}

function loggedIn(name){
	var container = document.createElement("div");
	container.id = "loggedIn";
	var label = document.createElement("label");
	label.innerHTML = name + " entrou.";
	container.appendChild(label);
	return container;
}

function loggedOut(name){
	var container = document.createElement("div");
	container.id = "loggedIn";
	var label = document.createElement("label");
	label.innerHTML = name + " loggedOut.";
	container.appendChild(label);
	return container;
}
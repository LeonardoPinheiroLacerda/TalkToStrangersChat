const USERNAME = getUsername();
function getUsername(){
	var params = new URLSearchParams(document.location.search);
    var name = params.get('name');
    return name;
}

const messageBox = document.getElementById("mensagens");

function send(){		
	var input = document.getElementById("mensagem");
	var message  = input.value;
	
	var request = new XMLHttpRequest();
	request.open("POST", "/messages/send");
	request.setRequestHeader("content-type", "application/json;charset=UTF-8");
	
	request.onreadystatechange = function(){
		if(request.readyState === XMLHttpRequest.DONE && request.status === 200) {
			var json = JSON.parse(request.response);			
    		append(sentMessage(json.userName, json.message, formatData(json.instant)));    		
 		}
	}
	
	request.send(JSON.stringify({
		"userName" : USERNAME,
		"message" : message,
		"instant" : null
	}));
	
	input.value = "";
	input.focus();
}

function receiveMessage(message){
	var p = document.createElement("p");
	p.innerHTML = message;
	document.body.appendChild(p);
}

function append(dom){
	messageBox.appendChild(dom);
}

function formatData(instant){
	return instant.substring(0, 5);
}

var input = document.getElementById("mensagem");
input.focus();
input.onkeyup = function(event){
	if(event.key === "Enter"){
		send();
	}
}
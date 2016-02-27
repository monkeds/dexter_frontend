var wsUri = getServerRootUri() + "/chapter4/chat-server";
var currentUsername = "";
var chatWebsocket;




function getServerRootUri() {
//    return "ws://" + (document.location.hostname == "" ? "localhost" : document.location.hostname) + ":" +
//        (document.location.port == "" ? "8080" : document.location.port);
	return "ws://" + (document.location.hostname == "" ? "localhost" : document.location.hostname) + ":8080";
}


function signinChat(idRoom) {
	currentUsername = _ID_USER; /* se obtiene de global.js. en login */
    if (currentUsername != "") {
        chatWebsocket = new WebSocket(wsUri+"/"+idRoom, "chat");
        chatWebsocket.onopen = function (evt) {
            chatWebsocket.send("unmsg" + currentUsername);
        };
        chatWebsocket.onmessage = function (evt) {
            onMessage(evt)
        };
        chatWebsocket.onerror = function (evt) {
            onError(evt)
        };
        chatWebsocket.onclose = function (evt) {
            onClose(evt);
        }
    }
};


function onMessage(evt) {
    var mString = evt.data.toString();
    if (mString.search("unmsg") == 0) {
        currentUsername = mString.substring(5, mString.length);
    }
    if (mString.search("ctmsg") == 0) {
        var transcriptUpdate = mString.substring(8, mString.length);
        console.log("mensaje recibido "+transcriptUpdate);
        writeTranscript(transcriptUpdate);
        posicionarScroll();
    }
    if (mString.search("ulupd") == 0) {
        var updateString = mString.substring(8, mString.length);
        writeUserlist(updateString);
    }
}


function onError(evt) {
    alert("Error: " + evt.data);
}

function onClose(evt) {
    currentUsername = "";
}


function writeTranscript(str)  {
//    var index = str.search(":");
//    var username = str.substring(0, index);
//    var message = str.substring(index+1, str.length);
	var splitter = str.split(":::");
	var username = splitter[0];
    var message = splitter[1];
    var time = splitter[2];
    console.log("el user es "+username+" / el currentuser es "+currentUsername);
    if(message==" just joined."){
    	printEvent(username);
    	posicionarScroll();
    }else{
    	if(currentUsername==username){
    		printSelfMessage(message,time);
    	}else{
    		printMessage(username, message,time);
    	}
    	posicionarScroll();
    }
}


function printEvent(currentUsername,message){
	$('#chatContainer .messages').append('<div class="event"><b>'+currentUsername+'</b> se ha unido</div>');
}


function printMessage(currentUsername, message, time){
	$('#chatContainer .messages').append('<li class="other">'
			+'<div class="avatar"></div>'
			+'<div class="msg">'
				+'<span class="from">'+currentUsername+'</span>'
				+'<p>'+message+'</p>'
				+'<time>'+time+'</time>'
			+'</div>'
		+'</li>');
}

function printSelfMessage( message, time){
	$('#chatContainer .messages').append('<li class="self">'
			+'<div class="avatar"></div>'
			+'<div class="msg">'
				+'<p>'+message+'</p>'
				+'<time>'+time+'</time>' 
			+'</div>'
		+'</li>');
}



function writeUserlist(rawStr) {
    var indexOfNext = -1;
    var remaining = rawStr;
    var usernames = new Array();
    while (remaining.search(":::") != -1) {
        var index = remaining.search(":::")+2;
        var nextPiece = remaining.substring(0, index);
        usernames.push(nextPiece);
        remaining = remaining.substring(index + 1, remaining.length);
    }
    usernames.push(remaining);
//    userListID.textContent = "";
//    var i = 0;
//    for (i = 0; i < usernames.length; i++) {
//        userListID.textContent = userListID.textContent + usernames[i];
//        if (i < (usernames.length - 1)) {
//            userListID.textContent = userListID.textContent + "\n";
//        }
//    }
}

function goodbye() {
    chatWebsocket.close();
}

//window.addEventListener("load", init, false);
window.addEventListener('beforeunload', goodbye, false);


function posicionarScroll(){
	console.log('moviendo scroll');
//	$("#chat").scrollTop($("#chat")[0].scrollHeight);
//	
	$('#chatContainer').scrollTop($('#chatContainer')[0].scrollHeight);
	
//	$("html").scrollTop($("html")[0].scrollHeight); no
}




function button_sendMessage() {
    var chatString = chatMessageTextID.value;
    if (chatString.length > 0) {
        chatWebsocket.send('ctmsg' + currentUsername + ':::' + chatString);
        chatMessageTextID.value = "";
    }
    console.log('mensaje enviado '+chatString);
    event.preventDefault();
    return false;
}
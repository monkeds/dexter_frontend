//eventos
var _ID_USER;

$('document').ready(function(){
	
	loadComponents();
	
	$('#btn_login').click(function(){
		email = getEmail_login();
		password = getPassword_login()
		login(email,password);
	});
	
	$('#btn_logout').click(function(){
		logout();
	});
	
	$('.cnt_contacts').on('click', '.contact .content' ,function(){
		var code_room = $(this).parent().attr('mkd_room');
		var code_concat = $(this).parent().attr('mkd_contact'); /* temporalmente enviar'e el codigo de contaacto */
		goChat(code_concat,code_room);
	});
	
	$('#chatContainer .btn_backPrincipal').click(function(){
		backPrincpal();
	});
	
	
	$('#chatContainer .btn_backPrincipal').click(function(){
		backPrincpal();
	});
	
	$('#chatContainer .btn_backPrincipal').click(function(){
		backPrincpal();
	});
	
	$('#chatContainer .btn_backPrincipal').click(function(){
		backPrincpal();
	});
	
	$('#btn_openAddContact').click(function(){
		openModalAddContact();
	});
	
	$('#btn_openChangeNick').click(function(){
		openModalChangeNick();
	});
	
	$('#btn_openSignUp').click(function(){
		openModalSignUp();
	});
	
	
	$('#btn_signUp').click(function(){
		signUp();
	});
	
	
	$('.cnt_contacts').on('click', '.contact .avatar' ,function(){
		loadContactInfo($(this).parent().attr('mkd_contact'));
	});
	
});




function login(email,password){
	console.log('ingresando '+email+' / '+password);
	$.ajax({
        type: 'POST',
        data: {email : email, password : password },
        url:  'ajax/login',
		dataType: 'json',
        async: true,
        success: function(result) {
//			console.log('user = ' + JSON.stringify(result));
//			buildRoomList(result.roomList);
//			displayScreen('PRINCIPAL');
//			_ID_USER = result.id;
        	if(result.ajaxMessage==null){
        		displayScreen('PRINCIPAL');
        		_ID_USER = result.id;
        	}else{
        		showMessage('INFO','Error al ingresar',result.ajaxMessage);
        	}
        },
        error: function(jqXHR,exception) { 
        	showMessage('ERROR','Error al ingresar',formatErrorMessage(jqXHR,exception));
//        	alert("ERROR "+formatErrorMessage(jqXHR,exception))
        },
        beforeSend: function () {  },           
        complete: function () {  }, 
    });
}


function signUp(){
	var formSelector = '#frm_signUp';
	var json = JSON.stringify($(formSelector).serializeObject());
	var json =  JSON.parse(json);
//	var data = {email : "12", password : "12", nick :"12" };
//	console.log("json "+json+" / "+json.to);
//	console.log("data "+data);
	$.ajax({
        type: 'POST',
//        data: {user:json},
//        data: {email : "12", password : "12", nick :"12" },
        data: json,
        url:  'ajax/signUp',
		dataType: 'json',
        async: true,
        success: function(result) {
        	console.log("SUCCESS "+result.ajaxMessage);
        	showMessage('INFO','Registro exitoso',result.ajaxMessage);
        	clearForm(formSelector);
        	closeModalSignUp();
        },
        error: function(jqXHR,exception) {
        	showMessage('ERROR','Error al registrar usuario ',formatErrorMessage(jqXHR,exception));
        },
        beforeSend: function () {  },           
        complete: function () {  }, 
    });
}


function logout(){
//	funcionalidad pendiente
	email = getEmail_login();
	console.log('cerrando sesion '+email);
	displayScreen('LOGIN');
}

function goChat(idContact,idRoom){
	console.log('ingresando al chat '+idRoom+ " / receptor "+idContact);
	$.ajax({
        type: 'GET',
        data: {id : idContact },
        url:  'chatController/ajax/detailContact',
		dataType: 'json',
        async: true,
        success: function(result) {
			console.log('user = ' + JSON.stringify(result));
			loadChatScreen(result,idRoom);
        },
        error: function(jqXHR,exception) { 
        	console.log(formatErrorMessage(jqXHR,exception));
        	showMessage('INFO','Error',formatErrorMessage(jqXHR,exception));
        },
        beforeSend: function () {  },           
        complete: function () {  }, 
    });
	
}

function backPrincpal(){
	console.log('regresando al principal');
	displayScreen('PRINCIPAL');
}


function loadChatScreen(contact, idRoom){
	displayScreen('CHAT');
	$("#chatContainer .contactInfo .header").text(contact.nick);
	$("#chatContainer .contactInfo span").text(contact.email);
	
	signinChat(idRoom);
	
}


function displayScreen(screen){
	console.log('mostrando '+screen);
	$('#loginContainer').addClass('hidden');
	$('#principalContainer').addClass('hidden');
	$('#chatContainer').addClass('hidden');
	
	if (screen=='PRINCIPAL'){
		$('#principalContainer').removeAttr('class');
	}else if (screen=='CHAT'){
		$('#chatContainer').removeAttr('class');
	}else if (screen=='LOGIN'){
		$('#loginContainer').removeAttr('class');
	}
}

function buildRoomList(roomList){
  
	var template='';
	$.each( roomList, function( index, room ){
		template+='<div class="item contact" mkd_room="'+room.id+'" mkd_contact="'+room.userDisplay.id+'">'
		    +'<img class="ui avatar image" src="images/wireframe/user_g.png">'
	    +'<div class="content">'
	      +'<h1>'+room.userDisplay.nick+'</h1>'
	      +'<span>'+room.userDisplay.email+'</span>'
	    +'</div>'
	  +'</div>';
	});
	$('#principalContainer .cnt_contacts').html(template);
}




function openModalChangeNick(){
	$('#dlg_changeNick').modal('show');
}

function openModalAddContact(){
	$('#dlg_addContact').modal('show');
}

function openModalSignUp(){
	$('#dlg_signUp').modal('show');
}

function closeModalSignUp(){
	$('#dlg_signUp').modal('hide');
}


function loadContactInfo(idContact){
	console.log('ver '+idContact);
	$.ajax({
        type: 'GET',
        data: {id : idContact },
        url:  'chatController/ajax/detailContact',
		dataType: 'json',
        async: true,
        success: function(result) {
			console.log('user = ' + JSON.stringify(result));
			openModalContactInfo(result);
        },
        error: function(jqXHR,exception) { 
        	console.log(formatErrorMessage(jqXHR,exception));
        	showMessage('INFO','Error',formatErrorMessage(jqXHR,exception));
        },
        beforeSend: function () {  },           
        complete: function () {  }, 
    });
}


function openModalContactInfo(user){
	$('#dlg_contactInfo').modal('show');
	
	dialog = $('#dlg_contactInfo');
	
	dialog.find('.content .header').text(user.nick);
	dialog.find('.content span').text(user.email);
	
	dialog.modal('show');
	
}


function openModal(nameModal){
	if (nameModal=='CHANGE_NICK'){
		
	}else if(nameModal=='ADD_CONTACT'){
		
	}else if(info)
	$('#dlg_room').modal('show');
}


function loadComponents(){
	$('.ui.dropdown').dropdown({
	    allowAdditions: true
	});
	
	$('.ui.checkbox')
	  .checkbox()
	;
}

function getEmail_login(){
	return $('#txt_email_login').val();
}

function getPassword_login(){
	return $('#txt_password_login').val();
}


//UTILES
function formatErrorMessage(jqXHR, exception) {
    if (jqXHR.status === 0) {
        return ('No est� conectado. \nPor favor, compruebe la conexi�n de red.');
    } else if (jqXHR.status == 404) {
        return ('La pagina solicitada no se encuentra. [404]');
    } else if (jqXHR.status == 500) {
        return ('Error interno del servidor.[500].');
    } else if (exception === 'parsererror') {
        return ('JSON solicitado no pudo analizar.');
    } else if (exception === 'timeout') {
        return ('Error tiempo de espera.');
    } else if (exception === 'abort') {
        return ('La solicitud AJAX fue abortada.');
    } else {
        return (jqXHR.responseText);
    }
}

//el tipo puede ser INFO,WARNING,ERROR
function showMessage(type,title, message){
	
	dialog = $('#dlg_genericMessage');
	
	if(type=='WARNING'){
		dialog.find('i.icon').attr('class','waning icon');
	}else if(type=='ERROR'){
		dialog.find('i.icon').attr('class','frown icon');
	}else{
		dialog.find('i.icon').attr('class','info icon');
	}
	
	dialog.find('.header span').text(title);
	dialog.find('.content p').text(message);
	
	dialog.modal('show');
}



$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

function clearForm(selectorForm){
	$(selectorForm).trigger("reset");
}



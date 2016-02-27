var _NICK;
var _CURRENT_ROOM;



$("document").ready(function(){

	cargarEventos();

	$('.ui.dropdown')
	  .dropdown({
	    allowAdditions: true
	  });
	
})


function cargarEventos(){

	$(".btn_login").click(function(){
		$(".cnt_login").css('display','none');
		$(".cnt_menu").css('display','block');
		$(".cnt_chat").css('display','none');
		
		setNick($("#txt_nick").val());
	})

	$(".btn_logout").click(function(){
		$(".cnt_login").css('display','block');
		$(".cnt_menu").css('display','none');
		$(".cnt_chat").css('display','none');
	})

	$("#ul_chat .item").click(function(){
		$(".cnt_login").css('display','none');
		$(".cnt_menu").css('display','none');
		$(".cnt_chat").css('display','block');

	})

	$(".btn_regresar").click(function(){
		$(".cnt_login").css('display','none');
		$(".cnt_menu").css('display','block');
		$(".cnt_chat").css('display','none');
	})


	$(".btn_abrirCambiarNick").click(function(){
		$('#dlg_nick').modal('show');
	});
	
	$(".btn_cambiarNick").click(function(){
		setNick($("#txt_nick_modal").val());
	});
	
	
	$(".btn_abrirAgregarRoom").click(function(){
		$('#dlg_room').modal('show');
	});
	
	$(".btn_agregarRoom").click(function(){
		setNick($("#txt_nick_modal").val());
	});
	
	
}

function setNick(nick){
	_NICK = nick;
	$("#txt_nick_modal").val(_NICK);
}
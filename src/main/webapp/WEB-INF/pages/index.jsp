<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<!-- Standard Meta -->
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /> -->

<title>Monked Chat</title>

<script src="<c:url value='/static/js/jquery-1.11.3.min.js' />"></script>
<script src="<c:url value='/static/semantic/semantic.min.js' />"></script>
<script src="<c:url value='/static/js/global.js' />"></script>
<script src="<c:url value='/static/js/chat.js' />"></script>

<link href="<c:url value='/static/css/reset.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/semantic/semantic.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/font-awesome.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/global.css' />" rel="stylesheet"></link>


</head>
<body>


	<div id="loginContainer" >
		<form id="frm_login" method="POST" action="login" class="ui large form">
			<div class="ui">
				<div class="field">
					<div class="ui left icon input">
						<i class="user icon"></i> 
						<input id="txt_email_login" type="text" name="nick" >
					</div>
				</div>
				<div class="field">
					<div class="ui left icon input">
						<i class="lock icon"></i> 
						<input id="txt_password_login" type="password" name="password" >
					</div>
				</div>
				<div id="btn_login" class="ui fluid large teal submit button">Ingresar</div>
				<div id="btn_openSignUp" class="ui fluid inverted button basic blue">Registrar Cuenta</div>
			</div>
			
		</form>
	</div>
	
	
	
	<div id="principalContainer" class="hidden">
		<div class="ui top fixed menu blue inverted ">
		  <a class="item active">CHATS</a>
		  <a class="item disabled">CONTACTOS</a>
		  <div class="right menu">
		    <div class="ui dropdown icon item">
		      <i class="wrench icon"></i>
		      <div class="menu">
		      	<div id="btn_openAddContact" class="item">
		      		<i class="add user icon"></i>
		      		Agregar contacto
		      	</div>
		        <div id="btn_openChangeNick" class="item" >
		        	<i class="paint brush icon"></i>
		       		Cambiar Nick
		        </div>
		        <div id="btn_logout" class="item">
		        	<i class="power icon"></i>
		        	Cerrar sesión
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
		<div class="ui relaxed divided list cnt_contacts" >
		</div>
	</div>
	
	
	
	<div id="chatContainer" class="hidden">
		<div class="ui top fixed menu teal inverted">
		    <a class="item btn_backPrincipal">
		      <i class="chevron left icon"></i>
		    </a>  
		    <div class="item contactMenu">
		      <img class="ui avatar image" src="static/images/wireframe/user_g.png">
		      <div class="content contactInfo">
		        <div class="header"></div>
		        <span></span>
		      </div>
		    </div>
		    <div class="right menu">
		      <a class="item">
		        <i class="ellipsis vertical icon"></i>
		      </a>
		    </div>
		  </div>
		  
		  <ol class="messages">
<!-- 		  	<li class="other"> -->
<!-- 		      <div class="avatar"></div> -->
<!-- 		      <div class="msg"> -->
<!-- 		        <span class="from">Edgar</span> -->
<!-- 		        <p>Hola!</p> -->
<!-- 		        <p> -->
<!-- 		          Te vienes a cenar al centro? -->
<!-- 		          <emoji class="pizza" /> -->
<!-- 		        </p> -->
<!-- 		        <time>20:17</time> -->
<!-- 		      </div> -->
<!-- 		    </li> -->
		    
<!-- 		    <div class="event">Hoy</div> -->
		    
<!-- 		    <li class="self"> -->
<!-- 		      <div class="avatar"></div> -->
<!-- 		      <div class="msg"> -->
<!-- 		        <p>Puff...</p> -->
<!-- 		        <p> -->
<!-- 		          Aún estoy haciendo el contexto de Góngora... -->
<!-- 		          <emoji class="books" /> -->
<!-- 		        </p> -->
<!-- 		        <p>Mejor otro día</p> -->
<!-- 		        <time>20:18</time> -->
<!-- 		      </div> -->
<!-- 		    </li> -->
    		
		  </ol>
		  
		  <div class="ui bottom fixed menu">
		    <form >
		      <input  id="chatMessageTextID" class="textarea" type="text" placeholder="Escribe aquí!" />
<!-- 		      <input type="button" style="visibility: hidden;" /> -->
<!-- 			  <input type="button"/> -->
				<input type="submit" style="visibility: hidden;" onclick="button_sendMessage()" />
		    </form>
		    <div class="emojis"></div>
		  </div>
	
	</div>
	
	
	
	
<!-- 	dialogs -->

<div id="dlg_changeNick" class="ui modal">
  <i class="close icon"></i>
  <div class="header">
    CAMBIAR NICK
  </div>
  <div class="content">
    <div class="ui transparent big input">
      <input  type="text"    >
    </div>
  </div>
  <div class="actions">
    <div class="ui black deny button">
      Cancelar
    </div>
    <div class="ui positive button">
      Aceptar
    </div>
  </div>
</div>



<div id="dlg_addContact" class="ui modal">
  <i class="close icon"></i>
  <div class="header">
    AGREGAR CONTACTO
  </div>
  <div class="content">
    <div class="ui transparent big input">
      <input   type="text"    >
    </div>
  </div>
  <div class="actions">
    <div class="ui black deny button">
      Cancelar
    </div>
    <div class="ui positive button">
      Aceptar
    </div>
  </div>
</div>
	
	
	
<div id="dlg_contactInfo" class="ui card modal">
  <div class="image">
    <img src="static/images/wireframe/user_g.png">
  </div>
  <div class="content">
    <a class="header">INFORMACION DE CONTACTO</a>
    <div class="meta">
      <span></span>
    </div>
  </div>
  <div class="extra content actions">
       <div class="ui basic red button deny">Cerrar</div>
   </div>
</div>



<div id="dlg_signUp" class="ui fullscreen modal">
    <i class="close icon"></i>
    <div class="header">
      REGISTRO DE USUARIO
    </div>
    <div class="content">
      	<form id="frm_signUp" class="ui form">
		  <div class="field">
		    <label>Correo electrónico</label>
		    <input type="text" name="email" >
		  </div>
		  <div class="field">
		    <label>Contraseña</label>
		    <input type="password" name="password" >
		  </div>
		  <div class="field">
		    <label>Alias</label>
		    <input type="text" name="nick" >
		  </div>
		  <div class="field">
		    <div class="ui checkbox">
		      <input type="checkbox" tabindex="0" class="hidden">
		      <label>Acepto los términos y condiciones (fake).</label>
		    </div>
		  </div>
		</form>
    </div>
    <div class="actions">
      <div class="ui button deny">Cancel</div>
      <div id="btn_signUp" class="ui possitive green button">Registrar</div>
    </div>
  </div>


<div id="dlg_genericMessage" class="ui basic modal">
<!-- 	<i class="close icon"></i> -->
   <div class="ui icon header">
     <i class="warning icon"></i>
     <span></span>
   </div>
   <div class="content">
     <p></p>
   </div>
 </div>
	
	

</body>
</html>
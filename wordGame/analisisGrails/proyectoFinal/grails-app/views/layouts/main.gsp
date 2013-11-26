<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}" type="text/css">
                <script src="${resource(dir: 'js', file: 'jquery-1.8.3.js')}"></script>
                <script src="${resource(dir: 'js', file: 'bootstrap.js')}"></script>              
                <script src="${resource(dir: 'js', file: 'arbor.js')}"></script>
                <script src="${resource(dir: 'js', file: 'graphics.js')}"></script>
                <script src="${resource(dir: 'js', file: 'renderer.js')}"></script>
                <g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
            <div class="navbar navbar-inverse navbar-fixed-top">
              <div class="container">
                  <div class="navbar-header">
                      <a class="navbar-brand" href="${createLink(uri: '/')}">Buscador de palabras</a>
                  </div>
                  <div class="collapse navbar-collapse">
                      <ul class="nav navbar-nav">
                          <li class="active"><a class="home" href="#">Integrantes</a></li>
                          <li><a href="#about">Res&eacute;ndiz Arteaga Juan Alberto</a></li>
                          <li><a href="#contact">L&oacute;pez Gardu&ntilde;o Blanca Azucena</a></li>
                      </ul>
                  </div><!--/.nav-collapse -->
              </div>
          </div>
          <div class="container">
              <g:layoutBody/>
          </div>              
	</body>
</html>

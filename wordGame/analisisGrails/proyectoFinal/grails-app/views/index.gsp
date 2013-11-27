<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main"/>
    <title>Analisis de Algoritmos :  Juego de las palabras : 3CV1 : ESCOM </title> 
    <style type="text/css" media="screen">
#status {
  background-color: #eee;
  border: .2em solid #fff;
  margin: 2em 2em 1em;
  padding: 1em;
  width: 12em;
  float: left;
  -moz-box-shadow: 0px 0px 1.25em #ccc;
  -webkit-box-shadow: 0px 0px 1.25em #ccc;
  box-shadow: 0px 0px 1.25em #ccc;
  -moz-border-radius: 0.6em;
  -webkit-border-radius: 0.6em;
  border-radius: 0.6em;
}

.ie6 #status {
  display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
}

#status ul {
  font-size: 0.9em;
  list-style-type: none;
  margin-bottom: 0.6em;
  padding: 0;
}

#status li {
  line-height: 1.3;
}

#status h1 {
  text-transform: uppercase;
  font-size: 1.1em;
  margin: 0 0 0.3em;
}

#page-body {
  margin: 2em 1em 1.25em 18em;
}

h2 {
  margin-top: 1em;
  margin-bottom: 0.3em;
  font-size: 1em;
}

p {
  line-height: 1.5;
  margin: 0.25em 0;
}

#controller-list ul {
  list-style-position: inside;
}

#controller-list li {
  line-height: 1.3;
  list-style-position: inside;
  margin: 0.25em 0;
}

@media screen and (max-width: 480px) {
  #status {
    display: none;
    }

      #page-body {
        margin: 0 1em 1em;
      }

      #page-body h1 {
        margin-top: 0;
      }
      }
    </style>
  </head>
  <body>
    <hr>
    <div class="container">
      <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
          <div class="well sidebar-nav">
            <ul class="nav">
              <li><b>Descripci&oacute;n</b></li>
              <li>
                <i>
                  Tiene como objetivo el modelar un grafo que represente los cambios de una palabra a otra.
                </i>
              </li>
              <li><b>Modo de uso</b></li>
              <li>
                <i>
                  Escribir una palabra de 4 letras en idioma ingl&eacute;s en los campos de origen y destino, elegir el algoritmo a usar y ver el resultado!
                </i>
              </li>
              <li><hr/></li>
              <li><b>Origen</b></li>
              <li><input type="text" class="form-control" name="origen" id="origen" maxlenght="4"></li>
              <li><b>Destino</b></li>
              <li><input type="text" class="form-control" name="destino" id="destino" maxlenght="4"></li>
              <li><b>Algoritmo</b></li>
              <li>
                <select name="algoritmo" id="algoritmo">
                  <option value="-1" selected> -- Selecciona -- </option>
                  <option value="1" > B&uacute;squeda por Amplitud </option>
                  <option value="2" > B&uacute;squeda por Profundidad </option>
                  <option value="3" > A estrella </option>
                  <option value="4" > Propuesto (Dijkstra) </option>
                </select>
              </li>
              <li><input class="btn btn-sm btn-info" type="button" id="obtenerSolucion" value="Buscar Solucion"></li>
              <li><hr/></li>
              <li><span id="fake">&nbsp;</span></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="col-xs-12 col-sm-9 container">  
          <canvas id="viewport" width="800" height="500"></canvas>
        </div>                
      </div><!--/row-->
    </div><!--/span-->
  </div>
  <div id="bar" class="navbar navbar-inverse navbar-fixed-bottom">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="#"><b>Instituto Polit&eacute;cnico Nacional</b></a>
          <a class="navbar-brand" href="#"><b>Escuela Superior de C&oacute;mputo</b></a>
          <a class="navbar-brand" href="#">Distribuido bajo una licencia <i>GNU General Public License v.3.0.</i></a>
        </div>
      </div>
  </div>
  <script type="text/javascript">
    // Se inicia lo necesario para el render de las imágenes
      var sys = arbor.ParticleSystem(10,600,0.1,true,55,0.02,0.3);
      sys.renderer = Renderer("#viewport");
      var urls = new Array("-1","busquedaPorAmplitud","busquedaPorProfundidad","busquedaAEstrella","busquedaDijkstra")
      var ruta;
      var arregloNodos =  new Array();
      // Funcion para limpiar el contenido del canvas
      function clearCanvas(){
        for(var i=0;i<arregloNodos.length;i++){
            sys.pruneNode(arregloNodos[i]);
        }
        arregloNodos =  new Array();
      }
      // Funcion para validar los formularios
      function validaFormularios(){
        var valida = true;
       //console.warn("Origen > ");
        //console.log($("#origen").val().length == 4); 
        valida = ($("#origen").val().length == 4) && valida;
        //console.warn("Destino > ");
        //console.log($("#destino").val().length == 4); 
        valida = ($("#destino").val().length == 4) && valida;
        //console.warn("Algoritmo > ");
        //console.log($("#algoritmo :selected").val().length > 0);
        valida = ($("#algoritmo :selected").val().length > 0) && valida;
        //console.log(">>> " + valida);
        return valida;
      }
      $(document).ready(function(){              
              $("#fake").css('background-image', 'url(${resource(dir: 'images', file: 'wait.gif')})');
              $("#fake").css('background-color', '#FFFFFF');
              $("#fake").css('width', '100%');
              $("#fake").css('opacity', '0.5');
              $("#fake").css('display', 'none');
              $("input").attr('disabled','disabled');
          $(document).ajaxStart(function(){
              $("#fake").css('background-color', '#ababab');
              $("#fake").css('display', 'block');
          });
          $(document).ajaxStop(function(){
              $("#fake").css('display', 'none');              
          });
          
          $.ajax({
            type: "GET",
            contenType:"JSON",
            url: "busquedas/respuestaCargaGrafo/",                  
          }).done(function(){
            $("input").removeAttr('disabled');
          });
      });
      /*
       * 
       * 
       * 
       */
      
       $( "#origen" ).autocomplete({
            source: function( request, response ) {
              $.ajax({
                url: "busquedas/obtenerPalabrasAutoComplete",
                contenType: "json",
                data: {                  
                   like: $("#origen").val()
                },
                success: function( data ) {
                  response( $.map( data.lista, function( item ) {
                     return {
                         label: item.word,
                         value: item.word
                     }
                 }));
                }
              });
            },
            minLength: 2,
            select: function( event, ui ) {
                $("#origen").val(ui);
            },
            open: function() {
                 $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
            },
            close: function() {
                $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
            }
          });
        $( "#destino" ).autocomplete({
            source: function( request, response ) {
              $.ajax({
                url: "busquedas/obtenerPalabrasAutoComplete",
                contenType: "json",
                data: {                  
                   like: $("#destino").val()
                },
                success: function( data ) {
                  response( $.map( data.lista, function( item ) {
                     return {
                         label: item.word,
                         value: item.word
                     }
                 }));
                }
              });
            },
            minLength: 2,
            select: function( event, ui ) {
                $("#destino").val(ui);
            },
            open: function() {
                 $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
            },
            close: function() {
                $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
            }
          });
      /*
       * 
       * 
       * 
       * 
       * 
       */
      $(document).on("click","#obtenerSolucion",function(){
          if(!validaFormularios()){
              alert("Llena el formulario!!!");
              return false;
          }
          $.ajax({
            type: "POST",
            contenType:"JSON",
            url: "busquedas/"+urls[$("#algoritmo :selected").val()]+"/",
            data: { inicio: $("#origen").val(), destino: $("#destino").val() }
          }).done(function( msg ) {
            console.log("Datos recibidos! " + msg.ruta);
            clearCanvas();
            ruta = msg.ruta;
            if(msg.error){
              alert("Los parametros de Inicio y Destino son obligatorios!!!");
            } else {
              for(var i=0;i<ruta.length;i++){
              if(i==0){
                arregloNodos[i]=sys.addNode(ruta[i],{'color':'red','label':ruta[i]})
              } else if(i==(ruta.length-1)){
                arregloNodos[i]=sys.addNode(ruta[i],{'color':'red','label':ruta[i]})
              } else {
                arregloNodos[i]=sys.addNode(ruta[i],{'color':'blue','label':ruta[i]})
              }
              
            }
            for(var i=0;i<(ruta.length-1);i++){
              sys.addEdge(arregloNodos[i],arregloNodos[i+1]);
            }
          }
          });
      });     
  </script>
</body>
</html>

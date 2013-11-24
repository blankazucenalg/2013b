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
    <div id="content">
      <table>
        <tr>
          <td>Origen</td>
          <td><input type="text" class="campoTexto" name="origen" id="origen" maxlenght="4"></td>
          <td>Destino</td>
          <td><input type="text" class="campoTexto" name="destino" id="destino" maxlenght="4"></td>
          <td>Algoritmo</td>
          <td>
            <select name="algoritmo" id="algoritmo">
              <option value="-1" selected> -- Selecciona -- </option>
              <option value="1" > B&uacute;squeda por Amplitud </option>
              <option value="2" > B&uacute;squeda por Profundidad </option>
              <option value="3" > A estrella </option>
              <option value="4" > Propuesto (Dijkstra) </option>
            </select></td>
          <td><input type="button" id="obtenerSolucion" value="Buscar Solucion"></td>
        </tr>
      </table>
    </div>
    <div style="border:thin black;">
      <canvas id="viewport" width="1280" height="800"></canvas>
    </div>

    <script type="text/javascript">
        var sys = arbor.ParticleSystem(10,600,0.1,true,55,0.02,0.3);
        sys.renderer = Renderer("#viewport");
        var urls = new Array("-1","busquedaPorAmplitud","busquedaPorProfundidad","busquedaAEstrella","busquedaDijkstra")
        var ruta;
        var arregloNodos =  new Array();
        function clearCanvas(){
          for(var i=0;i<arregloNodos.length;i++){
              sys.pruneNode(arregloNodos[i]);
          }
          arregloNodos =  new Array();
        }
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
        $(document).on("click","#obtenerSolucion",function(){
            if(!validaFormularios()){
                alert("Llena el formulario!!!");
                return false;
            }
            $.ajax({
              type: "POST",
              contenType:"JSON",
              url: "http://localhost:8080/proyectoFinal/busquedas/"+urls[$("#algoritmo :selected").val()]+"/",
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

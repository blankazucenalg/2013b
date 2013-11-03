###
Lopez Garduño Blanca Azucena
Resendiz Arteaga Juan Alberto
Analisis de Algoritmos
###

INFINITE = -1
NO_COLOR = 0
WHITE = 1
GRAY = 2
BLACK = 3
NO_PREDECESSOR = null
class Node
    constructor: (name) ->
        @nodeName = name
        @d = INFINITE
        @color = NO_COLOR
        @pi = null
        @neighbors = {}
        @f = INFINITE
    showInfo: ->
        return "Name: #{@nodeName} >> D: #{@d} >> COLOR: #{@color}"
    getColor: ->
        if @color == WHITE
            return "WHITE"
        else if @color == GRAY
            return "GRAY"
        else if @color == BLACK
            return "NEGRO"
        else
            return "NO COLOR"
    getNeighborsValues: ->
        return @neighbors.values()
    getNeighbors: ->
        return Object.keys(@neighbors)
    addNeighbor: (neighborName,cost)->        
        @neighbors[neighborName] = cost

class Graph
    constructor: (name) ->
        @graphName = name
        @numberOfNodes = 0
        @nodes = {}
        #alert("#{@graphName} >> #{@numberOfNodes}")
    showInfo:() ->
        return "#{@graphName} >> #{@numberOfNodes}"
    printNodes:() ->
        for node in @nodes
            console.log(node.showInfo())
    addNode: (nodeName) ->
        n  = new Node(nodeName)        
        @nodes[nodeName] = n
        @numberOfNodes = @numberOfNodes + 1      
    getKeysOfNodes: ->
        return Object.keys(@nodes)
    getNode: (name) ->
        #alert((@nodes[name]).nodeName+ ">>> "+ name )
        return @nodes[name]
    getNodes:() ->
        return @nodes
    deepFirstSearch: (nameOriginNode)->
        for nn in @getKeysOfNodes()
            n = @getNode(nn)
            n.color = WHITE
            n.pi = null
        @time = 0        
        origin = @getNode(nameOriginNode)
        @bp_visit(origin)        
    bp_visit: (u) ->
        u.color = GRAY
        @tiempo += 1
        u.d = @tiempo
        for nv in u.getNeighbors()
            v = @getNode(nv)
            if v.color == WHITE
                v.pi = u.nodeName
                @bp_visit(v)        
        u.color = BLACK
        @tiempo += 1
        u.f = @tiempo
    busquedaAmplitud:(nameOriginNode) ->        
        for nn in @getKeysOfNodes()
            if nn != nameOriginNode
                n = @getNode(nn)
                n.color = WHITE
                n.d = INFINITE
                n.pi = null                
        s = @getNode(nameOriginNode)        
        s.color = GRAY
        s.d = 0
        s.pi = NO_PREDECESSOR
        Q = []        
        Q.push(s)        
        while Q.length != 0
            z = Q.shift()            
            for nv in z.getNeighbors()
                v = @getNode(nv)
                if v.color is WHITE
                    v.color = GRAY
                    v.d = z.d + 1
                    v.pi = z.nodeName
                    Q.push(v)
            z.color = BLACK 
    ruta:(nameDestinyNode, list)->
        if list == null
            list = []
        list.push(nameDestinyNode)
        nd = @getNode(nameDestinyNode)
        if nd.pi isnt null and typeof nd.pi isnt "undefined" and "#{nd.pi}".length> 0
            return @ruta(nd.pi, list)
        return list
###
----------------------------------------
----------------------------------------
----------------------------------------
###
$(document).on("click","#start",->
    start()
    )
start = () ->
    g = new Graph("Mi grafo 1")
    #alert(g.showInfo())    
    g.addNode("r")
    g.addNode("s")
    g.addNode("t")
    g.addNode("u")
    g.addNode("v")
    g.addNode("w")
    g.addNode("x")
    g.addNode("y")
    #g.showInfo()
    console.log("LLAVES")
    console.log(g.getKeysOfNodes())
    r = g.getNode("r")
    r.addNeighbor("s", 1)
    r.addNeighbor("v", 1)

    s = g.getNode("s")
    s.addNeighbor("r", 1)
    s.addNeighbor("w", 1)

    t = g.getNode("t")
    t.addNeighbor("u", 1)
    t.addNeighbor("w", 1)
    t.addNeighbor("x", 1)

    u = g.getNode("u")
    u.addNeighbor("t", 1)
    u.addNeighbor("x", 1)
    u.addNeighbor("y", 1)

    v = g.getNode("v")
    v.addNeighbor("r", 1)

    w = g.getNode("w")
    w.addNeighbor("s", 1)
    w.addNeighbor("t", 1)
    w.addNeighbor("x", 1)

    x = g.getNode("x")
    x.addNeighbor("t", 1)
    x.addNeighbor("u", 1)
    x.addNeighbor("w", 1)
    x.addNeighbor("y", 1)

    y = g.getNode("y")
    y.addNeighbor("u", 1)
    y.addNeighbor("x", 1)

    console.warn("---- VECINOS ----")
    console.log(r.getNeighbors())
    console.log(s.getNeighbors())
    console.log(t.getNeighbors())
    console.log(u.getNeighbors())
    console.log(v.getNeighbors())
    console.log(w.getNeighbors())
    console.log(x.getNeighbors())
    console.log(y.getNeighbors())
    console.warn("----printNodes----")
    g.printNodes()
    console.warn("-----BUSQUEDA AMPLITUD---")
    g.busquedaAmplitud("s")
    console.warn("----printNodes----")    
    g.printNodes()
    console.warn("----RUTA ----")
    ruta = g.ruta("y", null)
    for elemento in ruta
        console.log("RUTA : Y  > "+elemento)
    console.warn("--------")
    ruta2 = g.ruta("v", null)
    console.log("RUTA : V  > "+ruta2)
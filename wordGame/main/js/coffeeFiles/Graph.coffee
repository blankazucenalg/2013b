###
Lopez Garduño Blanca Azucena
Resendiz Arteaga Juan Alberto
Analisis de Algoritmos
###
class Graph
    constructor: (name) ->
        @graphName = name
        @numberOfNodes = 0
        @nodes = {}
    showInfo: ->
        console.log("#{@graphName} >> #{@numberOfNodes}")
    printNodes: ->
        for node in @nodes
            console.log(node.showInfo())
    addNode: (nodeName) ->
        n  = Node(nodeName)
        @nodes[nodeName] = n
        @numberOfNodes = @numberOfNodes + 1      
    getKeysOfNodes: ->
        return Object.keys(@nodes)
    getNode: (name) ->
        return @nodes[name]
    getNodes: ->
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
        u.color = GRIS
        @tiempo += 1
        u.d = @tiempo
        for nv in u.getNeighbors()
            v = @getNode(nv)
            if v.color is WHITE
                v.pi = u.name
                @bp_visit(v)        
        u.color = BLACK
        @tiempo += 1
        u.f = @tiempo
    busquedaAmplitud:(self, nameOriginNode) ->        
        for nn in @getKeysOfNodes()
            if nn is not nameOriginNode
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
        while Q.length is not 0
            z = Q.shift()            
            for nv in z.getNeighbors()
                v = @getNode(nv)
                if v.color is WHITE
                    v.color = GRAY
                    v.d = z.d + 1
                    v.pi = z.name
                    Q.push(v)
            z.color = BLACK 
    ruta:(self, nameDestinyNode, list)->
        if list is null
            list = []
        list.push(nameDestinyNode)
        nd = @getNode(nameDestinyNode)
        if nd.pi is not null
            return @ruta(nd.pi, list)
        return list
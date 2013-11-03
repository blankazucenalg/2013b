###
Lopez Garduño Blanca Azucena
Resendiz Arteaga Juan Alberto
Analisis de Algoritmos
###
#Initialize the main values of the node
INFINITE = -1
NO_COLOR = 0
WHITE = 1
GRAY = 2
BLACK = 3
NO_PREDECESSOR = null
class Node
    @d = INFINITE
    @color = NO_COLOR
    @pi = null
    @neighbors = {}
    @f = INFINITE
    constructor: (@name) ->
        @nodeName = name
    showInfo: ->
        console.log("#{@name} >> #{@d} >> #{@color}")
    getColor: ->
        if @color is WHITE
            return "WHITE"
        else if @color is GRAY
            return "GRAY"
        else if @color is BLACK
            return "NEGRO"
        else
            return "NO COLOR"
    getNeighborsValues: ->
        return @neighbors.values()
    getNeighbors: ->
        return @neighbors
    addNeighbor: (@neighborName,@cost)->
        @neighbors['@neighborName'] = @cost
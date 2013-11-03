// Generated by CoffeeScript 1.6.1

/*
Lopez Garduño Blanca Azucena
Resendiz Arteaga Juan Alberto
Analisis de Algoritmos
*/


(function() {
  var BLACK, GRAY, INFINITE, NO_COLOR, NO_PREDECESSOR, Node, WHITE;

  INFINITE = -1;

  NO_COLOR = 0;

  WHITE = 1;

  GRAY = 2;

  BLACK = 3;

  NO_PREDECESSOR = null;

  Node = (function() {

    Node.d = INFINITE;

    Node.color = NO_COLOR;

    Node.pi = null;

    Node.neighbors = {};

    Node.f = INFINITE;

    function Node(name) {
      this.name = name;
      this.nodeName = name;
    }

    Node.prototype.showInfo = function() {
      return console.log("" + this.name + " >> " + this.d + " >> " + this.color);
    };

    Node.prototype.getColor = function() {
      if (this.color === WHITE) {
        return "WHITE";
      } else if (this.color === GRAY) {
        return "GRAY";
      } else if (this.color === BLACK) {
        return "NEGRO";
      } else {
        return "NO COLOR";
      }
    };

    Node.prototype.getNeighborsValues = function() {
      return this.neighbors.values();
    };

    Node.prototype.getNeighbors = function() {
      return this.neighbors;
    };

    Node.prototype.addNeighbor = function(neighborName, cost) {
      this.neighborName = neighborName;
      this.cost = cost;
      return this.neighbors['@neighborName'] = this.cost;
    };

    return Node;

  })();

}).call(this);

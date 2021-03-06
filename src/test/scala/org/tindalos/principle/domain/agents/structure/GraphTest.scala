package org.tindalos.principle.domain.agents.structure

import org.junit.Assert._
import org.junit.Test
import org.tindalos.principle.domain.agents.structure.Graph.Node

class GraphTest {

  @Test
  def findDownstreamNodesForSimpleAcyclicGraph() {
    //a -> b,c | b -> d
    val (a,b,c,d) = ("a","b","c","d")
    val nodeA = Node(a, Set(b, c), Set())
    val nodeB = Node(b, Set(d), Set(a))
    val nodeC = Node(c, Set(), Set(a))
    val nodeD = Node(d, Set(), Set(b))

    val graph = Set(nodeA, nodeB, nodeC, nodeD)

    assertTrue(Graph.isValid(graph))

    assertEquals(Set(nodeD), Graph.findDownstreamNodes(nodeD, graph))
    assertEquals(Set(nodeC), Graph.findDownstreamNodes(nodeC, graph))
    assertEquals(Set(nodeB,nodeD), Graph.findDownstreamNodes(nodeB, graph))
    assertEquals(Set(nodeA, nodeB, nodeC, nodeD), Graph.findDownstreamNodes(nodeA, graph))

  }


  @Test
  def findDownstreamNodesForDiamondAcyclicGraph() {
    //a -> b,c | b -> d | c->d
    val (a,b,c,d) = ("a","b","c","d")
    val nodeA = Node(a, Set(b, c), Set())
    val nodeB = Node(b, Set(d), Set(a))
    val nodeC = Node(c, Set(d), Set(a))
    val nodeD = Node(d, Set(), Set(b,c))

    val graph = Set(nodeA, nodeB, nodeC, nodeD)

    assertTrue(Graph.isValid(graph))

    assertEquals(Set(nodeD), Graph.findDownstreamNodes(nodeD, graph))
    assertEquals(Set(nodeC,nodeD), Graph.findDownstreamNodes(nodeC, graph))
    assertEquals(Set(nodeB,nodeD), Graph.findDownstreamNodes(nodeB, graph))
    assertEquals(Set(nodeA, nodeB, nodeC, nodeD), Graph.findDownstreamNodes(nodeA, graph))
  }

  @Test
  def findDownstreamNodesForCyclicTriangeGraph() {
    //a -> b,c | b -> d | c->d
    val (a,b,c) = ("a","b","c")
    val nodeA = Node(a, Set(b), Set(c))
    val nodeB = Node(b, Set(c), Set(a))
    val nodeC = Node(c, Set(a), Set(b))

    val graph = Set(nodeA, nodeB, nodeC)

    assertTrue(Graph.isValid(graph))

    assertEquals(graph, Graph.findDownstreamNodes(nodeA, graph))
    assertEquals(graph, Graph.findDownstreamNodes(nodeB, graph))
    assertEquals(graph, Graph.findDownstreamNodes(nodeC, graph))
  }


  @Test
  def findDetachableSubgraphsInGraphWithIslands() {
    //a -> b,c | b -> d | c->d
    val (a,b,c,d,e) = ("a","b","c","d","e")
    val nodeA = Node(a, Set(b), Set(c))
    val nodeB = Node(b, Set(c), Set(a))
    val nodeC = Node(c, Set(a), Set(b))

    val nodeD = Node(d,Set(e),Set(e))
    val nodeE = Node(e,Set(d),Set(d))

    val island1 = Set(nodeA, nodeB, nodeC)
    val island2 = Set(nodeD, nodeE)
    val graph = island1 ++ island2

    assertTrue(Graph.isValid(graph))

    val x = Graph.findDetachableSubgraphs(graph)
    //assertEquals(island1, Graph.findDetachableSubgraphs(graph))
    //assertEquals(island1, Graph.findDownstreamNodes(nodeB, graph))
    //assertEquals(island1, Graph.findDownstreamNodes(nodeC, graph))
  }

  @Test
  def isIslandPositive() {

    val (a,b,c,d,e) = ("a","b","c","d","e")
    val nodeA = Node(a, Set(b), Set(c))
    val nodeB = Node(b, Set(c), Set(a))
    val nodeC = Node(c, Set(a), Set(b))
    val nodeD = Node(c, Set(a), Set(e))
    val nodeE = Node(c, Set(a), Set(b))

    val island = Set(nodeA, nodeB, nodeC)
    val notIsland = Set(nodeA, nodeB, nodeC, nodeD)

    assertTrue(Graph.isIsland(island))
    assertFalse(Graph.isIsland(notIsland))
  }


  @Test
  def findDownstreamNodesForCompleteGraph() {

    val (a,b,c,d) = ("a","b","c","d")
    val nodeA = Node(a, Set(b,c,d), Set(b,c,d))
    val nodeB = Node(b, Set(a,c,d), Set(a,c,d))
    val nodeC = Node(c, Set(a,b,d), Set(a,b,d))
    val nodeD = Node(d, Set(a,b,c), Set(a,b,c))

    val graph = Set(nodeA, nodeB, nodeC, nodeD)

    assertTrue(Graph.isValid(graph))

    assertEquals(graph, Graph.findDownstreamNodes(nodeA, graph))
    assertEquals(graph, Graph.findDownstreamNodes(nodeB, graph))
    assertEquals(graph, Graph.findDownstreamNodes(nodeC, graph))
    assertEquals(graph, Graph.findDownstreamNodes(nodeD, graph))
  }

  @Test
  def simpleGraphValid() {
    val (a,b) = ("a","b") // a->b
    val nodeA = Node(a, Set(), Set(b))
    val nodeB = Node(b, Set(a), Set())

    assertTrue(Graph.isValid(Set(nodeA, nodeB)))
  }
  @Test
  def simpleGraphInValid() {
    val (a,b) = ("a","b") // a->b
    val nodeA = Node(a, Set(), Set(b))
    val nodeB = Node(b, Set(), Set()) // missing reference to A

    assertFalse(Graph.isValid(Set(nodeA, nodeB)))
  }

}

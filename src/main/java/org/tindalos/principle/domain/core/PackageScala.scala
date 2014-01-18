package org.tindalos.principle.domain.core

import scala.collection.mutable.ListBuffer
import org.apache.commons.lang3.builder.HashCodeBuilder

abstract class PackageScala(val reference: PackageReference) {

  def this(referenceName: String) = this(new PackageReference(referenceName))

  override def equals(other: Any) = other.isInstanceOf[PackageScala] && other.asInstanceOf[PackageScala].reference.equals(reference)

  override def hashCode() = new HashCodeBuilder().append(reference).hashCode()

  override def toString() = reference.toString()
}

class TraversedPackages(val packages: List[PackageReference] = List()) {

  def add(reference: PackageReference) = new TraversedPackages(packages :+ reference)
  def from(index: Int) = packages.slice(index, packages.length)
}

object TraversedPackages {
  def empty() = new TraversedPackages()
}
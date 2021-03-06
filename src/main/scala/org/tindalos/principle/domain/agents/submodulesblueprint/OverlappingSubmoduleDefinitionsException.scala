package org.tindalos.principle.domain.agents.submodulesblueprint

class OverlappingSubmoduleDefinitionsException(val overlaps: Set[Overlap])
  extends InvalidBlueprintDefinitionException(OverlappingSubmoduleDefinitionsException.toMessage(overlaps)) 

object OverlappingSubmoduleDefinitionsException {

  def toMessage(overlaps: Set[Overlap]) = {

    val msg = new StringBuffer("Overlapping submodules: ")
    overlaps.foreach({ overlap =>
      msg.append("\n")
      overlap.submoduleIds.foreach({ submoduleId => msg.append(submoduleId + " and ") })
      msg.append(msg.substring(0, msg.length() - 4))

    })

    msg.toString()
  }

}
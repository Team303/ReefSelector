package com.team303.lib.shuffleboard;

import edu.wpi.first.shuffleboard.api.plugin.Description;
import edu.wpi.first.shuffleboard.api.plugin.Plugin;
import edu.wpi.first.shuffleboard.api.widget.ComponentType;
import edu.wpi.first.shuffleboard.api.widget.WidgetType;

import java.util.List;

import com.team303.lib.shuffleboard.widget.NodeSelectorWidget;


@Description(
    group = "com.team303.lib",
    name = "NodeSelectorWidget",
    version = "2023.1.1",
    summary = "A widget that updates color based off of the state of how the operator interacts with a node"
)
public final class NodeSelectorWidgetPlugin extends Plugin {

  @Override
  public List<ComponentType> getComponents() {
    return List.of(
        WidgetType.forAnnotatedWidget(NodeSelectorWidget.class)
    );
  }
}
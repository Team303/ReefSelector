package com.team303.lib.shuffleboard;

import edu.wpi.first.shuffleboard.api.data.DataType;
import edu.wpi.first.shuffleboard.api.plugin.Description;
import edu.wpi.first.shuffleboard.api.plugin.Plugin;
import edu.wpi.first.shuffleboard.api.widget.ComponentType;
import edu.wpi.first.shuffleboard.api.widget.WidgetType;

import java.util.List;

import com.team303.lib.shuffleboard.data.type.ReefStateType;
import com.team303.lib.shuffleboard.widget.ReefSelectorWidget;

@Description(group = "com.team303.lib", name = "ReefSelectorWidget", version = "2025.1.3", summary = "A widget that updates color based off of the state of how the operator interacts with a node")
public final class ReefSelectorWidgetPlugin extends Plugin {

  @Override
  public List<DataType> getDataTypes() {
    return List.of(
        ReefStateType.Instance);
  }

  @Override
  public List<ComponentType> getComponents() {
    return List.of(
        WidgetType.forAnnotatedWidget(ReefSelectorWidget.class));
  }
}
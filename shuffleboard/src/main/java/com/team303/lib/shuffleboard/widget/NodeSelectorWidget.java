package com.team303.lib.shuffleboard.widget;

import java.io.File;
import java.util.List;

import com.google.common.collect.ImmutableList;

import edu.wpi.first.shuffleboard.api.prefs.Group;
import edu.wpi.first.shuffleboard.api.prefs.Setting;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
// import javafx.beans.property.SimpleStringProperty;
// import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

@Description(name = "State of Node", dataTypes = Integer.class, summary = "Updates color of box when state of node changes")
@ParametrizedController("NodeSelectorWidget.fxml")
public final class NodeSelectorWidget extends SimpleAnnotatedWidget<Integer> {

  /**
   * The root content to display. We do a simple light grey / dark grey box to
   * indicate true / false status and white if there's no data.
   */
  @FXML
  private Pane root;

  /** Holder for an error panel; this gets attached to root if we need to display error text */
  private Text errorMessage;


  /**
   * Called by the JavaFX framework to initialize the widget
   */
  @FXML
  private void initialize() {
    root.backgroundProperty().bind(
        Bindings.createObjectBinding(
            () -> createSolidColorBackground(getColor()),
            dataProperty()));
    root.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          if(event.getButton() == MouseButton.PRIMARY) {
            setData(6);
          } else if (event.getButton() == MouseButton.SECONDARY) {
            setData(7);
          }  
      }
   });
   // dataProperty().addListener((newValue) -> checkSoundPlay());
  }


  /**
   * Set error message, or clear error message by passing a null string
   * @param msg Message to set, or null to clear currently-set message
   */
  private void setErrorMessage(String msg) {
    if (msg == null) {
      if (errorMessage != null) {
        root.getChildren().remove(errorMessage);
        errorMessage = null;
        return;
      }
    } else {
      if (errorMessage == null) {
        errorMessage = new Text();
        errorMessage.setStyle("color: red");
        root.getChildren().add(errorMessage);
      }
      errorMessage.setText(msg);
    }
  }

  /**
   * Build a sold-color background to attach as a property
   * @param color Color to use
   * @return The built background
   */
  private Background createSolidColorBackground(Color color) {
    return new Background(new BackgroundFill(color, null, null));
  }

  /**
   * <br> Choose the color to use based on current widget state </br>
   * <br> &#8226 Box will be <b>gray</b> if a node has no object placed on it </br>
   * <br> &#8226 Box will be <b>yellow</b> if a node has a cone placed on it </br>
   * <br> &#8226 Box will be <b>purple</b> if a node has a cube placed on it </br>
   * <br> &#8226 Box wlil be <b>green</b> if operator is hovering over node in Shuffleboard </br>
   * <br> &#8226 Box will be <b>orange</b> if node placement is queued </br>
   * <br> &#8226 Box will be <b>red</b> if robot detects that placement on node is invalid (typically because cubes can't be placed
   * on cone nodes and vice versa) </br>
   * @return Color to use
   */
  private Color getColor() {
    if (getData()!= null) {
    final Number data = getData();
    final Integer realData = data.intValue();
    final Color PURPLE = Color.rgb(102,0,153);
    Color[] colorArr = {Color.GRAY,Color.YELLOW,PURPLE,Color.GREEN,Color.ORANGE,Color.RED};
    return colorArr[realData];
    } else {
      return Color.GRAY;
    }
  }

  @Override
  public Pane getView() {
    return root;
  }
}

package com.team303.lib.shuffleboard.widget;

import java.io.File;
import java.util.List;

import org.fxmisc.easybind.EasyBind;

import com.google.common.collect.ImmutableList;
import com.team303.lib.shuffleboard.data.ReefState;

import edu.wpi.first.shuffleboard.api.prefs.Group;
import edu.wpi.first.shuffleboard.api.prefs.Setting;
import edu.wpi.first.shuffleboard.api.sources.DataSource;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableNumberValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

@Description(name = "ReefSelector", dataTypes = ReefState.class, summary = "Updates color of box when state of node changes")
@ParametrizedController("ReefSelectorWidget.fxml")
public final class ReefSelectorWidget extends SimpleAnnotatedWidget<ReefState> {

  @FXML
  private Pane root;

  @FXML
  private Rectangle L4;

  @FXML
  private Rectangle L3;

  @FXML
  private Rectangle L2;

  @FXML
  private Rectangle L1;

  /**
   * Holder for an error panel; this gets attached to root if we need to display
   * error text
   */
  private Text errorMessage;

  /**
   * Called by the JavaFX framework to initialize the widget
   */
  @FXML
  private void initialize() {

    L4.fillProperty().bind(
        Bindings.createObjectBinding(() -> getL4Color(), dataProperty()));
    L3.fillProperty().bind(
        Bindings.createObjectBinding(() -> getL3Color(), dataProperty()));
    L2.fillProperty().bind(
        Bindings.createObjectBinding(() -> getL2Color(), dataProperty()));
    L1.fillProperty().bind(
        Bindings.createObjectBinding(() -> getL1Color(), dataProperty()));
    L4.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
          long[] curValues = dataProperty().get().getState();
          if (curValues[3] == 0) {
            setData(new ReefState(curValues[0], curValues[1], curValues[2], 1));
          } else {
            setData(new ReefState(curValues[0], curValues[1], curValues[2], 0));
          }
        }
      }
    });
    L3.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
          long[] curValues = dataProperty().get().getState();
          if (curValues[2] == 0) {
            setData(new ReefState(curValues[0], curValues[1], 1, curValues[3]));
          } else {
            setData(new ReefState(curValues[0], curValues[1], 0, curValues[3]));
          }
        }
      }
    });
    L2.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
          long[] curValues = dataProperty().get().getState();
          if (curValues[1] == 0) {
            setData(new ReefState(curValues[0], 1, curValues[2], curValues[3]));
          } else {
            setData(new ReefState(curValues[0], 0, curValues[2], curValues[3]));
          }
        }
      }
    });
    L1.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
          long[] curValues = dataProperty().get().getState();
          if (curValues[0] == 0) {
            setData(new ReefState(1, curValues[1], curValues[2], curValues[3]));
          } else {
            setData(new ReefState(0, curValues[1], curValues[2], curValues[3]));
          }
        }
      }
    });
  }

  /**
   * Set error message, or clear error message by passing a null string
   * 
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
   * 
   * @param color Color to use
   * @return The built background
   */
  private Background createSolidColorBackground(Color color) {
    return new Background(new BackgroundFill(color, null, null));
  }

  /**
   * <br>
   * Choose the color to use based on current widget state </br>
   * <br>
   * &#8226 Box will be <b>gray</b> if a node has no object placed on it </br>
   * <br>
   * &#8226 Box will be <b>green</b> if a node has coral placed in it </br>
   * <br>
   * &#8226 Box wlil be <b>yellow</b> if operator is hovering over node in
   * Shuffleboard </br>
   * <br>
   * &#8226 Box will be <b>orange</b> if node placement is queued </br>
   * <br>
   * &#8226 Box will be <b>red</b> if robot detects that placement on node is
   * invalid (typically because cubes can't be placed
   * on cone nodes and vice versa) </br>
   * 
   * @return Color to use
   */
  private Color getL1Color() {
    if (getData() != null) {
      final int data = (int) (getData().getState())[0];
      Color[] colorArr = { Color.GRAY, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED };
      return colorArr[data];
    } else {
      return Color.GRAY;
    }
  }

  private Color getL2Color() {
    if (getData() != null) {
      final int data = (int) (getData().getState())[1];
      Color[] colorArr = { Color.GRAY, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED };
      return colorArr[data];
    } else {
      return Color.GRAY;
    }
  }

  private Color getL3Color() {
    if (getData() != null) {
      final int data = (int) (getData().getState())[2];
      Color[] colorArr = { Color.GRAY, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED };
      return colorArr[data];
    } else {
      return Color.GRAY;
    }
  }

  private Color getL4Color() {
    if (getData() != null) {
      final int data = (int) (getData().getState())[3];
      Color[] colorArr = { Color.GRAY, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED };
      return colorArr[data];
    } else {
      return Color.GRAY;
    }
  }

  @Override
  public Pane getView() {
    return root;
  }
}

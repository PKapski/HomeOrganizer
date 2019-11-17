package pl.polsl.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EventColor
 */
@Validated

public class EventColor   {
  @JsonProperty("primary")
  private String primary = null;

  @JsonProperty("secondary")
  private String secondary = null;

  public EventColor primary(String primary) {
    this.primary = primary;
    return this;
  }

  /**
   * Event primary color
   * @return primary
  **/
  @ApiModelProperty(value = "Event primary color")


  public String getPrimary() {
    return primary;
  }

  public void setPrimary(String primary) {
    this.primary = primary;
  }

  public EventColor secondary(String secondary) {
    this.secondary = secondary;
    return this;
  }

  /**
   * Event secondary color
   * @return secondary
  **/
  @ApiModelProperty(value = "Event secondary color")


  public String getSecondary() {
    return secondary;
  }

  public void setSecondary(String secondary) {
    this.secondary = secondary;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventColor eventColor = (EventColor) o;
    return Objects.equals(this.primary, eventColor.primary) &&
        Objects.equals(this.secondary, eventColor.secondary);
  }

  @Override
  public int hashCode() {
    return Objects.hash(primary, secondary);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventColor {\n");
    
    sb.append("    primary: ").append(toIndentedString(primary)).append("\n");
    sb.append("    secondary: ").append(toIndentedString(secondary)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


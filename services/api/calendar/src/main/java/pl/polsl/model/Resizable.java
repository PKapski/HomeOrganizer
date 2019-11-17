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
 * Resizable
 */
@Validated

public class Resizable   {
  @JsonProperty("beforeStart")
  private Boolean beforeStart = null;

  @JsonProperty("afterEnd")
  private Boolean afterEnd = null;

  public Resizable beforeStart(Boolean beforeStart) {
    this.beforeStart = beforeStart;
    return this;
  }

  /**
   * Get beforeStart
   * @return beforeStart
  **/
  @ApiModelProperty(value = "")


  public Boolean isBeforeStart() {
    return beforeStart;
  }

  public void setBeforeStart(Boolean beforeStart) {
    this.beforeStart = beforeStart;
  }

  public Resizable afterEnd(Boolean afterEnd) {
    this.afterEnd = afterEnd;
    return this;
  }

  /**
   * Get afterEnd
   * @return afterEnd
  **/
  @ApiModelProperty(value = "")


  public Boolean isAfterEnd() {
    return afterEnd;
  }

  public void setAfterEnd(Boolean afterEnd) {
    this.afterEnd = afterEnd;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Resizable resizable = (Resizable) o;
    return Objects.equals(this.beforeStart, resizable.beforeStart) &&
        Objects.equals(this.afterEnd, resizable.afterEnd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(beforeStart, afterEnd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Resizable {\n");
    
    sb.append("    beforeStart: ").append(toIndentedString(beforeStart)).append("\n");
    sb.append("    afterEnd: ").append(toIndentedString(afterEnd)).append("\n");
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


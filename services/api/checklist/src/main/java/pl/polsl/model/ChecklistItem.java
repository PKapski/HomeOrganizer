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
 * ChecklistItem
 */
@Validated

public class ChecklistItem   {
  @JsonProperty("message")
  private String message = null;

  @JsonProperty("isChecked")
  private Boolean isChecked = false;

  public ChecklistItem message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Certain checklist item message
   * @return message
  **/
  @ApiModelProperty(required = true, value = "Certain checklist item message")
  @NotNull


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ChecklistItem isChecked(Boolean isChecked) {
    this.isChecked = isChecked;
    return this;
  }

  /**
   * Describes if item is done or not
   * @return isChecked
  **/
  @ApiModelProperty(required = true, value = "Describes if item is done or not")
  @NotNull


  public Boolean isIsChecked() {
    return isChecked;
  }

  public void setIsChecked(Boolean isChecked) {
    this.isChecked = isChecked;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChecklistItem checklistItem = (ChecklistItem) o;
    return Objects.equals(this.message, checklistItem.message) &&
        Objects.equals(this.isChecked, checklistItem.isChecked);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, isChecked);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChecklistItem {\n");
    
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    isChecked: ").append(toIndentedString(isChecked)).append("\n");
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


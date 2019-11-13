package pl.polsl.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import pl.polsl.model.Checklist;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ChecklistsPaging
 */
@Validated

public class ChecklistsPaging   {
  @JsonProperty("array")
  @Valid
  private List<Checklist> array = null;

  @JsonProperty("maxItems")
  private Integer maxItems = null;

  public ChecklistsPaging array(List<Checklist> array) {
    this.array = array;
    return this;
  }

  public ChecklistsPaging addArrayItem(Checklist arrayItem) {
    if (this.array == null) {
      this.array = new ArrayList<>();
    }
    this.array.add(arrayItem);
    return this;
  }

  /**
   * Get array
   * @return array
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Checklist> getArray() {
    return array;
  }

  public void setArray(List<Checklist> array) {
    this.array = array;
  }

  public ChecklistsPaging maxItems(Integer maxItems) {
    this.maxItems = maxItems;
    return this;
  }

  /**
   * Get maxItems
   * @return maxItems
  **/
  @ApiModelProperty(value = "")


  public Integer getMaxItems() {
    return maxItems;
  }

  public void setMaxItems(Integer maxItems) {
    this.maxItems = maxItems;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChecklistsPaging checklistsPaging = (ChecklistsPaging) o;
    return Objects.equals(this.array, checklistsPaging.array) &&
        Objects.equals(this.maxItems, checklistsPaging.maxItems);
  }

  @Override
  public int hashCode() {
    return Objects.hash(array, maxItems);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChecklistsPaging {\n");
    
    sb.append("    array: ").append(toIndentedString(array)).append("\n");
    sb.append("    maxItems: ").append(toIndentedString(maxItems)).append("\n");
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


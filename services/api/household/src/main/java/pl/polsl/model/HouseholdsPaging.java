package pl.polsl.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import pl.polsl.model.Household;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * HouseholdsPaging
 */
@Validated

public class HouseholdsPaging   {
  @JsonProperty("array")
  @Valid
  private List<Household> array = null;

  @JsonProperty("maxItems")
  private Integer maxItems = null;

  public HouseholdsPaging array(List<Household> array) {
    this.array = array;
    return this;
  }

  public HouseholdsPaging addArrayItem(Household arrayItem) {
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

  public List<Household> getArray() {
    return array;
  }

  public void setArray(List<Household> array) {
    this.array = array;
  }

  public HouseholdsPaging maxItems(Integer maxItems) {
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
    HouseholdsPaging householdsPaging = (HouseholdsPaging) o;
    return Objects.equals(this.array, householdsPaging.array) &&
        Objects.equals(this.maxItems, householdsPaging.maxItems);
  }

  @Override
  public int hashCode() {
    return Objects.hash(array, maxItems);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HouseholdsPaging {\n");
    
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


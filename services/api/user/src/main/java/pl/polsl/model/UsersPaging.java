package pl.polsl.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import pl.polsl.model.User;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UsersPaging
 */
@Validated

public class UsersPaging   {
  @JsonProperty("array")
  @Valid
  private List<User> array = null;

  @JsonProperty("maxItems")
  private Integer maxItems = null;

  public UsersPaging array(List<User> array) {
    this.array = array;
    return this;
  }

  public UsersPaging addArrayItem(User arrayItem) {
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

  public List<User> getArray() {
    return array;
  }

  public void setArray(List<User> array) {
    this.array = array;
  }

  public UsersPaging maxItems(Integer maxItems) {
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
    UsersPaging usersPaging = (UsersPaging) o;
    return Objects.equals(this.array, usersPaging.array) &&
        Objects.equals(this.maxItems, usersPaging.maxItems);
  }

  @Override
  public int hashCode() {
    return Objects.hash(array, maxItems);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsersPaging {\n");
    
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


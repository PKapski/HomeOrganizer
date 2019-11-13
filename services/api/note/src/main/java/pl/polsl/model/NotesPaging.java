package pl.polsl.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import pl.polsl.model.Note;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NotesPaging
 */
@Validated

public class NotesPaging   {
  @JsonProperty("array")
  @Valid
  private List<Note> array = null;

  @JsonProperty("maxItems")
  private Integer maxItems = null;

  public NotesPaging array(List<Note> array) {
    this.array = array;
    return this;
  }

  public NotesPaging addArrayItem(Note arrayItem) {
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

  public List<Note> getArray() {
    return array;
  }

  public void setArray(List<Note> array) {
    this.array = array;
  }

  public NotesPaging maxItems(Integer maxItems) {
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
    NotesPaging notesPaging = (NotesPaging) o;
    return Objects.equals(this.array, notesPaging.array) &&
        Objects.equals(this.maxItems, notesPaging.maxItems);
  }

  @Override
  public int hashCode() {
    return Objects.hash(array, maxItems);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NotesPaging {\n");
    
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


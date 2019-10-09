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
 * Household
 */
@Validated

public class Household   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("image")
  private byte[] image = null;

  public Household id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Household Id providing uniqueness in database
   * @return id
  **/
  @ApiModelProperty(readOnly = true, value = "Household Id providing uniqueness in database",hidden = true)


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Household name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the household, doesn't have to be unique
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Name of the household, doesn't have to be unique")
  @NotNull

@Size(max=20) 
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Household description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Description of a household
   * @return description
  **/
  @ApiModelProperty(value = "Description of a household")

@Size(max=250) 
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Household image(byte[] image) {
    this.image = image;
    return this;
  }

  /**
   * Household image
   * @return image
  **/
  @ApiModelProperty(value = "Household image")


  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Household household = (Household) o;
    return Objects.equals(this.id, household.id) &&
        Objects.equals(this.name, household.name) &&
        Objects.equals(this.description, household.description) &&
        Objects.equals(this.image, household.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, image);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Household {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
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


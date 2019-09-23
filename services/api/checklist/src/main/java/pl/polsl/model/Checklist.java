package pl.polsl.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Checklist
 */
@Validated

public class Checklist   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("title")
  private String title = "";

  @JsonProperty("itemList")
  @Valid
  private List<String> itemList = new ArrayList<>();

  @JsonProperty("recipent")
  private String recipent = null;

  @JsonProperty("creator")
  private String creator = null;

  @JsonProperty("creationDate")
  private LocalDate creationDate = null;

  @JsonProperty("expirationDate")
  private LocalDate expirationDate = null;

  public Checklist id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Checklist Id providing uniqueness in database
   * @return id
  **/
  @ApiModelProperty(readOnly = true, value = "Checklist Id providing uniqueness in database", hidden = true)


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Checklist title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Checklist tile
   * @return title
  **/
  @ApiModelProperty(value = "Checklist tile")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Checklist itemList(List<String> itemList) {
    this.itemList = itemList;
    return this;
  }

  public Checklist addItemListItem(String itemListItem) {
    this.itemList.add(itemListItem);
    return this;
  }

  /**
   * List of items added to the checklist
   * @return itemList
  **/
  @ApiModelProperty(required = true, value = "List of items added to the checklist")
  @NotNull


  public List<String> getItemList() {
    return itemList;
  }

  public void setItemList(List<String> itemList) {
    this.itemList = itemList;
  }

  public Checklist recipent(String recipent) {
    this.recipent = recipent;
    return this;
  }

  /**
   * Username of a person that the checklist is intender for, null means that it is to everyone
   * @return recipent
  **/
  @ApiModelProperty(value = "Username of a person that the checklist is intender for, null means that it is to everyone")


  public String getRecipent() {
    return recipent;
  }

  public void setRecipent(String recipent) {
    this.recipent = recipent;
  }

  public Checklist creator(String creator) {
    this.creator = creator;
    return this;
  }

  /**
   * Username of a person that created the checklist
   * @return creator
  **/
  @ApiModelProperty(value = "Username of a person that created the checklist")


  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public Checklist creationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Date when the checklist was created
   * @return creationDate
  **/
  @ApiModelProperty(value = "Date when the checklist was created")

  @Valid

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public Checklist expirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
    return this;
  }

  /**
   * Expiration date of a checklist
   * @return expirationDate
  **/
  @ApiModelProperty(value = "Expiration date of a checklist")

  @Valid

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Checklist checklist = (Checklist) o;
    return Objects.equals(this.id, checklist.id) &&
        Objects.equals(this.title, checklist.title) &&
        Objects.equals(this.itemList, checklist.itemList) &&
        Objects.equals(this.recipent, checklist.recipent) &&
        Objects.equals(this.creator, checklist.creator) &&
        Objects.equals(this.creationDate, checklist.creationDate) &&
        Objects.equals(this.expirationDate, checklist.expirationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, itemList, recipent, creator, creationDate, expirationDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Checklist {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    itemList: ").append(toIndentedString(itemList)).append("\n");
    sb.append("    recipent: ").append(toIndentedString(recipent)).append("\n");
    sb.append("    creator: ").append(toIndentedString(creator)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    expirationDate: ").append(toIndentedString(expirationDate)).append("\n");
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


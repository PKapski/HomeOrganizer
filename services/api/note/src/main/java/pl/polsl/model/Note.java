package pl.polsl.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Note
 */
@Validated

public class Note   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("title")
  private String title = "";

  @JsonProperty("message")
  private String message = "";

  @JsonProperty("recipent")
  private String recipent = null;

  @JsonProperty("creator")
  private String creator = null;

  @JsonProperty("householdId")
  private String householdId = "testGroup";

  @JsonProperty("creationDate")
  private LocalDate creationDate = null;

  @JsonProperty("expirationDate")
  private LocalDate expirationDate = null;

  public Note id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Note Id providing uniqueness in database
   * @return id
  **/
  @ApiModelProperty(readOnly = true, value = "Note Id providing uniqueness in database")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Note title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Note tile
   * @return title
  **/
  @ApiModelProperty(value = "Note tile")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Note message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Note message
   * @return message
  **/
  @ApiModelProperty(required = true, value = "Note message")
  @NotNull


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Note recipent(String recipent) {
    this.recipent = recipent;
    return this;
  }

  /**
   * Username of a person that the message is written to, null means that it is to everyone
   * @return recipent
  **/
  @ApiModelProperty(value = "Username of a person that the message is written to, null means that it is to everyone")


  public String getRecipent() {
    return recipent;
  }

  public void setRecipent(String recipent) {
    this.recipent = recipent;
  }

  public Note creator(String creator) {
    this.creator = creator;
    return this;
  }

  /**
   * Username of a person that created the note
   * @return creator
  **/
  @ApiModelProperty(value = "Username of a person that created the note")


  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public Note householdId(String householdId) {
    this.householdId = householdId;
    return this;
  }

  /**
   * Household id that the note is written to
   * @return householdId
  **/
  @ApiModelProperty(value = "Household id that the note is written to")


  public String getHouseholdId() {
    return householdId;
  }

  public void setHouseholdId(String householdId) {
    this.householdId = householdId;
  }

  public Note creationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Date when the note was created
   * @return creationDate
  **/
  @ApiModelProperty(value = "Date when the note was created")

  @Valid

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public Note expirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
    return this;
  }

  /**
   * Expiration date of a note
   * @return expirationDate
  **/
  @ApiModelProperty(value = "Expiration date of a note")

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
    Note note = (Note) o;
    return Objects.equals(this.id, note.id) &&
        Objects.equals(this.title, note.title) &&
        Objects.equals(this.message, note.message) &&
        Objects.equals(this.recipent, note.recipent) &&
        Objects.equals(this.creator, note.creator) &&
        Objects.equals(this.householdId, note.householdId) &&
        Objects.equals(this.creationDate, note.creationDate) &&
        Objects.equals(this.expirationDate, note.expirationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, message, recipent, creator, householdId, creationDate, expirationDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Note {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    recipent: ").append(toIndentedString(recipent)).append("\n");
    sb.append("    creator: ").append(toIndentedString(creator)).append("\n");
    sb.append("    householdId: ").append(toIndentedString(householdId)).append("\n");
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


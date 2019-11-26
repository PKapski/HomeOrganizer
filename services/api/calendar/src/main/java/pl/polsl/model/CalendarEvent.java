package pl.polsl.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import pl.polsl.model.EventColor;
import pl.polsl.model.Resizable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CalendarEvent
 */
@Validated

public class CalendarEvent   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("householdId")
  private String householdId = null;

  @JsonProperty("title")
  private String title = "";

  @JsonProperty("start")
  private OffsetDateTime start = null;

  @JsonProperty("end")
  private OffsetDateTime end = null;

  @JsonProperty("color")
  private EventColor color = null;

  @JsonProperty("allDay")
  private Boolean allDay = null;

  @JsonProperty("draggable")
  private Boolean draggable = null;

  @JsonProperty("resizable")
  private Resizable resizable = null;

  public CalendarEvent id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Event Id providing uniqueness in database
   * @return id
  **/
  @ApiModelProperty(readOnly = true, value = "Event Id providing uniqueness in database", hidden = true)


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CalendarEvent householdId(String householdId) {
    this.householdId = householdId;
    return this;
  }

  /**
   * Household that event is created in
   * @return householdId
  **/
  @ApiModelProperty(value = "Household that event is created in")


  public String getHouseholdId() {
    return householdId;
  }

  public void setHouseholdId(String householdId) {
    this.householdId = householdId;
  }

  public CalendarEvent title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Event title
   * @return title
  **/
  @ApiModelProperty(required = true, value = "Event title")
  @NotNull


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public CalendarEvent start(OffsetDateTime start) {
    this.start = start;
    return this;
  }

  /**
   * Event start date
   * @return start
  **/
  @ApiModelProperty(required = true, value = "Event start date")
  @NotNull

  @Valid

  public OffsetDateTime getStart() {
    return start;
  }

  public void setStart(OffsetDateTime start) {
    this.start = start;
  }

  public CalendarEvent end(OffsetDateTime end) {
    this.end = end;
    return this;
  }

  /**
   * Event end date
   * @return end
  **/
  @ApiModelProperty(value = "Event end date")

  @Valid

  public OffsetDateTime getEnd() {
    return end;
  }

  public void setEnd(OffsetDateTime end) {
    this.end = end;
  }

  public CalendarEvent color(EventColor color) {
    this.color = color;
    return this;
  }

  /**
   * Get color
   * @return color
  **/
  @ApiModelProperty(value = "")

  @Valid

  public EventColor getColor() {
    return color;
  }

  public void setColor(EventColor color) {
    this.color = color;
  }

  public CalendarEvent allDay(Boolean allDay) {
    this.allDay = allDay;
    return this;
  }

  /**
   * Describes if event should be counter in full days
   * @return allDay
  **/
  @ApiModelProperty(value = "Describes if event should be counter in full days")


  public Boolean isAllDay() {
    return allDay;
  }

  public void setAllDay(Boolean allDay) {
    this.allDay = allDay;
  }

  public CalendarEvent draggable(Boolean draggable) {
    this.draggable = draggable;
    return this;
  }

  /**
   * Describes if even should be draggable
   * @return draggable
  **/
  @ApiModelProperty(value = "Describes if even should be draggable")


  public Boolean isDraggable() {
    return draggable;
  }

  public void setDraggable(Boolean draggable) {
    this.draggable = draggable;
  }

  public CalendarEvent resizable(Resizable resizable) {
    this.resizable = resizable;
    return this;
  }

  /**
   * Get resizable
   * @return resizable
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Resizable getResizable() {
    return resizable;
  }

  public void setResizable(Resizable resizable) {
    this.resizable = resizable;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CalendarEvent calendarEvent = (CalendarEvent) o;
    return Objects.equals(this.id, calendarEvent.id) &&
        Objects.equals(this.householdId, calendarEvent.householdId) &&
        Objects.equals(this.title, calendarEvent.title) &&
        Objects.equals(this.start, calendarEvent.start) &&
        Objects.equals(this.end, calendarEvent.end) &&
        Objects.equals(this.color, calendarEvent.color) &&
        Objects.equals(this.allDay, calendarEvent.allDay) &&
        Objects.equals(this.draggable, calendarEvent.draggable) &&
        Objects.equals(this.resizable, calendarEvent.resizable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, householdId, title, start, end, color, allDay, draggable, resizable);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CalendarEvent {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    householdId: ").append(toIndentedString(householdId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    end: ").append(toIndentedString(end)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    allDay: ").append(toIndentedString(allDay)).append("\n");
    sb.append("    draggable: ").append(toIndentedString(draggable)).append("\n");
    sb.append("    resizable: ").append(toIndentedString(resizable)).append("\n");
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


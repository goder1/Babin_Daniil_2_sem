package main_package.entity;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "user_action")
@Getter
@Setter
public class UserAction {

  @JsonProperty("id")
  @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
  private UUID id;

  @JsonProperty("eventTime")
  @PrimaryKeyColumn(name = "event_time", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
  private Instant eventTime;

  @JsonProperty("eventType")
  @Column(value = "event_type")
  private String eventType;

  public UserAction() {}

  @JsonCreator
  public UserAction(@JsonProperty("id") UUID userId,
                    @JsonProperty("event_time") Instant eventTime,
                    @JsonProperty("event_type") String eventType) {
    this.id = userId;
    this.eventTime = eventTime;
    this.eventType = eventType;
  }
}
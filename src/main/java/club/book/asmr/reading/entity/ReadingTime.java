package club.book.asmr.reading.entity;

import club.book.asmr.common.entity.BaseTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class ReadingTime extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "reading_time_id", nullable = false)
    private Long id;
    @Column
    @Default
    private Integer minute;
    @ManyToOne
    private Reading reading;
}
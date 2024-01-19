package epicode.u2w2d5BEdispositivi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue
    private Long id;
    // types can be various like(mobile, laptop,...) can not be definitive
    private String type;
    // 0: Available, 1: In Maintain, 2: In use, 3: Removed
    private int availability;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Device{");
        sb.append("id=").append(id);
        sb.append(", type='").append(type).append('\'');
        sb.append(", availability=").append(availability);
        sb.append('}');
        return sb.toString();
    }
}

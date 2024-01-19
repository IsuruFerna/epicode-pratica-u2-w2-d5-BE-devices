package epicode.u2w2d5BEdispositivi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Device {
    @Id
    @GeneratedValue
    private Long id;
    // types can be various like(mobile, laptop,...) can not be definitive
    private String type;
    // 0: Available, 1: In Maintain, 2: In use, 3: Removed
    private int availability;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

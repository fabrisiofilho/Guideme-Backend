package br.com.fabrisio.guideme.dto.user;

import br.com.fabrisio.guideme.dto.roadmap.StepDTO;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProgressDTO {
    private boolean isDone;
    private StepDTO step;
    private UserDTO user;
}

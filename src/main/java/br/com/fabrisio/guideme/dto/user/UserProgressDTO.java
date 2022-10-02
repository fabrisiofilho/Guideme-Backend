package br.com.fabrisio.guideme.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProgressDTO {
    private boolean isDone;
    private boolean isOpen;
    private Long stepId;
    private Long userId;
}

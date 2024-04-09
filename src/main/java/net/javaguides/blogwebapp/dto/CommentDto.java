package net.javaguides.blogwebapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private long id;
    @NotEmpty(message = "name should not be empty")
    private String name;
    @NotEmpty(message = "email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "write your comment")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}

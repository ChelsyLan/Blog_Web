package net.javaguides.blogwebapp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

//dto to transfer data between view layer and controller layer
//entity operate on database, dto for View and Controller
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private long id;
    @NotEmpty(message = "post title should not be empty")
    private String title;
    private String url;
    @NotEmpty(message = "post content should not be empty")
    private String content;
    @NotEmpty(message = "post description should not be empty")
    private String shortDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Set<CommentDto> comments;


}

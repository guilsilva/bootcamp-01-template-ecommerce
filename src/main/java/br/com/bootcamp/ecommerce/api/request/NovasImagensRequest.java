package br.com.bootcamp.ecommerce.api.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class NovasImagensRequest {
    @Size(min = 1, message = "{SizeMin1}")
    @NotNull(message = "{NotBlank}")
    private List<MultipartFile> imagens = new ArrayList<>();
}

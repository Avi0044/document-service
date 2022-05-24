package in.nbt.document.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentContent implements Serializable {
    public String key;
    public String value;
}

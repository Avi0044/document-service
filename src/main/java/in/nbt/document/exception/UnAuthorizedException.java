package in.nbt.document.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException(String message){
        super(message);
    }
}

package kr.hs.dgsw.web01blog.Protocol;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttachmentProtocol {
    private String storepath;
    private String originalName;

    public AttachmentProtocol(String storepath, String originalName) {
        this.storepath = storepath;
        this.originalName = originalName;
    }
}

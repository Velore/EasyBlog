package velore.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import velore.po.Tag;

/**
 * @author Velore
 * @date 2022/4/12
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagRequest {

    private String tagName;

    private String description;

    public Tag getTag(){
        Tag tag = new Tag();
        tag.setId(null);
        tag.setName(tagName);
        tag.setDescription(description);
        return tag;
    }
}

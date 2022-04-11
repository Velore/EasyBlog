package velore.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 继承该类：保存对象被修改的时间
 * @author Velore
 * @date 2022/4/10
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifiedRecord {

    protected LocalDateTime createTime;

    protected LocalDateTime updateTime;
}

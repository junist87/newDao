package testVO;

import com.ciaosgarage.newDao.vo.DbColumn;
import com.ciaosgarage.newDao.vo.Vo;

public class PhotoVO extends Vo {
    @DbColumn
    public String accounPk;
    @DbColumn
    public String path;
}

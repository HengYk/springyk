package cn.edu.xidian.ictt.springyk.dao;

import cn.edu.xidian.ictt.springyk.entity.Address;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface AddressDao {

    /**
     * 数组分页
     *
     * @return
     */
    List<Address> queryAddressByArray();

    /**
     * SQL分页
     *
     * @return
     */
    List<Address> queryAddressBySql(Map<String, Object> data);

    /**
     * 拦截器分页
     *
     * @return
     */
    List<Address> queryAddressByPage(Map<String, Object> data);

    /**
     * RowBounds分页
     *
     * @return
     */
    List<Address> queryAddressByRowBounds(RowBounds rowBounds);
}

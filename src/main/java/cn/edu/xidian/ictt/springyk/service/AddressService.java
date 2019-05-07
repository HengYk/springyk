package cn.edu.xidian.ictt.springyk.service;

import cn.edu.xidian.ictt.springyk.entity.Address;

import java.util.List;

public interface AddressService {

    /**
     *
     * @param currPage
     * @param pageSize
     * @return
     */
    List<Address> queryAddressByArray(int currPage, int pageSize);

    /**
     *
     * @param currPage
     * @param pageSize
     * @return
     */
    List<Address> queryAddressBySql(int currPage, int pageSize);

    /**
     *
     * @param currPage
     * @param pageSize
     * @return
     */
    List<Address> queryAddressByPage(int currPage, int pageSize);

    /**
     *
     * @param currPage
     * @param pageSize
     * @return
     */
    List<Address> queryAddressByRowBounds(int currPage, int pageSize);
}

package cn.edu.xidian.ictt.springyk.service.impl;

import cn.edu.xidian.ictt.springyk.dao.AddressDao;
import cn.edu.xidian.ictt.springyk.entity.Address;
import cn.edu.xidian.ictt.springyk.service.AddressService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public List<Address> queryAddressByArray(int currPage, int pageSize) {

        List<Address> addressList = addressDao.queryAddressByArray();

        int firstIndex = (currPage - 1) * pageSize;
        int lastIndex = currPage * pageSize;

        return addressList.subList(firstIndex, lastIndex);
    }

    @Override
    public List<Address> queryAddressBySql(int currPage, int pageSize) {

        Map<String, Object> data = new HashMap<>();

        data.put("currIndex", (currPage - 1) * pageSize);
        data.put("pageSize", pageSize);

        return addressDao.queryAddressBySql(data);
    }

    @Override
    public List<Address> queryAddressByPage(int currPage, int pageSize) {

        Map<String, Object> data = new HashMap<>();

        data.put("currPage", currPage);
        data.put("pageSize", pageSize);

        return addressDao.queryAddressByPage(data);
    }

    @Override
    public List<Address> queryAddressByRowBounds(int currPage, int pageSize) {
        return addressDao.queryAddressByRowBounds(
                new RowBounds((currPage - 1) * pageSize, pageSize));
    }
}

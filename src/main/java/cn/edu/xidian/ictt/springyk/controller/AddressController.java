package cn.edu.xidian.ictt.springyk.controller;

import cn.edu.xidian.ictt.springyk.entity.Address;
import cn.edu.xidian.ictt.springyk.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ResponseBody
    @RequestMapping("/address/array/{currPage}/{pageSize}")
    public List<Address> getAddressByArray(@PathVariable("currPage") int currPage, @PathVariable("pageSize") int pageSize) {
        return addressService.queryAddressByArray(currPage, pageSize);
    }

    @ResponseBody
    @RequestMapping("/address/sql/{currPage}/{pageSize}")
    public List<Address> getAddressBySql(@PathVariable("currPage") int currPage, @PathVariable("pageSize") int pageSize) {
        return addressService.queryAddressBySql(currPage, pageSize);
    }

    @ResponseBody
    @RequestMapping("/address/page/{currPage}/{pageSize}")
    public List<Address> getAddressByPage(@PathVariable("currPage") int currPage, @PathVariable("pageSize") int pageSize) {
        return addressService.queryAddressByPage(currPage, pageSize);
    }

    @ResponseBody
    @RequestMapping("/address/RowBounds/{currPage}/{pageSize}")
    public List<Address> getAddressByRowBounds(@PathVariable("currPage") int currPage, @PathVariable("pageSize") int pageSize) {
        return addressService.queryAddressByRowBounds(currPage, pageSize);
    }
}
package com.wengzhoujun.vechat.base;

import com.wengzhoujun.vechat.domain.Result;
import com.wengzhoujun.vechat.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2019/7/23.
 *
 * @author WengZhoujun
 */
public abstract class BaseController<E, ID extends Serializable> {

    /**
     * 获取service
     * @return
     */
    @Autowired
    public abstract BaseService<E,ID> getService();

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "通过id获取")
    public Result<E> get(@PathVariable ID id){

        E entity = getService().get(id);
        return ResponseUtil.ok(entity);
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取全部数据")
    public Result<List<E>> getAll(){

        List<E> list = getService().getAll();
        return ResponseUtil.ok(list);
    }

    @RequestMapping(value = "/getByPage",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页获取")
    public Result<Page<E>> getByPage(@PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable){

        Page<E> data = getService().findAll(pageable);
        return ResponseUtil.ok(data);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存数据")
    public Result<E> save(@ModelAttribute E entity){

        E e = getService().save(entity);
        return ResponseUtil.ok(e);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新数据")
    public Result<E> update(@ModelAttribute E entity){

        E e = getService().update(entity);
        return ResponseUtil.ok(e);
    }

    @RequestMapping(value = "/delByIds/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delAllByIds(@PathVariable ID[] ids){

        for(ID id:ids){
            getService().delete(id);
        }
        return ResponseUtil.ok();
    }
}

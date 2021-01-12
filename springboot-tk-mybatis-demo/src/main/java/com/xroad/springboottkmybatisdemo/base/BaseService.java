package com.xroad.springboottkmybatisdemo.base;

import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

public interface BaseService<T> extends Mapper<T> {
    @Override
    int deleteByPrimaryKey(Object o);

    @Override
    int delete(T o);

    @Override
    int insert(T o);

    @Override
    int insertSelective(T o);

    @Override
    List<T> selectAll();

    @Override
    T selectByPrimaryKey(Object o);

    @Override
    public int selectCount(T o);

    @Override
    public List<T> select(T o);

    @Override
    public T selectOne(T o);

    @Override
    public int updateByPrimaryKey(T o);

    @Override
    public int updateByPrimaryKeySelective(T o);

    @Override
    public int deleteByExample(Object o);

    @Override
    public List<T> selectByExample(Object o);

    @Override
    public int selectCountByExample(Object o);

    @Override
    public T selectOneByExample(Object o);

    @Override
    public int updateByExample(T o, Object o2);

    @Override
    public int updateByExampleSelective(T o, Object o2);

    @Override
    public List<T> selectByExampleAndRowBounds(Object o, RowBounds rowBounds);

    @Override
    public List<T> selectByRowBounds(T o, RowBounds rowBounds);
}

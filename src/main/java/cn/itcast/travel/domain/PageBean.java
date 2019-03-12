package cn.itcast.travel.domain;

import java.util.List;

/**
 * @Author: 杨朝阳
 * @Version: V1.0
 * @Date: 2019/3/5 20:46
 * @Description: 分页对象
 **/
public class PageBean<T> {
    private int totalCount;
    private int totalPage;
    private int currentPage;
    private int pageSize;

    private List<T> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

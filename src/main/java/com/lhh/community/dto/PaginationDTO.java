package com.lhh.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: community
 * @Date: 2020/1/5 15:22
 * @Author: lhh
 * @Description:
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage,Integer page)
    {
        this.totalPage = totalPage;
        this.page = page;

        pages.add(page);
        for(int i = 1;i <= 3;i++)
        {
            if(page - i > 0) pages.add(0,page - i);
            if(page + i <= totalPage)pages.add(page + i);
        }

        if(page == 1) showPrevious = false;
        else showPrevious = true;

        if(page == totalPage) showNext = false;
        else showNext = true;

        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }

    }


}



















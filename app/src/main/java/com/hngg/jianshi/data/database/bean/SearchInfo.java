package com.hngg.jianshi.data.database.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SearchInfo {
    @Id(autoincrement = true)
    private Long id;
    private String searchField;
    private Long searchTime;
    @Generated(hash = 1354439885)
    public SearchInfo(Long id, String searchField, Long searchTime) {
        this.id = id;
        this.searchField = searchField;
        this.searchTime = searchTime;
    }
    @Generated(hash = 2142761523)
    public SearchInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSearchField() {
        return this.searchField;
    }
    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }
    public Long getSearchTime() {
        return this.searchTime;
    }
    public void setSearchTime(Long searchTime) {
        this.searchTime = searchTime;
    }
}

package com.hngg.jianshi.data.bean.home;

/**
 * Date: 2020/11/24
 * Timer: 18:04
 * Author: nedhuo
 * Description:
 */

import java.util.List;

/**
 * Auto-generated: 2020-11-22 14:0:2
 */
public class Header {

    private int id;
    private String title;
    private String font;
    private String subTitle;
    private String subTitleFont;
    private String textAlign;
    private String cover;
    private Label label;
    private String actionUrl;
    private List<LabelList> labelList;
    private String rightText;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getFont() {
        return font;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitleFont(String subTitleFont) {
        this.subTitleFont = subTitleFont;
    }

    public String getSubTitleFont() {
        return subTitleFont;
    }

    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Label getLabel() {
        return label;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setLabelList(List<LabelList> labelList) {
        this.labelList = labelList;
    }

    public List<LabelList> getLabelList() {
        return labelList;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public String getRightText() {
        return rightText;
    }

}

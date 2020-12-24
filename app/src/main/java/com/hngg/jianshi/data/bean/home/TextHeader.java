package com.hngg.jianshi.data.bean.home;

import java.util.List;

/**
 * Date: 2020/11/24
 * Timer: 18:05
 * Author: nedhuo
 * Description:
 */
public class TextHeader extends Data {

    private String dataType;
    private String text;
    private String font;
    private List<String> adTrack;
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public String getDataType() {
        return dataType;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setFont(String font) {
        this.font = font;
    }
    public String getFont() {
        return font;
    }

    @Override
    public List<String> getAdTrack() {
        return adTrack;
    }

    @Override
    public void setAdTrack(List<String> adTrack) {
        this.adTrack = adTrack;
    }
}


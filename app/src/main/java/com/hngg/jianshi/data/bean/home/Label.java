package com.hngg.jianshi.data.bean.home;

import java.io.Serializable;

/**
* Date: 2021/2/18
* Timer: 21:21
* Author: nedhuo
* Description:
*/
class Label implements Serializable {

   private String text;
   private String card;
   private String detail;
   public void setText(String text) {
       this.text = text;
   }
   public String getText() {
       return text;
   }

   public void setCard(String card) {
       this.card = card;
   }
   public String getCard() {
       return card;
   }

   public void setDetail(String detail) {
       this.detail = detail;
   }
   public String getDetail() {
       return detail;
   }

}

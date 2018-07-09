
package com.syntrontech.pmo.pmo.member;

import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetNotifyPreferencesResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getNotifyPreferencesResult"
})
@XmlRootElement(name = "GetNotifyPreferencesResponse")
public class GetNotifyPreferencesResponse {

    @XmlElement(name = "GetNotifyPreferencesResult")
    protected String getNotifyPreferencesResult;

    /**
     * 取得 getNotifyPreferencesResult 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetNotifyPreferencesResult() {
        return getNotifyPreferencesResult;
    }

    /**
     * 設定 getNotifyPreferencesResult 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetNotifyPreferencesResult(String value) {
        this.getNotifyPreferencesResult = value;
    }

}


package com.syntrontech.pmo.pmo.vitalsign;

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
 *         &lt;element name="GetVitalRecordResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getVitalRecordResult"
})
@XmlRootElement(name = "GetVitalRecordResponse")
public class GetVitalRecordResponse {

    @XmlElement(name = "GetVitalRecordResult")
    protected String getVitalRecordResult;

    /**
     * 取得 getVitalRecordResult 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetVitalRecordResult() {
        return getVitalRecordResult;
    }

    /**
     * 設定 getVitalRecordResult 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetVitalRecordResult(String value) {
        this.getVitalRecordResult = value;
    }

}

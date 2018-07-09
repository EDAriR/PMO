
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
 *         &lt;element name="RegisterUserByVendorResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "registerUserByVendorResult"
})
@XmlRootElement(name = "RegisterUserByVendorResponse")
public class RegisterUserByVendorResponse {

    @XmlElement(name = "RegisterUserByVendorResult")
    protected String registerUserByVendorResult;

    /**
     * 取得 registerUserByVendorResult 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegisterUserByVendorResult() {
        return registerUserByVendorResult;
    }

    /**
     * 設定 registerUserByVendorResult 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegisterUserByVendorResult(String value) {
        this.registerUserByVendorResult = value;
    }

}

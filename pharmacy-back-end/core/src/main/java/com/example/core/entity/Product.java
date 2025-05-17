package com.example.core.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Integer productId;

    @Basic(optional = false)
    @Column(name = "prod_name", nullable = false)
    private String prodName;
    @Basic(optional = false)
    @Column(name = "prod_descrip", nullable = false)
    private String prodDescription;
    @Basic(optional = false)
    @Column(name = "barcode", unique = true)
    private String barcode;
    @Basic(optional = false)
    @Column(name = "price")
    private Double price;
    @Basic(optional = false)
    @Column(name = "qty")
    private Integer qty;
    @Basic(optional = false)
    @Column(name = "stock_status")
    private String stockStatus;

    @Basic(optional = false)
    @Column(name = "maket_status", nullable = false)
    private String marketPlaceStatus;

    @Basic(optional = false)
    @Column(name = "manufact_date")
    private Date manufactDate;

    @Basic(optional = false)
    @Column(name = "exp_date")
    private Date expDate;

    @Basic(optional = false)
    @Column(name = "creted_at")
    private Date createdAt;

    @Basic(optional = false)
    @Column(name = "created_by")
    private String createdBy;

    @Basic(optional = false)
    @Column(name = "update_at")
    private Date updateAt;

    @Basic(optional = false)
    @Column(name = "update_by")
    private String updateBy;

    public Product(Integer productId, String prodName, String prodDescription, String barcode, Double price, Integer qty, String stockStatus, String marketPlaceStatus, Date manufactDate, Date expDate, Date createdAt, String createdBy, Date updateAt, String updateBy) {
        this.productId = productId;
        this.prodName = prodName;
        this.prodDescription = prodDescription;
        this.barcode = barcode;
        this.price = price;
        this.qty = qty;
        this.stockStatus = stockStatus;
        this.marketPlaceStatus = marketPlaceStatus;
        this.manufactDate = manufactDate;
        this.expDate = expDate;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
    }

    public Product() {

    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getMarketPlaceStatus() {
        return marketPlaceStatus;
    }

    public void setMarketPlaceStatus(String marketPlaceStatus) {
        this.marketPlaceStatus = marketPlaceStatus;
    }

    public Date getManufactDate() {
        return manufactDate;
    }

    public void setManufactDate(Date manufactDate) {
        this.manufactDate = manufactDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}

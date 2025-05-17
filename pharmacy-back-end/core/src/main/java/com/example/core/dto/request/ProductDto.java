package com.example.core.dto.request;

import java.util.Date;


public class ProductDto {
    private Integer productId;
    private String prodName;
    private String prodDescription;
    private String barcode;
    private Double price;
    private Integer qty;
    private String stockStatus;
    private String marketPlaceStatus;
    private Date manufactDate;
    private Date expDate;
    private Date createdAt;
    private String createdBy;
    private Date updateAt;
    private String updateBy;

    public ProductDto(Integer productId, String prodName, String prodDescription, String barcode, Double price, Integer qty, String stockStatus, String marketPlaceStatus, Date manufactDate, Date expDate, Date createdAt, String createdBy, Date updateAt, String updateBy) {
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

    public ProductDto() {
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

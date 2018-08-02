package com.github.hakkatjai.saleswatcher.watcher;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Watcher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String url;
    private String formerPriceAttributeName;
    private String formerPriceAttributeValue;
    private Double formerPrice;
    private String currentPriceAttributeName;
    private String currentPriceAttributeValue;
    private Double currentPrice;

    public Watcher() { }

    public Watcher(Watcher watcher) {
        this.id = watcher.getId();
        this.name = watcher.getName();
        this.description = watcher.getDescription();
        this.url = watcher.getUrl();
        this.formerPriceAttributeName = watcher.getFormerPriceAttributeName();
        this.formerPriceAttributeValue = watcher.getFormerPriceAttributeValue();
        this.formerPrice = watcher.getFormerPrice();
        this.currentPriceAttributeName = watcher.getCurrentPriceAttributeName();
        this.currentPriceAttributeValue = watcher.getCurrentPriceAttributeValue();
        this.currentPrice = watcher.getCurrentPrice();
    }

    public Watcher(String name, String description, String url, String formerPriceAttributeName, String formerPriceAttributeValue, Double formerPrice, String currentPriceAttributeName, String currentPriceAttributeValue, Double currentPrice) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.formerPriceAttributeName = formerPriceAttributeName;
        this.formerPriceAttributeValue = formerPriceAttributeValue;
        this.formerPrice = formerPrice;
        this.currentPriceAttributeName = currentPriceAttributeName;
        this.currentPriceAttributeValue = currentPriceAttributeValue;
        this.currentPrice = currentPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormerPriceAttributeName() {
        return formerPriceAttributeName;
    }

    public void setFormerPriceAttributeName(String formerPriceAttributeName) {
        this.formerPriceAttributeName = formerPriceAttributeName;
    }

    public String getFormerPriceAttributeValue() {
        return formerPriceAttributeValue;
    }

    public void setFormerPriceAttributeValue(String formerPriceAttributeValue) {
        this.formerPriceAttributeValue = formerPriceAttributeValue;
    }

    public Double getFormerPrice() {
        return formerPrice;
    }

    public void setFormerPrice(Double formerPrice) {
        this.formerPrice = formerPrice;
    }

    public String getCurrentPriceAttributeName() {
        return currentPriceAttributeName;
    }

    public void setCurrentPriceAttributeName(String currentPriceAttributeName) {
        this.currentPriceAttributeName = currentPriceAttributeName;
    }

    public String getCurrentPriceAttributeValue() {
        return currentPriceAttributeValue;
    }

    public void setCurrentPriceAttributeValue(String currentPriceAttributeValue) {
        this.currentPriceAttributeValue = currentPriceAttributeValue;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public boolean isCheaperThan(Watcher watcher) {
        if (this.getCurrentPrice() == null || watcher.getCurrentPrice() == null) {
            return false;
        }
        return this.getCurrentPrice() < watcher.getCurrentPrice();
    }
}

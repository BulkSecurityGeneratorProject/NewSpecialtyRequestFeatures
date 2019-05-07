package com.java.service.dto;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.java.domain.WoWorkOrder} entity.
 */
public class WoWorkOrderDTO implements Serializable {

    private Long id;

    @Size(max = 255)
    private String customsBrokerName;

    @Size(max = 255)
    private String customsContactName;

    @Size(max = 255)
    private String customsCurrency;

    @Size(max = 255)
    private String customsPhoneNumber;

    private Double customsValue;

    @Size(max = 255)
    private String fromAirportCode;

    @Max(value = 11)
    private Integer fromLocationType;

    @Size(max = 255)
    private String jobContentDescr;

    @Max(value = 11)
    private Integer shipQuantity;

    @Max(value = 11)
    private Integer jobOriginalCost;

    @Size(max = 255)
    private String quotedAmount;

    @Size(max = 255)
    private String assignTo;

    @Size(max = 255)
    private String toCompany;

    @Max(value = 11)
    private Integer toLocationType;

    private ZonedDateTime dateCreated;

    @Size(max = 255)
    private String jobNumber;

    @Size(max = 255)
    private String jobCustomer;

    private Long serviceType;

    @Size(max = 255)
    private String jobSales;

    private Long jobDeadlineTime;

    private Long jobTimeZone;

    private LocalDate shippingDate;

    private Long shippingTime;

    private Long insuranceType;

    private Boolean isStorage;

    private Boolean isPickPack;

    @Size(max = 255)
    private String shipTotalWeight;

    @Size(max = 255)
    private String shipmentCurrency;

    @Size(max = 255)
    private String shipmentMetric;

    @Max(value = 11)
    private Integer woRequestType;


    private Long woCustomsBrokerageId;

    private Long woStorageId;

    private Long woPickPackId;

    private Long woShippingInfoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomsBrokerName() {
        return customsBrokerName;
    }

    public void setCustomsBrokerName(String customsBrokerName) {
        this.customsBrokerName = customsBrokerName;
    }

    public String getCustomsContactName() {
        return customsContactName;
    }

    public void setCustomsContactName(String customsContactName) {
        this.customsContactName = customsContactName;
    }

    public String getCustomsCurrency() {
        return customsCurrency;
    }

    public void setCustomsCurrency(String customsCurrency) {
        this.customsCurrency = customsCurrency;
    }

    public String getCustomsPhoneNumber() {
        return customsPhoneNumber;
    }

    public void setCustomsPhoneNumber(String customsPhoneNumber) {
        this.customsPhoneNumber = customsPhoneNumber;
    }

    public Double getCustomsValue() {
        return customsValue;
    }

    public void setCustomsValue(Double customsValue) {
        this.customsValue = customsValue;
    }

    public String getFromAirportCode() {
        return fromAirportCode;
    }

    public void setFromAirportCode(String fromAirportCode) {
        this.fromAirportCode = fromAirportCode;
    }

    public Integer getFromLocationType() {
        return fromLocationType;
    }

    public void setFromLocationType(Integer fromLocationType) {
        this.fromLocationType = fromLocationType;
    }

    public String getJobContentDescr() {
        return jobContentDescr;
    }

    public void setJobContentDescr(String jobContentDescr) {
        this.jobContentDescr = jobContentDescr;
    }

    public Integer getShipQuantity() {
        return shipQuantity;
    }

    public void setShipQuantity(Integer shipQuantity) {
        this.shipQuantity = shipQuantity;
    }

    public Integer getJobOriginalCost() {
        return jobOriginalCost;
    }

    public void setJobOriginalCost(Integer jobOriginalCost) {
        this.jobOriginalCost = jobOriginalCost;
    }

    public String getQuotedAmount() {
        return quotedAmount;
    }

    public void setQuotedAmount(String quotedAmount) {
        this.quotedAmount = quotedAmount;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public String getToCompany() {
        return toCompany;
    }

    public void setToCompany(String toCompany) {
        this.toCompany = toCompany;
    }

    public Integer getToLocationType() {
        return toLocationType;
    }

    public void setToLocationType(Integer toLocationType) {
        this.toLocationType = toLocationType;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getJobCustomer() {
        return jobCustomer;
    }

    public void setJobCustomer(String jobCustomer) {
        this.jobCustomer = jobCustomer;
    }

    public Long getServiceType() {
        return serviceType;
    }

    public void setServiceType(Long serviceType) {
        this.serviceType = serviceType;
    }

    public String getJobSales() {
        return jobSales;
    }

    public void setJobSales(String jobSales) {
        this.jobSales = jobSales;
    }

    public Long getJobDeadlineTime() {
        return jobDeadlineTime;
    }

    public void setJobDeadlineTime(Long jobDeadlineTime) {
        this.jobDeadlineTime = jobDeadlineTime;
    }

    public Long getJobTimeZone() {
        return jobTimeZone;
    }

    public void setJobTimeZone(Long jobTimeZone) {
        this.jobTimeZone = jobTimeZone;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Long getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Long shippingTime) {
        this.shippingTime = shippingTime;
    }

    public Long getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(Long insuranceType) {
        this.insuranceType = insuranceType;
    }

    public Boolean isIsStorage() {
        return isStorage;
    }

    public void setIsStorage(Boolean isStorage) {
        this.isStorage = isStorage;
    }

    public Boolean isIsPickPack() {
        return isPickPack;
    }

    public void setIsPickPack(Boolean isPickPack) {
        this.isPickPack = isPickPack;
    }

    public String getShipTotalWeight() {
        return shipTotalWeight;
    }

    public void setShipTotalWeight(String shipTotalWeight) {
        this.shipTotalWeight = shipTotalWeight;
    }

    public String getShipmentCurrency() {
        return shipmentCurrency;
    }

    public void setShipmentCurrency(String shipmentCurrency) {
        this.shipmentCurrency = shipmentCurrency;
    }

    public String getShipmentMetric() {
        return shipmentMetric;
    }

    public void setShipmentMetric(String shipmentMetric) {
        this.shipmentMetric = shipmentMetric;
    }

    public Integer getWoRequestType() {
        return woRequestType;
    }

    public void setWoRequestType(Integer woRequestType) {
        this.woRequestType = woRequestType;
    }

    public Long getWoCustomsBrokerageId() {
        return woCustomsBrokerageId;
    }

    public void setWoCustomsBrokerageId(Long woCustomsBrokerageId) {
        this.woCustomsBrokerageId = woCustomsBrokerageId;
    }

    public Long getWoStorageId() {
        return woStorageId;
    }

    public void setWoStorageId(Long woStorageId) {
        this.woStorageId = woStorageId;
    }

    public Long getWoPickPackId() {
        return woPickPackId;
    }

    public void setWoPickPackId(Long woPickPackId) {
        this.woPickPackId = woPickPackId;
    }

    public Long getWoShippingInfoId() {
        return woShippingInfoId;
    }

    public void setWoShippingInfoId(Long woShippingInfoId) {
        this.woShippingInfoId = woShippingInfoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WoWorkOrderDTO woWorkOrderDTO = (WoWorkOrderDTO) o;
        if (woWorkOrderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woWorkOrderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoWorkOrderDTO{" +
            "id=" + getId() +
            ", customsBrokerName='" + getCustomsBrokerName() + "'" +
            ", customsContactName='" + getCustomsContactName() + "'" +
            ", customsCurrency='" + getCustomsCurrency() + "'" +
            ", customsPhoneNumber='" + getCustomsPhoneNumber() + "'" +
            ", customsValue=" + getCustomsValue() +
            ", fromAirportCode='" + getFromAirportCode() + "'" +
            ", fromLocationType=" + getFromLocationType() +
            ", jobContentDescr='" + getJobContentDescr() + "'" +
            ", shipQuantity=" + getShipQuantity() +
            ", jobOriginalCost=" + getJobOriginalCost() +
            ", quotedAmount='" + getQuotedAmount() + "'" +
            ", assignTo='" + getAssignTo() + "'" +
            ", toCompany='" + getToCompany() + "'" +
            ", toLocationType=" + getToLocationType() +
            ", dateCreated='" + getDateCreated() + "'" +
            ", jobNumber='" + getJobNumber() + "'" +
            ", jobCustomer='" + getJobCustomer() + "'" +
            ", serviceType=" + getServiceType() +
            ", jobSales='" + getJobSales() + "'" +
            ", jobDeadlineTime=" + getJobDeadlineTime() +
            ", jobTimeZone=" + getJobTimeZone() +
            ", shippingDate='" + getShippingDate() + "'" +
            ", shippingTime=" + getShippingTime() +
            ", insuranceType=" + getInsuranceType() +
            ", isStorage='" + isIsStorage() + "'" +
            ", isPickPack='" + isIsPickPack() + "'" +
            ", shipTotalWeight='" + getShipTotalWeight() + "'" +
            ", shipmentCurrency='" + getShipmentCurrency() + "'" +
            ", shipmentMetric='" + getShipmentMetric() + "'" +
            ", woRequestType=" + getWoRequestType() +
            ", woCustomsBrokerage=" + getWoCustomsBrokerageId() +
            ", woStorage=" + getWoStorageId() +
            ", woPickPack=" + getWoPickPackId() +
            ", woShippingInfo=" + getWoShippingInfoId() +
            "}";
    }
}

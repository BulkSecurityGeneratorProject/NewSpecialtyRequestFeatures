package com.java.domain;



import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A WoWorkOrder.
 */
@Entity
@Table(name = "wo_work_order")
public class WoWorkOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "customs_broker_name", length = 255)
    private String customsBrokerName;

    @Size(max = 255)
    @Column(name = "customs_contact_name", length = 255)
    private String customsContactName;

    @Size(max = 255)
    @Column(name = "customs_currency", length = 255)
    private String customsCurrency;

    @Size(max = 255)
    @Column(name = "customs_phone_number", length = 255)
    private String customsPhoneNumber;

    @Column(name = "customs_value")
    private Double customsValue;

    @Size(max = 255)
    @Column(name = "from_airport_code", length = 255)
    private String fromAirportCode;

    @Max(value = 11)
    @Column(name = "from_location_type")
    private Integer fromLocationType;

    @Size(max = 255)
    @Column(name = "job_content_descr", length = 255)
    private String jobContentDescr;

    @Max(value = 11)
    @Column(name = "ship_quantity")
    private Integer shipQuantity;

    @Max(value = 11)
    @Column(name = "job_original_cost")
    private Integer jobOriginalCost;

    @Size(max = 255)
    @Column(name = "quoted_amount", length = 255)
    private String quotedAmount;

    @Size(max = 255)
    @Column(name = "assign_to", length = 255)
    private String assignTo;

    @Size(max = 255)
    @Column(name = "to_company", length = 255)
    private String toCompany;

    @Max(value = 11)
    @Column(name = "to_location_type")
    private Integer toLocationType;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

    @Size(max = 255)
    @Column(name = "job_number", length = 255)
    private String jobNumber;

    @Size(max = 255)
    @Column(name = "job_customer", length = 255)
    private String jobCustomer;

    @Column(name = "service_type")
    private Long serviceType;

    @Size(max = 255)
    @Column(name = "job_sales", length = 255)
    private String jobSales;

    @Column(name = "job_deadline_time")
    private Long jobDeadlineTime;

    @Column(name = "job_time_zone")
    private Long jobTimeZone;

    @Column(name = "shipping_date")
    private LocalDate shippingDate;

    @Column(name = "shipping_time")
    private Long shippingTime;

    @Column(name = "insurance_type")
    private Long insuranceType;

    @Column(name = "is_storage")
    private Boolean isStorage;

    @Column(name = "is_pick_pack")
    private Boolean isPickPack;

    @Size(max = 255)
    @Column(name = "ship_total_weight", length = 255)
    private String shipTotalWeight;

    @Size(max = 255)
    @Column(name = "shipment_currency", length = 255)
    private String shipmentCurrency;

    @Size(max = 255)
    @Column(name = "shipment_metric", length = 255)
    private String shipmentMetric;

    @Max(value = 11)
    @Column(name = "wo_request_type")
    private Integer woRequestType;

    @OneToOne
    @JoinColumn(unique = true)
    private WoCustomsBrokerage woCustomsBrokerage;

    @OneToOne
    @JoinColumn(unique = true)
    private WoStorage woStorage;

    @OneToOne
    @JoinColumn(unique = true)
    private WoPickPack woPickPack;

    @OneToOne
    @JoinColumn(unique = true)
    private WoShippingInfo woShippingInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomsBrokerName() {
        return customsBrokerName;
    }

    public WoWorkOrder customsBrokerName(String customsBrokerName) {
        this.customsBrokerName = customsBrokerName;
        return this;
    }

    public void setCustomsBrokerName(String customsBrokerName) {
        this.customsBrokerName = customsBrokerName;
    }

    public String getCustomsContactName() {
        return customsContactName;
    }

    public WoWorkOrder customsContactName(String customsContactName) {
        this.customsContactName = customsContactName;
        return this;
    }

    public void setCustomsContactName(String customsContactName) {
        this.customsContactName = customsContactName;
    }

    public String getCustomsCurrency() {
        return customsCurrency;
    }

    public WoWorkOrder customsCurrency(String customsCurrency) {
        this.customsCurrency = customsCurrency;
        return this;
    }

    public void setCustomsCurrency(String customsCurrency) {
        this.customsCurrency = customsCurrency;
    }

    public String getCustomsPhoneNumber() {
        return customsPhoneNumber;
    }

    public WoWorkOrder customsPhoneNumber(String customsPhoneNumber) {
        this.customsPhoneNumber = customsPhoneNumber;
        return this;
    }

    public void setCustomsPhoneNumber(String customsPhoneNumber) {
        this.customsPhoneNumber = customsPhoneNumber;
    }

    public Double getCustomsValue() {
        return customsValue;
    }

    public WoWorkOrder customsValue(Double customsValue) {
        this.customsValue = customsValue;
        return this;
    }

    public void setCustomsValue(Double customsValue) {
        this.customsValue = customsValue;
    }

    public String getFromAirportCode() {
        return fromAirportCode;
    }

    public WoWorkOrder fromAirportCode(String fromAirportCode) {
        this.fromAirportCode = fromAirportCode;
        return this;
    }

    public void setFromAirportCode(String fromAirportCode) {
        this.fromAirportCode = fromAirportCode;
    }

    public Integer getFromLocationType() {
        return fromLocationType;
    }

    public WoWorkOrder fromLocationType(Integer fromLocationType) {
        this.fromLocationType = fromLocationType;
        return this;
    }

    public void setFromLocationType(Integer fromLocationType) {
        this.fromLocationType = fromLocationType;
    }

    public String getJobContentDescr() {
        return jobContentDescr;
    }

    public WoWorkOrder jobContentDescr(String jobContentDescr) {
        this.jobContentDescr = jobContentDescr;
        return this;
    }

    public void setJobContentDescr(String jobContentDescr) {
        this.jobContentDescr = jobContentDescr;
    }

    public Integer getShipQuantity() {
        return shipQuantity;
    }

    public WoWorkOrder shipQuantity(Integer shipQuantity) {
        this.shipQuantity = shipQuantity;
        return this;
    }

    public void setShipQuantity(Integer shipQuantity) {
        this.shipQuantity = shipQuantity;
    }

    public Integer getJobOriginalCost() {
        return jobOriginalCost;
    }

    public WoWorkOrder jobOriginalCost(Integer jobOriginalCost) {
        this.jobOriginalCost = jobOriginalCost;
        return this;
    }

    public void setJobOriginalCost(Integer jobOriginalCost) {
        this.jobOriginalCost = jobOriginalCost;
    }

    public String getQuotedAmount() {
        return quotedAmount;
    }

    public WoWorkOrder quotedAmount(String quotedAmount) {
        this.quotedAmount = quotedAmount;
        return this;
    }

    public void setQuotedAmount(String quotedAmount) {
        this.quotedAmount = quotedAmount;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public WoWorkOrder assignTo(String assignTo) {
        this.assignTo = assignTo;
        return this;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public String getToCompany() {
        return toCompany;
    }

    public WoWorkOrder toCompany(String toCompany) {
        this.toCompany = toCompany;
        return this;
    }

    public void setToCompany(String toCompany) {
        this.toCompany = toCompany;
    }

    public Integer getToLocationType() {
        return toLocationType;
    }

    public WoWorkOrder toLocationType(Integer toLocationType) {
        this.toLocationType = toLocationType;
        return this;
    }

    public void setToLocationType(Integer toLocationType) {
        this.toLocationType = toLocationType;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public WoWorkOrder dateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public WoWorkOrder jobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
        return this;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getJobCustomer() {
        return jobCustomer;
    }

    public WoWorkOrder jobCustomer(String jobCustomer) {
        this.jobCustomer = jobCustomer;
        return this;
    }

    public void setJobCustomer(String jobCustomer) {
        this.jobCustomer = jobCustomer;
    }

    public Long getServiceType() {
        return serviceType;
    }

    public WoWorkOrder serviceType(Long serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public void setServiceType(Long serviceType) {
        this.serviceType = serviceType;
    }

    public String getJobSales() {
        return jobSales;
    }

    public WoWorkOrder jobSales(String jobSales) {
        this.jobSales = jobSales;
        return this;
    }

    public void setJobSales(String jobSales) {
        this.jobSales = jobSales;
    }

    public Long getJobDeadlineTime() {
        return jobDeadlineTime;
    }

    public WoWorkOrder jobDeadlineTime(Long jobDeadlineTime) {
        this.jobDeadlineTime = jobDeadlineTime;
        return this;
    }

    public void setJobDeadlineTime(Long jobDeadlineTime) {
        this.jobDeadlineTime = jobDeadlineTime;
    }

    public Long getJobTimeZone() {
        return jobTimeZone;
    }

    public WoWorkOrder jobTimeZone(Long jobTimeZone) {
        this.jobTimeZone = jobTimeZone;
        return this;
    }

    public void setJobTimeZone(Long jobTimeZone) {
        this.jobTimeZone = jobTimeZone;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public WoWorkOrder shippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Long getShippingTime() {
        return shippingTime;
    }

    public WoWorkOrder shippingTime(Long shippingTime) {
        this.shippingTime = shippingTime;
        return this;
    }

    public void setShippingTime(Long shippingTime) {
        this.shippingTime = shippingTime;
    }

    public Long getInsuranceType() {
        return insuranceType;
    }

    public WoWorkOrder insuranceType(Long insuranceType) {
        this.insuranceType = insuranceType;
        return this;
    }

    public void setInsuranceType(Long insuranceType) {
        this.insuranceType = insuranceType;
    }

    public Boolean isIsStorage() {
        return isStorage;
    }

    public WoWorkOrder isStorage(Boolean isStorage) {
        this.isStorage = isStorage;
        return this;
    }

    public void setIsStorage(Boolean isStorage) {
        this.isStorage = isStorage;
    }

    public Boolean isIsPickPack() {
        return isPickPack;
    }

    public WoWorkOrder isPickPack(Boolean isPickPack) {
        this.isPickPack = isPickPack;
        return this;
    }

    public void setIsPickPack(Boolean isPickPack) {
        this.isPickPack = isPickPack;
    }

    public String getShipTotalWeight() {
        return shipTotalWeight;
    }

    public WoWorkOrder shipTotalWeight(String shipTotalWeight) {
        this.shipTotalWeight = shipTotalWeight;
        return this;
    }

    public void setShipTotalWeight(String shipTotalWeight) {
        this.shipTotalWeight = shipTotalWeight;
    }

    public String getShipmentCurrency() {
        return shipmentCurrency;
    }

    public WoWorkOrder shipmentCurrency(String shipmentCurrency) {
        this.shipmentCurrency = shipmentCurrency;
        return this;
    }

    public void setShipmentCurrency(String shipmentCurrency) {
        this.shipmentCurrency = shipmentCurrency;
    }

    public String getShipmentMetric() {
        return shipmentMetric;
    }

    public WoWorkOrder shipmentMetric(String shipmentMetric) {
        this.shipmentMetric = shipmentMetric;
        return this;
    }

    public void setShipmentMetric(String shipmentMetric) {
        this.shipmentMetric = shipmentMetric;
    }

    public Integer getWoRequestType() {
        return woRequestType;
    }

    public WoWorkOrder woRequestType(Integer woRequestType) {
        this.woRequestType = woRequestType;
        return this;
    }

    public void setWoRequestType(Integer woRequestType) {
        this.woRequestType = woRequestType;
    }

    public WoCustomsBrokerage getWoCustomsBrokerage() {
        return woCustomsBrokerage;
    }

    public WoWorkOrder woCustomsBrokerage(WoCustomsBrokerage woCustomsBrokerage) {
        this.woCustomsBrokerage = woCustomsBrokerage;
        return this;
    }

    public void setWoCustomsBrokerage(WoCustomsBrokerage woCustomsBrokerage) {
        this.woCustomsBrokerage = woCustomsBrokerage;
    }

    public WoStorage getWoStorage() {
        return woStorage;
    }

    public WoWorkOrder woStorage(WoStorage woStorage) {
        this.woStorage = woStorage;
        return this;
    }

    public void setWoStorage(WoStorage woStorage) {
        this.woStorage = woStorage;
    }

    public WoPickPack getWoPickPack() {
        return woPickPack;
    }

    public WoWorkOrder woPickPack(WoPickPack woPickPack) {
        this.woPickPack = woPickPack;
        return this;
    }

    public void setWoPickPack(WoPickPack woPickPack) {
        this.woPickPack = woPickPack;
    }

    public WoShippingInfo getWoShippingInfo() {
        return woShippingInfo;
    }

    public WoWorkOrder woShippingInfo(WoShippingInfo woShippingInfo) {
        this.woShippingInfo = woShippingInfo;
        return this;
    }

    public void setWoShippingInfo(WoShippingInfo woShippingInfo) {
        this.woShippingInfo = woShippingInfo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoWorkOrder)) {
            return false;
        }
        return id != null && id.equals(((WoWorkOrder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoWorkOrder{" +
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
            "}";
    }
}
